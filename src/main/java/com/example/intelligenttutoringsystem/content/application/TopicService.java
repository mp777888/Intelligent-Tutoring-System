package com.example.intelligenttutoringsystem.content.application;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.intelligenttutoringsystem.content.domain.Topic;
import com.example.intelligenttutoringsystem.content.infrastructure.repository.TopicJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicJpaRepository topicRepo;

    @Transactional
    public Topic createTopic(CreateTopicRequest dto) {
        Topic topic = Topic.builder()
                .name(dto.name())
                .description(dto.description())
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

    @Transactional
    public Topic updateTopic(String id, CreateTopicRequest dto) {
        Topic topic = getTopic(id);
        topic.setName(dto.name());
        topic.setDescription(dto.description());
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
