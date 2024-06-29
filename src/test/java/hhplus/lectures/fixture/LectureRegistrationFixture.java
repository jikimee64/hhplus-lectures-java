package hhplus.lectures.fixture;

import hhplus.lectures.domain.LectureRegistration;

public class LectureRegistrationFixture {

    public static LectureRegistration 자바_특강_신청(long lectureId, long userId){
        return new LectureRegistration(lectureId, userId);
    }

}
