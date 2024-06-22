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

    public void validateLimitedRegisterCount() {
        if(this.limitedCount <= this.registeredCount){
            throw new RuntimeException("신청 가능한 인원을 초과하였습니다.");
        }
    }

    public Long getId() {
        return id;
    }
}
