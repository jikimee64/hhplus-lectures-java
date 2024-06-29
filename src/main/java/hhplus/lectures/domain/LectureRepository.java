package hhplus.lectures.domain;

import java.util.List;

public interface LectureRepository {
    Lecture save(Lecture lecture);
    List<LectureInfo> findAllLectureInfo();
}
