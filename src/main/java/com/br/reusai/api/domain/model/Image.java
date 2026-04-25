package com.br.reusai.api.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Image {
    private Integer id;
    private String url;
    private LocalDateTime createdAt;
}
