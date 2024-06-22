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

    private Long lectureId;

    private Long userId;

    public LectureRegistration(Long lectureId, Long userId) {
        this.lectureId = lectureId;
        this.userId = userId;
    }

    public boolean isSameBy(long lectureId, long userId) {
        return this.lectureId == lectureId && this.userId == userId;
    }

}
