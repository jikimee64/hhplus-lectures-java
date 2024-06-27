package hhplus.lectures.controller;

import hhplus.lectures.application.LectureService;
import hhplus.lectures.application.dto.LectureInfoApplicationDto;
import hhplus.lectures.controller.dto.LectureApplyRequest;
import hhplus.lectures.controller.dto.LectureRegisterResultResponse;
import hhplus.lectures.controller.dto.LectureSelectResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/apply")
    public void apply(@RequestBody @Valid LectureApplyRequest request) {
        lectureService.apply(request.lectureScheduleId(), request.userId());
    }

    @GetMapping("/application/{lectureScheduleId}/{userId}")
    public LectureRegisterResultResponse selectUserAppliedForLecture(@PathVariable Long lectureScheduleId, @PathVariable Long userId) {
        return new LectureRegisterResultResponse(lectureService.hasUserAppliedForLecture(lectureScheduleId, userId));
    }

    @GetMapping
    public List<LectureSelectResponse> findLectures() {
        List<LectureInfoApplicationDto> lectureInfos = lectureService.findLectures();
        return LectureSelectResponse.from(lectureInfos);
    }
}
