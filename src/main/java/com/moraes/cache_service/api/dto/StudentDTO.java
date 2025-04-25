package com.moraes.cache_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Builder.Default
    private boolean withCache = Boolean.FALSE;
}
