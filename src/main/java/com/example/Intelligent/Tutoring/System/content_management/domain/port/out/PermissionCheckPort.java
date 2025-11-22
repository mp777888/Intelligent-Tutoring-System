package com.example.Intelligent.Tutoring.System.content_management.domain.port.out;

public interface PermissionCheckPort {
    boolean hasPermission(Long userId, Long courseId);
}
