package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.LectureSchedule;
import hhplus.lectures.domain.LectureScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureScheduleRepositoryImpl implements LectureScheduleRepository {

    private final LectureScheduleJpaRepository lectureScheduleJpaRepository;

    @Override
    public LectureSchedule findById(Long id) {
        return lectureScheduleJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("강의 스케줄이 존재하지 않습니다."));
    }

    @Override
    public LectureSchedule save(LectureSchedule lectureSchedule) {
        return lectureScheduleJpaRepository.save(lectureSchedule);
    }
}
