package com.vacation.heidi.springboot.web.dto;

import com.vacation.heidi.springboot.domain.vacations.UserVacation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVacationSaveRequestDto {
    private Long uid;//사용자키값
    private float remainDays;

    @Builder
    public UserVacationSaveRequestDto(Long uid, float remainDays) {
        this.uid = uid;
        this.remainDays = remainDays;
    }

    public UserVacation toEntity() {
        return UserVacation.builder()
                .uid(uid)
                .remainDays(remainDays)
                .build();
    }

}
