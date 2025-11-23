package com.example.intelligenttutoringsystem.assessment.application;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.domain.AttemptItem;
import com.example.intelligenttutoringsystem.assessment.domain.StudentProfile;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.StudentProfileJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentProfileService {

    private final StudentProfileJpaRepository profileRepo;

    @Transactional(readOnly = true)
    public StudentProfile getOrCreate(String studentId) {
        return profileRepo.findByStudentId(studentId)
                .orElseGet(() -> {
                    StudentProfile p = StudentProfile.builder()
                            .studentId(studentId)
                            .totalScore(0.0)
                            .totalAttempts(0L)
                            .avgScoreEasy(0.0)
                            .avgScoreMedium(0.0)
                            .avgScoreHard(0.0)
                            .tags("")
                            .createdAt(Instant.now())
                            .updatedAt(Instant.now())
                            .build();
                    return profileRepo.save(p);
                });
    }

    @Transactional
    public void updateAfterAttempt(StudentProfile profile, double totalScore, List<AttemptItem> items) {
        profile.setTotalAttempts(profile.getTotalAttempts() + 1);
        profile.setTotalScore(profile.getTotalScore() + totalScore);
        profile.setUpdatedAt(Instant.now());

        long wrongCount = items.stream().filter(item -> Boolean.FALSE.equals(item.getIsCorrect())).count();
        if (wrongCount > items.size() / 2) {
            String mostWrongTopic = items.stream()
                    .filter(item -> Boolean.FALSE.equals(item.getIsCorrect()))
                    .map(AttemptItem::getQuestionId)
                    .findFirst()
                    .map(qId -> "weak-" + qId) // naive tag
                    .orElse("");
            if (!mostWrongTopic.isEmpty()
                    && (profile.getTags() == null || !profile.getTags().contains(mostWrongTopic))) {
                profile.setTags((profile.getTags() == null ? "" : profile.getTags() + ",") + mostWrongTopic);
            }
        }

        profileRepo.save(profile);
    }
}
