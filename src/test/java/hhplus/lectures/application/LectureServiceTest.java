package hhplus.lectures.application;

import hhplus.lectures.IntegrationTest;
import hhplus.lectures.domain.Lecture;
import hhplus.lectures.domain.LectureRegistration;
import hhplus.lectures.domain.LectureRegistrationRepository;
import hhplus.lectures.domain.LectureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static hhplus.lectures.fixture.LectureFixture.스프링_특강;
import static hhplus.lectures.fixture.LectureFixture.자바_특강;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class LectureServiceTest extends IntegrationTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureRegistrationRepository lectureRegistrationRepository;

    @Test
    void 특강_신청에_성공한다() {
        // given
        lectureRepository.save(
                자바_특강(0, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2))
        );
        long userId = 1L;
        long lectureId = 1L;

        // when
        lectureService.apply(lectureId, userId);

        // then
        List<LectureRegistration> lectureRegistrations = lectureRegistrationRepository.findBy(lectureId, userId);
        assertThat(lectureRegistrations).hasSize(1)
                .extracting("userId", "lectureId")
                .containsExactly(
                        tuple(userId, lectureId)
                );
    }

    @Test
    void 특강_등록자_명단에_존재하는_사용자는_true를_반환받는다() {
        // given
        lectureRepository.save(
                자바_특강(0, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2))
        );
        long userId = 1L;
        long lectureId = 1L;
        lectureService.apply(lectureId, userId);

        // when
        boolean isRegistered = lectureService.hasUserAppliedForLecture(lectureId, userId);

        // then
        assertThat(isRegistered).isTrue();
    }

    @Test
    void 특강_등록자_명단에_존재하지_않는_사용자는_false를_반환받는다() {
        // given
        long userId = 1L;
        long lectureId = 1L;

        // when
        boolean isRegistered = lectureService.hasUserAppliedForLecture(lectureId, userId);

        // then
        assertThat(isRegistered).isFalse();
    }

    @Test
    void 모든_특강을_조회한다() {
        // given
        LocalDateTime javaStartDate = LocalDateTime.now().plusDays(1);
        LocalDateTime javaEndDate = LocalDateTime.now().plusDays(1).plusHours(2);
        lectureRepository.save(
                자바_특강( 0, javaStartDate, javaEndDate)
        );

        LocalDateTime springStartDate = LocalDateTime.now().plusDays(2);
        LocalDateTime springEndDate = LocalDateTime.now().plusDays(2).plusHours(3);
        lectureRepository.save(
                스프링_특강( 0, springStartDate, springEndDate)
        );

        // when
        List<Lecture> lectures = lectureService.selectLectures();

        // then
        assertThat(lectures).hasSize(2)
                .extracting("limitedCount", "startDateTime", "endDateTime")
                .containsExactly(
                        tuple(30, javaStartDate, javaEndDate),
                        tuple(30, springStartDate, springEndDate)
                );
    }

}
