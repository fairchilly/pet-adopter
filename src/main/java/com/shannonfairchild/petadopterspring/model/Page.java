package com.shannonfairchild.petadopterspring.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Page extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String path;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String html_content;

    @NonNull
    private Integer priority;
}
