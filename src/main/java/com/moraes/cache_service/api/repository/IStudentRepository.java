package com.moraes.cache_service.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moraes.cache_service.api.model.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {

}
