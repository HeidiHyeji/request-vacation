package com.vacation.heidi.springboot.web.dto;

import com.vacation.heidi.springboot.domain.vacations.UserVacation;
import com.vacation.heidi.springboot.domain.vacations.Vacations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVacationUpdateRequestDto {
    private Long uid;
    private float remainDays;

    @Builder
    public UserVacationUpdateRequestDto(Long uid, float remainDays) {
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
