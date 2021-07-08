package com.vacation.heidi.springboot.domain.vacations;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VacationsRepositoryTest {

    @Autowired
    VacationsRepository vacationsRepository;

    @After
    public void cleanup() {
        vacationsRepository.deleteAll();
    }

    @Test
    public void 연차신청하고불러오기() {
        //given
        String uid = "1";
        String type = "연차";
        String startDate = "2021-05-15";
        String endDate = "2021-05-16";
        String comment = "개인사정으로 인한 연차";


        vacationsRepository.save(Vacations.builder()
                .uid(uid)
                .type(type)
                .startDate(startDate)
                .endDate(endDate)
                .useDay(1)
                .comment(comment)
                .build());

        //when
        List<Vacations> vacationsList = vacationsRepository.findAll();

        //then
        Vacations vacations = vacationsList.get(0);
        assertThat(vacations.getUid()).isEqualTo(uid);
        assertThat(vacations.getType()).isEqualTo(type);
        assertThat(vacations.getStartDate()).isEqualTo(startDate);
        assertThat(vacations.getEndDate()).isEqualTo(endDate);
        assertThat(vacations.getUseDay()).isEqualTo(1);
        assertThat(vacations.getComment()).isEqualTo(comment);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        String uid = "1";
        String type = "연차";
        String startDate = "2021-05-15";
        String endDate = "2021-05-16";
        String comment = "개인사정으로 인한 연차";

        LocalDateTime now = LocalDateTime.of(2021, 5, 16, 0, 0, 0);
        vacationsRepository.save(Vacations.builder()
                .uid(uid)
                .type(type)
                .startDate(startDate)
                .endDate(endDate)
                .useDay(1)
                .comment(comment)
                .build());
        //when
        List<Vacations> vacationsList = vacationsRepository.findAll();

        //then
        Vacations vacations = vacationsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + vacations.getCreatedDate() + ", modifiedDate=" + vacations.getModifiedDate());

        assertThat(vacations.getCreatedDate()).isAfter(now);
        assertThat(vacations.getModifiedDate()).isAfter(now);
    }

}
