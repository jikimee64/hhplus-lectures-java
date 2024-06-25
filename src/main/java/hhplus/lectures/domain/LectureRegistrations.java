package hhplus.lectures.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureRegistrations {

    private final LectureRegistrationRepository lectureRegistrationRepository;
    private final LectureRepository lectureRepository;

    public LectureRegistration register(long userId, long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        checkDuplicateRegistration(userId, lecture.getId());
        validateOverStartDateTime(lecture);
        lecture.validateLimitedRegisterCount();
        return new LectureRegistration(userId, lecture.getId());
    }

    private void checkDuplicateRegistration(long userId, long lectureId) {
        List<LectureRegistration> lectureRegistrations = lectureRegistrationRepository.findBy(lectureId, userId);
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
