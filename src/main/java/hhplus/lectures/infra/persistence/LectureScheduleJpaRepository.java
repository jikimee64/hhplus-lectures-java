package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.LectureSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureScheduleJpaRepository extends JpaRepository<LectureSchedule, Long> {
}
