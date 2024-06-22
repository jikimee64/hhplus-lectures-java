package hhplus.lectures.domain;

import java.util.List;

public interface LectureRepository {
    Lecture save(Lecture lecture);
    Lecture findById(Long id);
    List<Lecture> findAll();
}
