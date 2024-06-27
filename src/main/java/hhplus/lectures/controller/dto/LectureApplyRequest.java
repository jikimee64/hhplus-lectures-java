package hhplus.lectures.controller.dto;

import jakarta.validation.constraints.NotNull;

public record LectureApplyRequest(
        @NotNull
        Long userId,
        @NotNull
        Long lectureScheduleId
) {
}
