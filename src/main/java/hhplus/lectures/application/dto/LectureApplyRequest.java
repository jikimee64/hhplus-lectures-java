package hhplus.lectures.application.dto;

import jakarta.validation.constraints.NotNull;

public record LectureApplyRequest(
        @NotNull
        Long userId,
        @NotNull
        Long lectureId
) {
}
