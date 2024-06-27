package hhplus.lectures.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRegistration extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lectureScheduleId;

    private Long userId;

    public LectureRegistration(Long lectureScheduleId, Long userId) {
        this.lectureScheduleId = lectureScheduleId;
        this.userId = userId;
    }

    public boolean isSameBy(long lectureScheduleId, long userId) {
        return this.lectureScheduleId == lectureScheduleId && this.userId == userId;
    }

}
