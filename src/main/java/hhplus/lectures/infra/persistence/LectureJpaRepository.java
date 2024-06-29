package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.Lecture;
import hhplus.lectures.domain.LectureInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
    @Query("""
                SELECT new hhplus.lectures.domain.LectureInfo(
                    ls.id,
                    l.id,
                    l.name,
                    ls.limitedCount,
                    ls.registeredCount,
                    ls.startDateTime,
                    ls.endDateTime
                )
                 FROM Lecture l
                 JOIN LectureSchedule ls ON l.id = ls.lectureId
                order by l.id asc, ls.startDateTime asc
            """)
    List<LectureInfo> findAllLectureInfo();
}
