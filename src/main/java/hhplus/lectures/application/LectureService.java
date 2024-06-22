package hhplus.lectures.application;

import hhplus.lectures.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureRegistrationRepository lectureRegistrationRepository;

    public void apply(long lectureId, long userId) {
        LectureRegistrations lectureRegistrations = new LectureRegistrations(
                lectureRegistrationRepository.findBy(lectureId, userId)
        );
        Lecture lecture = lectureRepository.findById(lectureId);
        LectureRegistration lectureRegistration = lectureRegistrations.register(userId, lecture);
        lectureRegistrationRepository.save(lectureRegistration);
    }

}
