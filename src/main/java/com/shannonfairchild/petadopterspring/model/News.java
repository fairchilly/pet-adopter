package com.shannonfairchild.petadopterspring.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class News extends Auditable<String> {

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

    private Boolean visible;
}
