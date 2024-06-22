package hhplus.lectures.fixture;

import hhplus.lectures.domain.Lecture;

import java.time.LocalDateTime;

public class LectureFixture {

    public static Lecture 자바_특강(long id, int registeredCount, LocalDateTime startDateTime, LocalDateTime endDateTime){
        return new Lecture(id, "자바 특강", 30, registeredCount, startDateTime, endDateTime);
    }
}
