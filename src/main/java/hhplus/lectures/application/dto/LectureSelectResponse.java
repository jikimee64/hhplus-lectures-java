package hhplus.lectures.application.dto;

import hhplus.lectures.domain.Lecture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record LectureSelectResponse(
        Long id,
        int limitedCount,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {


    public static LectureSelectResponse from(Lecture lecture) {
        return new LectureSelectResponse(
                lecture.getId(),
                lecture.getLimitedCount(),
                lecture.getStartDateTime(),
                lecture.getEndDateTime()
        );
    }

    public static List<LectureSelectResponse> from(List<Lecture> lectures) {
        return lectures.stream()
                .map(LectureSelectResponse::from)
                .collect(Collectors.toList());
    }
}
