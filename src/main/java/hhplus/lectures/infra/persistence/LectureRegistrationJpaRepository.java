package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.LectureRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRegistrationJpaRepository extends JpaRepository<LectureRegistration, Long>{
    List<LectureRegistration> findByLectureScheduleIdAndUserId(Long lectureScheduleId, Long memberId);
}
