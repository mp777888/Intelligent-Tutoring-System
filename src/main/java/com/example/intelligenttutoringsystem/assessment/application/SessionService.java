package com.example.intelligenttutoringsystem.assessment.application;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.assessment.application.dto.CreateSessionRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.UpdateSessionRequest;
import com.example.intelligenttutoringsystem.assessment.domain.Session;
import com.example.intelligenttutoringsystem.assessment.infrastructure.repository.SessionJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionJpaRepository sessionRepo;

    @Transactional
    public Session createOrResumeSession(CreateSessionRequest dto) {
        return sessionRepo.findByQuizIdAndStudentIdAndCompletedFalse(dto.quizId(), dto.studentId())
                .orElseGet(() -> {
                    Session s = Session.builder()
                            .quizId(dto.quizId())
                            .studentId(dto.studentId())
                            .completed(false)
                            .startedAt(Instant.now())
                            .updatedAt(Instant.now())
                            .build();
                    return sessionRepo.save(s);
                });
    }

    @Transactional(readOnly = true)
    public Session getSession(String id) {
        return sessionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
    }

    @Transactional(readOnly = true)
    public List<Session> listSessions(int page, int size) {
        return sessionRepo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }

    @Transactional
    public Session updateSession(String id, UpdateSessionRequest dto) {
        Session s = getSession(id);
        if (dto.completed() != null) {
            s.setCompleted(dto.completed());
            s.setUpdatedAt(Instant.now());
        }
        return sessionRepo.save(s);
    }

    @Transactional
    public void deleteSession(String id) {
        if (!sessionRepo.existsById(id))
            throw new IllegalArgumentException("Session not found");
        sessionRepo.deleteById(id);
    }
}
