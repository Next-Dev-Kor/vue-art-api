package com.vueart.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FAVORTIE_CATEGORY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;
    private Long userId;
    private Long categoryId;
}

