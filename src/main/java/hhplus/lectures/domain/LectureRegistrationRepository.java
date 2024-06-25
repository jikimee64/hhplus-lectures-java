package hhplus.lectures.domain;

import java.util.List;

public interface LectureRegistrationRepository {
    LectureRegistration save(LectureRegistration lectureRegistration);
    List<LectureRegistration> findBy(Long lectureId, Long memberId);
    long count();
}
