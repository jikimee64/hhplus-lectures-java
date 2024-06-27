package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.LectureRegistration;
import hhplus.lectures.domain.LectureRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRegistrationRepositoryImpl implements LectureRegistrationRepository {
    private final LectureRegistrationJpaRepository lectureRegistrationJpaRepository;

    public LectureRegistration save(LectureRegistration lectureRegistration) {
        return lectureRegistrationJpaRepository.save(lectureRegistration);
    }

    @Override
    public List<LectureRegistration> findBy(Long lectureScheduleId, Long userId) {
        return lectureRegistrationJpaRepository.findByLectureScheduleIdAndUserId(lectureScheduleId, userId);
    }

    @Override
    public long count() {
        return lectureRegistrationJpaRepository.count();
    }

}
