package hhplus.lectures.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int limitedCount;

    private int registeredCount;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public Lecture(String name, int limitedCount, int registeredCount, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.name = name;
        this.limitedCount = limitedCount;
        this.registeredCount = 0;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
