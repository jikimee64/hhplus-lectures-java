package hhplus.lectures.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int limitedCount;

    private int registeredCount;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public boolean isOverStartDateTime() {
        return LocalDateTime.now().isAfter(startDateTime);
    }

    public Lecture increaseRegisteredCount() {
        return new Lecture(
              this.id,
              this.name,
              this.limitedCount,
              this.registeredCount + 1,
              this.startDateTime,
              this.endDateTime
        );
    }

    public Long getId() {
        return id;
    }
}
