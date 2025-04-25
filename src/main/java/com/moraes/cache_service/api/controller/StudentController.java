package com.moraes.cache_service.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moraes.cache_service.api.dto.StudentDTO;
import com.moraes.cache_service.api.service.interfaces.IStudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final IStudentService service;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(StudentDTO studentDTO) {
        final Long id = service.create(studentDTO);
        final URI uri = URI.create("/api/v1/students/".concat(id.toString()));
        return ResponseEntity.created(uri).body(id);
    }

    @PostMapping("/populate")
    public ResponseEntity<Void> populate() {
        service.populate();
        return ResponseEntity.noContent().build();
    }
}
