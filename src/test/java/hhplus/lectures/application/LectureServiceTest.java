package hhplus.lectures.application;

import hhplus.lectures.domain.Lecture;
import hhplus.lectures.domain.LectureRegistration;
import hhplus.lectures.domain.LectureRegistrationRepository;
import hhplus.lectures.domain.LectureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static hhplus.lectures.fixture.LectureFixture.자바_특강;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class LectureServiceTest {

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
                자바_특강(1L, 0, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2))
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

}
