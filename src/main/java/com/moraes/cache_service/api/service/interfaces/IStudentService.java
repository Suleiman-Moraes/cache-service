package com.moraes.cache_service.api.service.interfaces;

import java.util.List;

import com.moraes.cache_service.api.dto.StudentDTO;

public interface IStudentService {

    Long create(StudentDTO studentDTO);

    List<StudentDTO> getAll();

    StudentDTO getById(Long id);

    void populate();
}
