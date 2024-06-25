package hhplus.lectures.fixture;

import hhplus.lectures.domain.Lecture;

import java.time.LocalDateTime;

public class LectureFixture {

    public static Lecture 자바_특강(int registeredCount, LocalDateTime startDateTime, LocalDateTime endDateTime){
        return new Lecture("자바 특강", 30, registeredCount, startDateTime, endDateTime);
    }

    public static Lecture 스프링_특강(int registeredCount, LocalDateTime startDateTime, LocalDateTime endDateTime){
        return new Lecture("스프링 특강", 30, registeredCount, startDateTime, endDateTime);
    }
}
