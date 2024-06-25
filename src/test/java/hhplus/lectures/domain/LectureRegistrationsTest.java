package hhplus.lectures.domain;

import hhplus.lectures.IntegrationTest;
import hhplus.lectures.fixture.LectureRegistrationFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static hhplus.lectures.fixture.LectureFixture.자바_특강;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class LectureRegistrationsTest extends IntegrationTest {

    @Autowired
    private LectureRegistrations lectureRegistrations;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureRegistrationRepository lectureRegistrationRepository;

    private final long userId = 1L;
    private final long lectureId = 1L;

    @Test
    void 사용자는_특강을_신청할_수_있다() {
        // given
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        lectureRepository.save(자바_특강(0, startDateTime, endDateTime));

        // when
        LectureRegistration lectureRegistration = lectureRegistrations.register(userId, lectureId);

        // then
        assertThat(lectureRegistration.isSameBy(userId, lectureId)).isTrue();
    }

    @Test
    void 특강에_이미_신청한_사용자는_같은_특강을_중복해서_신청할_수_없다() {
        // given
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        Lecture lecture = lectureRepository.save(자바_특강(0, startDateTime, endDateTime));
        lectureRegistrationRepository.save(LectureRegistrationFixture.자바_특강_신청(lecture.getId(), userId));

        // when & then
        assertThatThrownBy(() -> lectureRegistrations.register(userId, lectureId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 신청한 특강입니다.");
    }

    @Test
    void 특강_시작일자가_지난경우_특강을_신청할_수_없다() {
        // given
        LocalDateTime startDateTime = LocalDateTime.now().minusSeconds(1L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        lectureRepository.save(자바_특강(0, startDateTime, endDateTime));

        // when & then
        assertThatThrownBy(() -> lectureRegistrations.register(userId, lectureId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("특강 시작일자가 이미 지났습니다.");
    }

    @Test
    void 특강_제한인원인_30명안에_들지_못했을_경우_특강을_신청할_수_없다() {
        // given
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        lectureRepository.save(자바_특강(30, startDateTime, endDateTime));

        // when & then
        assertThatThrownBy(() -> lectureRegistrations.register(userId, lectureId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("신청 가능한 인원을 초과하였습니다.");
    }

}
