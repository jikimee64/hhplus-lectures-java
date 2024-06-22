package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.Lecture;
import hhplus.lectures.domain.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;

    public Lecture save(Lecture lecture){
        return lectureJpaRepository.save(lecture);
    }

    @Override
    public Lecture findById(Long id) {
        return lectureJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));
    }

}
