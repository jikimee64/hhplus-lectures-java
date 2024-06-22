package hhplus.lectures.controller;

import hhplus.lectures.application.LectureService;
import hhplus.lectures.application.dto.LectureApplyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/apply")
    public void apply(@RequestBody @Valid LectureApplyRequest request) {
        lectureService.apply(request.lectureId(), request.userId());
    }
}
