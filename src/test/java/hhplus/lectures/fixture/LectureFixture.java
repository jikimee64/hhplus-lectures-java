package hhplus.lectures.fixture;

import hhplus.lectures.domain.Lecture;

public class LectureFixture {

    public static Lecture 자바_특강() {
        return new Lecture("자바 특강");
    }

    public static Lecture 스프링_특강(){
        return new Lecture("스프링 특강");
    }
}
