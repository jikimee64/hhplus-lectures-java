package hhplus.lectures.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LectureRegistrationTest {

    @Test
    void lectureId와_userId가_같은지_비교() {
        // given
        long userId = 1L;
        long lectureId = 1L;

        LectureRegistration lectureRegistration = new LectureRegistration(lectureId, userId);
        assertThat(lectureRegistration.isSameBy(lectureId, userId)).isTrue();
    }

}
