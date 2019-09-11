package com.shannonfairchild.petadopterspring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class News extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Please enter an author")
    private String author;

    @NotEmpty(message = "Please enter a title")
    private String title;

    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String html_content;

    private Boolean visible;

    public String substringSubtitle() {
        int length = 100;

        if (subtitle.length() >= length) {
            return subtitle.substring(0, length);
        }

        return subtitle;
    }
}
