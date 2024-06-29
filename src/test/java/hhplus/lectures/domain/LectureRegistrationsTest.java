package hhplus.lectures.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static hhplus.lectures.fixture.LectureFixture.자바_특강;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LectureRegistrationsTest {

    @Test
    void 사용자는_특강을_신청할_수_있다() {
        // given
        long userId = 1L;
        long lectureId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        Lecture lecture = 자바_특강(lectureId, 29, startDateTime, endDateTime);
        LectureRegistrations lectureRegistrations = new LectureRegistrations(new ArrayList<>());

        // when
        LectureRegistration lectureRegistration = lectureRegistrations.register(userId, lecture);

        // then
        assertThat(lectureRegistration.isSameBy(userId, lectureId)).isTrue();
    }

    @Test
    void 특강에_이미_신청한_사용자는_같은_특강을_중복해서_신청할_수_없다() {
        // given
        long userId = 1L;
        long lectureId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        Lecture lecture = 자바_특강(lectureId, 29, startDateTime, endDateTime);
        LectureRegistrations lectureRegistrations = new LectureRegistrations(
                List.of(new LectureRegistration(1L, userId, lectureId))
        );

        // when & then
        assertThatThrownBy(() -> lectureRegistrations.register(userId, lecture))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 신청한 특강입니다.");
    }

    @Test
    void 특강_시작일자가_지난경우_특강을_신청할_수_없다() {
        // given
        long userId = 1L;
        long lectureId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now().minusSeconds(1L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        Lecture lecture = 자바_특강(lectureId, 29, startDateTime, endDateTime);
        LectureRegistrations lectureRegistrations = new LectureRegistrations(new ArrayList<>());

        // when & then
        assertThatThrownBy(() -> lectureRegistrations.register(userId, lecture))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("특강 시작일자가 이미 지났습니다.");
    }

    @Test
    void 특강_제한인원인_30명안에_들지_못했을_경우_특강을_신청할_수_없다() {
        // given
        long userId = 1L;
        long lectureId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now().plusSeconds(5L);
        LocalDateTime endDateTime = LocalDateTime.now().plusSeconds(60L);
        Lecture lecture = 자바_특강(lectureId, 30, startDateTime, endDateTime);
        LectureRegistrations lectureRegistrations = new LectureRegistrations(new ArrayList<>());

        // when & then
        assertThatThrownBy(() -> lectureRegistrations.register(userId, lecture))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("신청 가능한 인원을 초과하였습니다.");
    }

}
