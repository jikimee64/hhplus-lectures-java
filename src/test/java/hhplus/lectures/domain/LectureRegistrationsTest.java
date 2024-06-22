package hhplus.lectures.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LectureRegistrationsTest {

    @Test
    void 사용자는_특강을_신청할_수_있다() {
        // given
        long userId = 1L;
        long lectureId = 1L;
        LectureRegistrations lectureRegistrations = new LectureRegistrations(new ArrayList<>());

        // when
        LectureRegistration lectureRegistration = lectureRegistrations.register(userId, lectureId);

        // then
        assertThat(lectureRegistration.isSameBy(userId, lectureId)).isTrue();
    }

    @Test
    void 특강에_이미_신청한_사용자는_같은_특강을_중복해서_신청할_수_없다() {
        // given
        long userId = 1L;
        long lectureId = 1L;
        LectureRegistrations lectureRegistrations = new LectureRegistrations(
                List.of(new LectureRegistration(1L, userId, lectureId))
        );

        // when & then
        assertThatThrownBy(() -> lectureRegistrations.register(userId, lectureId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 신청한 특강입니다.");
    }

}
