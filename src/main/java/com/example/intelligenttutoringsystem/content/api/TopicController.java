package com.example.intelligenttutoringsystem.content.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.intelligenttutoringsystem.content.application.CreateTopicRequest;
import com.example.intelligenttutoringsystem.content.application.TopicService;
import com.example.intelligenttutoringsystem.content.application.dto.ContentMapper;
import com.example.intelligenttutoringsystem.content.application.dto.TopicResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/content/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final ContentMapper contentMapper;

    @PostMapping
    public ResponseEntity<TopicResponse> create(@RequestBody CreateTopicRequest req) {
        var created = topicService.createTopic(req);
        TopicResponse response = contentMapper.toTopicResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public TopicResponse get(@PathVariable String id) {
        var topic = topicService.getTopic(id);
        return contentMapper.toTopicResponse(topic);
    }

    @GetMapping
    public List<TopicResponse> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        var topics = topicService.listTopics(page, size);
        return topics.stream().map(contentMapper::toTopicResponse).toList();
    }

    @GetMapping("/by-course/{courseId}")
    public List<TopicResponse> getTopicsByCourse(@PathVariable String courseId) {
        var topics = topicService.getTopicsByCourse(courseId);
        return topics.stream().map(contentMapper::toTopicResponse).toList();
    }

    @PutMapping("/{id}")
    public TopicResponse update(@PathVariable String id, @RequestBody CreateTopicRequest req) {
        var updated = topicService.updateTopic(id, req);
        return contentMapper.toTopicResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        topicService.deleteTopic(id);
    }
}
