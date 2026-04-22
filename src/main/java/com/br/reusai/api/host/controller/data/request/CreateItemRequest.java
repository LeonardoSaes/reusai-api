package com.br.reusai.api.host.controller.data.request;

import com.br.reusai.api.utils.constants.StatusEnum;

public record CreateItemRequest(String title, String description, String category, StatusEnum status, String imageUrl) {
}
