package hhplus.lectures.domain;

import java.util.ArrayList;
import java.util.List;

public class LectureRegistrations {

    private final List<LectureRegistration> lectureRegistrations;

    public LectureRegistrations(List<LectureRegistration> lectureRegistrations) {
        this.lectureRegistrations = new ArrayList<>(lectureRegistrations);
    }

    public LectureRegistration register(long userId, Lecture lecture) {
        checkDuplicateRegistration(userId, lecture.getId());
        validateOverStartDateTime(lecture);
        return new LectureRegistration(userId, lecture.getId());
    }

    private void checkDuplicateRegistration(long userId, long lectureId) {
        boolean isDuplicate = lectureRegistrations.stream()
                .anyMatch(registration -> registration.isSameBy(userId, lectureId));

        if (isDuplicate) {
            throw new RuntimeException("이미 신청한 특강입니다.");
        }
    }

    private void validateOverStartDateTime(Lecture lecture) {
        if(lecture.isOverStartDateTime()) {
            throw new RuntimeException("특강 시작일자가 이미 지났습니다.");
        }
    }
}
