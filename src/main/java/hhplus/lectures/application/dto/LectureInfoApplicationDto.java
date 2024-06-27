package hhplus.lectures.application.dto;

import hhplus.lectures.domain.LectureInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record LectureInfoApplicationDto(
        Long lectureId,
        String lectureName,
        Long lectureScheduleId,
        int limitedCount,
        int registeredCount,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
    public static List<LectureInfoApplicationDto> from(List<LectureInfo> lectureInfos) {
        return lectureInfos.stream()
                .map(LectureInfoApplicationDto::from)
                .collect(Collectors.toList());
    }

    public static LectureInfoApplicationDto from(LectureInfo lectureInfo) {
        return new LectureInfoApplicationDto(
                lectureInfo.getLectureId(),
                lectureInfo.getName(),
                lectureInfo.getLectureScheduleId(),
                lectureInfo.getLimitedCount(),
                lectureInfo.getRegisteredCount(),
                lectureInfo.getStartDateTime(),
                lectureInfo.getEndDateTime()
        );
    }
}
