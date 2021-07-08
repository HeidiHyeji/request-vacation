package com.vacation.heidi.springboot.web.dto;

import com.vacation.heidi.springboot.domain.vacations.UserVacation;
import lombok.Getter;

@Getter
public class UserVacationResponseDto {

    private Long uid;//사용자키값
    private float remainDays;
    public UserVacationResponseDto(UserVacation entity) {
        this.uid = entity.getUid();
        this.remainDays = entity.getRemainDays();
    }
}
