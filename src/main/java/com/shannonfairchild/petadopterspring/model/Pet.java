package com.shannonfairchild.petadopterspring.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NonNull
    private Sex sex;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType type;

    private Boolean featured;

    private Boolean declawed;

    private Boolean specialNeeds;

    public String calculateAge() {
        String age;
        Period period = Period.between(this.getBirthDate(), LocalDate.now());

        if (period.getYears() > 0) {
            age = period.getYears() + " years old";
        } else {
            age = period.getMonths() + " months old";
        }

        return age;
    }
}
