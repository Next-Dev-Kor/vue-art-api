package com.vueart.api.dto.request.favorite;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddFavoriteCategoryRequest {

    private Long userId;
    private List<Long> categoryIds;
}
