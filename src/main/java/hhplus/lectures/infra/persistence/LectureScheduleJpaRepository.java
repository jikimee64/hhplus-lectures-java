package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.LectureSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureScheduleJpaRepository extends JpaRepository<LectureSchedule, Long> {
    Optional<LectureSchedule> findByLectureId(Long lectureId);
}
