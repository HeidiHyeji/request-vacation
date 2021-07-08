package com.vacation.heidi.springboot.domain.vacations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserVacationRepository extends JpaRepository<UserVacation, Long> {

    @Query("SELECT uv FROM UserVacation uv ORDER BY uv.uid DESC")
    List<UserVacation> findAllDesc();

}