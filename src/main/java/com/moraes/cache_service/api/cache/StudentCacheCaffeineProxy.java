package com.moraes.cache_service.api.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.moraes.cache_service.api.dto.StudentDTO;
import com.moraes.cache_service.api.service.interfaces.IStudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Primary
@Service
public class StudentCacheCaffeineProxy implements IStudentService {

    @Qualifier("studentService")
    private final IStudentService service;

    private Cache<String, StudentDTO> cache = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES)
            .build();

    @Override
    public Long create(StudentDTO studentDTO) {
        final Long id = service.create(studentDTO);
        cache.put(String.valueOf(id), studentDTO);
        return id;
    }

    @Override
    public List<StudentDTO> getAll() {
        return service.getAll();
    }

    @Override
    public StudentDTO getById(Long id) {
        final String key = String.valueOf(id);
        StudentDTO studentDTO = cache.get(key, k -> service.getById(id));
        studentDTO.setWithCache(Boolean.TRUE);
        return studentDTO;
    }

    @Override
    public void populate() {
        service.populate();
    }
}
