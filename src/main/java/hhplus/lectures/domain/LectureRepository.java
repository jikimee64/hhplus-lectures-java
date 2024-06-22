package hhplus.lectures.domain;

public interface LectureRepository {
    Lecture save(Lecture lecture);
    Lecture findById(Long id);
}
