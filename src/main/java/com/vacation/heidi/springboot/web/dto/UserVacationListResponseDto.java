package com.vacation.heidi.springboot.web.dto;

import com.vacation.heidi.springboot.domain.vacations.UserVacation;
import com.vacation.heidi.springboot.domain.vacations.Vacations;
import lombok.Getter;

@Getter
public class UserVacationListResponseDto {
    private Long uid;
    private float remainDays;

    public UserVacationListResponseDto(UserVacation entity) {
        this.uid = entity.getUid();
        this.remainDays = entity.getRemainDays();
    }
}
