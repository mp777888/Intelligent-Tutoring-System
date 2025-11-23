package com.example.intelligenttutoringsystem.assessment.api;

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

import com.example.intelligenttutoringsystem.assessment.application.SessionService;
import com.example.intelligenttutoringsystem.assessment.application.dto.CreateSessionRequest;
import com.example.intelligenttutoringsystem.assessment.application.dto.UpdateSessionRequest;
import com.example.intelligenttutoringsystem.assessment.domain.Session;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessments/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<Session> createOrResume(@RequestBody CreateSessionRequest req) {
        Session session = sessionService.createOrResumeSession(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @GetMapping("/{id}")
    public Session get(@PathVariable String id) {
        return sessionService.getSession(id);
    }

    @GetMapping
    public List<Session> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return sessionService.listSessions(page, size);
    }

    @PutMapping("/{id}")
    public Session update(@PathVariable String id, @RequestBody UpdateSessionRequest req) {
        return sessionService.updateSession(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        sessionService.deleteSession(id);
    }
}
