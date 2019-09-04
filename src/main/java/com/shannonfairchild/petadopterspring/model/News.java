package com.shannonfairchild.petadopterspring.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String author;

    @NonNull
    private String title;

    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String html_content;

    @NonNull
    private LocalDate createdAt;

    private LocalDate updatedAt;

    private Boolean visible;
}
