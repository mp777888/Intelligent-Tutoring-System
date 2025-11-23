package com.example.intelligenttutoringsystem.content.application;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.content.domain.Course;
import com.example.intelligenttutoringsystem.content.domain.Topic;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.CourseJpaRepository;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.TopicJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicJpaRepository topicRepo;
    private final CourseJpaRepository courseRepo;

    @Transactional
    public Topic createTopic(CreateTopicRequest dto) {
        if (dto.courseId() == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }

        Course course = courseRepo.findById(dto.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Topic topic = Topic.builder()
                .name(dto.name())
                .description(dto.description())
                .course(course)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        return topicRepo.save(topic);
    }

    @Transactional(readOnly = true)
    public Topic getTopic(String id) {
        return topicRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Topic not found"));
    }

    @Transactional(readOnly = true)
    public List<Topic> listTopics(int page, int size) {
        return topicRepo.findAll(org.springframework.data.domain.PageRequest.of(page, size)).getContent();
    }

    @Transactional(readOnly = true)
    public List<Topic> getTopicsByCourse(String courseId) {
        return topicRepo.findByCourseId(courseId);
    }

    @Transactional
    public Topic updateTopic(String id, CreateTopicRequest dto) {
        Topic topic = getTopic(id);
        topic.setName(dto.name());
        topic.setDescription(dto.description());

        if (dto.courseId() != null && !dto.courseId().equals(topic.getCourse().getId())) {
            Course newCourse = courseRepo.findById(dto.courseId())
                    .orElseThrow(() -> new IllegalArgumentException("Course not found"));
            topic.setCourse(newCourse);
        }

        topic.setUpdatedAt(Instant.now());
        return topicRepo.save(topic);
    }

    @Transactional
    public void deleteTopic(String id) {
        if (!topicRepo.existsById(id)) {
            throw new IllegalArgumentException("Topic not found");
        }
        topicRepo.deleteById(id);
    }
}
