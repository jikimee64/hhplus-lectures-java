package hhplus.lectures.application;

import hhplus.lectures.IntegrationTest;
import hhplus.lectures.domain.Lecture;
import hhplus.lectures.domain.LectureRegistrationRepository;
import hhplus.lectures.domain.LectureRepository;
import hhplus.lectures.domain.LectureScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static hhplus.lectures.fixture.LectureFixture.자바_특강;
import static hhplus.lectures.fixture.LectureScheduleFixture.특강_스케줄;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OptimisticLectureServiceTest extends IntegrationTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureScheduleRepository lectureScheduleRepository;

    @Autowired
    private LectureRegistrationRepository lectureRegistrationRepository;

    @Test
    void 낙관적_락을_이용한_특강_신청_인원_제한_동시성_테스트() throws Exception {
        // given
        Lecture lecture = lectureRepository.save(
                자바_특강()
        );

        LocalDateTime now = LocalDateTime.now();
        lectureScheduleRepository.save(특강_스케줄(
                lecture.getId(),
                0,
                now.plusHours(1L),
                now.plusHours(2L)
        ));

        int userCount = 100;
        int lectureLimitCount = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(userCount);
        CountDownLatch countDownLatch = new CountDownLatch(userCount);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        // when
        for (int i = 1; i <= userCount; i++) {
            executorService.execute(() -> {
                try {
                    lectureService.apply(lecture.getId(), userCount);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    failCount.incrementAndGet();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        // then
        long registeredCount = lectureRegistrationRepository.count();
        assertThat(registeredCount).isEqualTo(lectureLimitCount);
    }
}
