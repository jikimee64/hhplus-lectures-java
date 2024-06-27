package hhplus.lectures.controller.dto;

import hhplus.lectures.application.dto.LectureInfoApplicationDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record LectureSelectResponse(
        Long lectureId,
        String lectureName,
        Long lectureScheduleId,
        int limitedCount,
        int registeredCount,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {

    public static LectureSelectResponse from(LectureInfoApplicationDto lectureInfo) {
        return new LectureSelectResponse(
                lectureInfo.lectureId(),
                lectureInfo.lectureName(),
                lectureInfo.lectureScheduleId(),
                lectureInfo.limitedCount(),
                lectureInfo.registeredCount(),
                lectureInfo.startDateTime(),
                lectureInfo.endDateTime()
        );
    }

    public static List<LectureSelectResponse> from( List<LectureInfoApplicationDto>  lectureInfos) {
        return lectureInfos.stream()
                .map(LectureSelectResponse::from)
                .collect(Collectors.toList());
    }
}
