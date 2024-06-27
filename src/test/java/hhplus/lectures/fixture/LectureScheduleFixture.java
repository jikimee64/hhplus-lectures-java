package hhplus.lectures.fixture;

import hhplus.lectures.domain.LectureSchedule;

import java.time.LocalDateTime;

public class LectureScheduleFixture {

    public static LectureSchedule 특강_스케줄(Long lectureId, int registeredCount, LocalDateTime startDateTime, LocalDateTime endDateTime){
        return new LectureSchedule(lectureId, 30, registeredCount, startDateTime, endDateTime);
    }

}
