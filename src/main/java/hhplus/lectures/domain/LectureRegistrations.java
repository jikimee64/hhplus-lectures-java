package hhplus.lectures.domain;

import java.util.ArrayList;
import java.util.List;

public class LectureRegistrations {

    private final List<LectureRegistration> lectureRegistrations;

    public LectureRegistrations(List<LectureRegistration> lectureRegistrations) {
        this.lectureRegistrations = new ArrayList<>(lectureRegistrations);
    }

    public LectureRegistration register(long userId, long lectureId) {
        checkDuplicateRegistration(userId, lectureId);
        return new LectureRegistration(userId, lectureId);
    }

    private void checkDuplicateRegistration(long userId, long lectureId) {
        boolean isDuplicate = lectureRegistrations.stream()
                .anyMatch(registration -> registration.isSameBy(userId, lectureId));

        if (isDuplicate) {
            throw new RuntimeException("이미 신청한 특강입니다.");
        }
    }
}
