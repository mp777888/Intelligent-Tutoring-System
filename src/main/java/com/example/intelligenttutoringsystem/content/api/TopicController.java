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
import com.example.intelligenttutoringsystem.content.domain.Topic;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/content/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<Topic> create(@RequestBody CreateTopicRequest req) {
        Topic created = topicService.createTopic(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public Topic get(@PathVariable String id) {
        return topicService.getTopic(id);
    }

    @GetMapping
    public List<Topic> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return topicService.listTopics(page, size);
    }

    @PutMapping("/{id}")
    public Topic update(@PathVariable String id, @RequestBody CreateTopicRequest req) {
        return topicService.updateTopic(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        topicService.deleteTopic(id);
    }
}
