package hhplus.lectures.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureRegistrations {

    private final LectureRegistrationRepository lectureRegistrationRepository;
    private final LectureScheduleRepository lectureScheduleRepository;

    public LectureRegistration register(long lectureScheduleId, long userId) {
        LectureSchedule lectureSchedule = lectureScheduleRepository.findById(lectureScheduleId);
        checkDuplicateRegistration(userId, lectureSchedule.getId());
        validateOverStartDateTime(lectureSchedule);
        lectureSchedule.increaseRegisterCount();
        return new LectureRegistration(lectureSchedule.getId(), userId);
    }

    private void checkDuplicateRegistration(long userId, long lectureScheduleId) {
        List<LectureRegistration> lectureRegistrations = lectureRegistrationRepository.findBy(lectureScheduleId, userId);
        boolean isDuplicate = lectureRegistrations.stream()
                .anyMatch(registration -> registration.isSameBy(userId, lectureScheduleId));

        if (isDuplicate) {
            throw new RuntimeException("이미 신청한 특강입니다.");
        }
    }

    private void validateOverStartDateTime(LectureSchedule lectureSchedule) {
        if(lectureSchedule.isOverStartDateTime()) {
            throw new RuntimeException("특강 시작일자가 이미 지났습니다.");
        }
    }

}
