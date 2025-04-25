package com.moraes.cache_service.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;
import com.moraes.cache_service.api.dto.StudentDTO;
import com.moraes.cache_service.api.model.Student;
import com.moraes.cache_service.api.repository.IStudentRepository;
import com.moraes.cache_service.api.service.interfaces.IStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final IStudentRepository repository;

    private final Faker faker = new Faker();

    @Override
    @Transactional
    public Long create(StudentDTO studentDTO) {
        return repository.save(Student.builder().name(studentDTO.getName()).build()).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAll() {
        return repository.findAll().stream().map(student -> StudentDTO.builder().name(student.getName()).build()).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getById(Long id) {
        return repository.findById(id).map(student -> StudentDTO.builder().name(student.getName()).build()).orElseThrow();
    }

    @Override
    @Transactional
    public void populate() {
        List<Student> students = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            students.add(Student.builder().name(faker.name().fullName()).build());
        }
        repository.saveAll(students);
    }
}
