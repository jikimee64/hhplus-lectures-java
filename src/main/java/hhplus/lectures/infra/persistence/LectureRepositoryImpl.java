package hhplus.lectures.infra.persistence;

import hhplus.lectures.domain.Lecture;
import hhplus.lectures.domain.LectureInfo;
import hhplus.lectures.domain.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;

    public Lecture save(Lecture lecture){
        return lectureJpaRepository.save(lecture);
    }

    @Override
    public List<LectureInfo> findAllLectureInfo() {
        return lectureJpaRepository.findAllLectureInfo();
    }

}
