package com.congdinh.vivuchat.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    @NotBlank(message = "Model is required")
    private String model;
}
