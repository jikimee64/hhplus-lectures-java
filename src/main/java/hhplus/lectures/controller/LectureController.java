package hhplus.lectures.controller;

import hhplus.lectures.application.LectureService;
import hhplus.lectures.application.dto.LectureApplyRequest;
import hhplus.lectures.application.dto.LectureRegisterResultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/apply")
    public void apply(@RequestBody @Valid LectureApplyRequest request) {
        lectureService.apply(request.lectureId(), request.userId());
    }

    // 강의 고유값도 Path Variable로 받도록 변경
    @PostMapping("/application/{lectureId}/{userId}")
    public LectureRegisterResultResponse selectUserAppliedForLecture(@PathVariable Long lectureId, @PathVariable Long userId) {
        return new LectureRegisterResultResponse(lectureService.hasUserAppliedForLecture(lectureId, userId));
    }
}
