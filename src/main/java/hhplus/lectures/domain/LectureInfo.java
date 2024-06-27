package hhplus.lectures.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LectureInfo {
    private Long lectureScheduleId;
    private Long lectureId;
    private String name;
    private int limitedCount;
    private int registeredCount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
