package com.vacation.heidi.springboot.domain.vacations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacationsRepository extends JpaRepository<Vacations, Long> {

    @Query("SELECT v FROM Vacations v Where v.cancelYN != 'Y' or v.cancelYN is null ORDER BY v.uid DESC")
    List<Vacations> findAllDesc();

    @Query("SELECT v FROM Vacations v Where (v.cancelYN != 'Y' or v.cancelYN is null) and v.uid= :uid ORDER BY v.uid DESC")
    List<Vacations> findByUid(@Param("uid") long uid);

}