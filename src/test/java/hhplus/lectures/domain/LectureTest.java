package hhplus.lectures.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static hhplus.lectures.fixture.LectureFixture.자바_특강;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LectureTest {

    @Test
    void 특강_시작시간이_지난경우_true_반환() {
        // given
        Lecture lecture = 자바_특강(1L, 10, LocalDateTime.now().minusSeconds(1L), LocalDateTime.now().plusDays(1).plusHours(2));

        // when & then
        assertThat(lecture.isOverStartDateTime()).isTrue();
    }

    @Test
    void 특강_시작시간이_지나지_않은_경우_false_반환() {
        // given
        Lecture lecture = 자바_특강(1L, 10, LocalDateTime.now().plusSeconds(1L), LocalDateTime.now().plusDays(1).plusHours(2));

        // when & then
        assertThat(lecture.isOverStartDateTime()).isFalse();
    }

    @Test
    void 특강_제한인원이_모두_찬_경우_예외_발생() {
        // given
        Lecture lecture = 자바_특강(1L, 30, LocalDateTime.now().minusSeconds(1L), LocalDateTime.now().plusDays(1).plusHours(2));

        // when & then
        assertThatThrownBy(lecture::validateLimitedRegisterCount)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("신청 가능한 인원을 초과하였습니다.");
    }

}
