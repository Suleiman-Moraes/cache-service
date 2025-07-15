package com.moraes.cache_service.api.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.moraes.cache_service.api.dto.StudentDTO;
import com.moraes.cache_service.api.service.interfaces.IStudentService;

import lombok.RequiredArgsConstructor;

@Profile("redis")
@RequiredArgsConstructor
@Primary
@Service
public class StudentCacheRedisProxy implements IStudentService {

    @Qualifier("studentService")
    private final IStudentService service;

    private final RedisTemplate<String, StudentDTO> redisTemplate;

    private static final long TTL_MINUTES = 60;

    /**
     * Creates a new student by delegating to the underlying service and stores the
     * created StudentDTO in Redis cache.
     * 
     * Steps:
     * 
     * Calls the create method of the proxied service to persist the student and
     * obtain its generated ID.
     * Generates a cache key based on the student ID.
     * Saves the StudentDTO into Redis cache with a time-to-live (TTL) of 60
     * minutes.
     * 
     *
     * @param studentDTO the student data to be created
     * @return the generated student ID after creation
     */
    @Override
    public Long create(StudentDTO studentDTO) {
        Long id = service.create(studentDTO);
        String key = getKey(id);
        ValueOperations<String, StudentDTO> ops = redisTemplate.opsForValue();
        ops.set(key, studentDTO, TTL_MINUTES, TimeUnit.MINUTES);
        return id;
    }

    @Override
    public List<StudentDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a student by ID, checking the Redis cache first.
     * 
     * Steps:
     * 
     * Generates a cache key based on the student ID.
     * Attempts to fetch the StudentDTO from Redis cache.
     * If found in cache, sets the 'withCache' flag to true and returns it.
     * If not found, delegates to the underlying service to fetch from the
     * database, stores it in Redis with a TTL of 60 minutes, sets the 'withCache'
     * flag to false, and returns it.
     * 
     *
     * @param id the student ID to retrieve
     * @return the StudentDTO, either from cache or database
     */
    @Override
    public StudentDTO getById(Long id) {
        String key = getKey(id);
        ValueOperations<String, StudentDTO> ops = redisTemplate.opsForValue();
        StudentDTO studentDTO = ops.get(key);

        if (studentDTO != null) {
            studentDTO.setWithCache(Boolean.TRUE);
            studentDTO.setCacheName("Redis");
            return studentDTO;
        }

        studentDTO = service.getById(id);
        ops.set(key, studentDTO, TTL_MINUTES, TimeUnit.MINUTES);
        studentDTO.setWithCache(Boolean.FALSE);
        return studentDTO;
    }

    @Override
    public void populate() {
        service.populate();
    }

    private String getKey(Long id) {
        return "student:" + id;
    }
}
