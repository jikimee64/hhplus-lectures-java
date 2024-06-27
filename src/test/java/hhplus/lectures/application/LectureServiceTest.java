package hhplus.lectures.application;

import hhplus.lectures.IntegrationTest;
import hhplus.lectures.application.dto.LectureInfoApplicationDto;
import hhplus.lectures.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static hhplus.lectures.fixture.LectureFixture.스프링_특강;
import static hhplus.lectures.fixture.LectureFixture.자바_특강;
import static hhplus.lectures.fixture.LectureScheduleFixture.특강_스케줄;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class LectureServiceTest extends IntegrationTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureScheduleRepository lectureScheduleRepository;

    @Autowired
    private LectureRegistrationRepository lectureRegistrationRepository;

    @Test
    void 특강_신청에_성공한다() {
        // given
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        Lecture lecture = lectureRepository.save(자바_특강());
        LectureSchedule lectureSchedule = 특강_및_스케줄_등록(lecture, 0, startDateTime, endDateTime);
        Long lectureScheduleId = lectureSchedule.getId();

        long userId = 1L;

        // when
        lectureService.apply(lectureScheduleId, userId);

        // then
        List<LectureRegistration> lectureRegistrations = lectureRegistrationRepository.findBy(lectureScheduleId, userId);
        assertThat(lectureRegistrations).hasSize(1)
                .extracting("userId", "lectureScheduleId")
                .containsExactly(
                        tuple(userId, lectureScheduleId)
                );
    }

    @Test
    void 특강_등록자_명단에_존재하는_사용자는_true를_반환받는다() {
        // given
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        Lecture lecture = lectureRepository.save(자바_특강());
        LectureSchedule lectureSchedule = 특강_및_스케줄_등록(lecture, 0, startDateTime, endDateTime);
        Long lectureScheduleId = lectureSchedule.getId();

        long userId = 1L;
        lectureService.apply(lectureSchedule.getId(), userId);

        // when
        boolean isRegistered = lectureService.hasUserAppliedForLecture(lectureScheduleId, userId);

        // then
        assertThat(isRegistered).isTrue();
    }

    @Test
    void 특강_등록자_명단에_존재하지_않는_사용자는_false를_반환받는다() {
        // given
        long userId = 1L;
        long lectureScheduleId = 1L;

        // when
        boolean isRegistered = lectureService.hasUserAppliedForLecture(lectureScheduleId, userId);

        // then
        assertThat(isRegistered).isFalse();
    }

    @Test
    void 모든_특강을_조회한다() {
        // given
        LocalDateTime javaStartDate = LocalDateTime.now().plusDays(1);
        LocalDateTime javaEndDate = LocalDateTime.now().plusDays(1).plusHours(2);
        Lecture javaLecture = lectureRepository.save(자바_특강());
        특강_및_스케줄_등록(javaLecture, 0, javaStartDate, javaEndDate);

        LocalDateTime springStartDate = LocalDateTime.now().plusDays(2);
        LocalDateTime springEndDate = LocalDateTime.now().plusDays(2).plusHours(3);
        Lecture springLecture = lectureRepository.save(스프링_특강());
        특강_및_스케줄_등록(springLecture, 0, springStartDate, springEndDate);

        // when
        List<LectureInfoApplicationDto> lectureInfos = lectureService.findLectures();

        // then
        assertThat(lectureInfos).hasSize(2)
                .extracting("lectureName", "limitedCount", "startDateTime", "endDateTime")
                .containsExactly(
                        tuple("자바 특강", 30, javaStartDate, javaEndDate),
                        tuple("스프링 특강", 30, springStartDate, springEndDate)
                );
    }

    private LectureSchedule 특강_및_스케줄_등록(Lecture lecture, int registeredCount, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        return lectureScheduleRepository.save(특강_스케줄(
                lecture.getId(),
                registeredCount,
                startDateTime,
                endDateTime
        ));
    }

}
