package hhplus.lectures.domain;

public interface LectureScheduleRepository {
    LectureSchedule findById(Long id);
    LectureSchedule save(LectureSchedule lectureSchedule);
}
