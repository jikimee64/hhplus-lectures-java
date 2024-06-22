package hhplus.lectures.domain;

import com.google.common.annotations.VisibleForTesting;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRegistration extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lectureId;

    private Long userId;

    @VisibleForTesting
    public LectureRegistration(Long id, Long lectureId, Long userId) {
        this.id = id;
        this.lectureId = lectureId;
        this.userId = userId;
    }

    public LectureRegistration(Long lectureId, Long userId) {
        this.lectureId = lectureId;
        this.userId = userId;
    }

    public boolean isSameBy(long lectureId, long userId) {
        return this.lectureId == lectureId && this.userId == userId;
    }

}
