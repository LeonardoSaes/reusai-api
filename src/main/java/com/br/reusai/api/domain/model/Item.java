package com.br.reusai.api.domain.model;

import com.br.reusai.api.utils.constants.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Item {
    private String id;
    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private StatusEnum status;
    private Boolean availableToChange;
    private String idUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Item(String id, LocalDateTime updatedAt, LocalDateTime createdAt, String idUser, Boolean availableToChange, StatusEnum status, String imageUrl, String category, String description, String title) {
        this.id = id;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.idUser = idUser;
        this.availableToChange = availableToChange;
        this.status = status;
        this.imageUrl = imageUrl;
        this.category = category;
        this.description = description;
        this.title = title;
    }
}
