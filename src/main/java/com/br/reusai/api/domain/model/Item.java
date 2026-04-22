package com.br.reusai.api.domain.model;

import com.br.reusai.api.utils.constants.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    private String id;
    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private StatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
