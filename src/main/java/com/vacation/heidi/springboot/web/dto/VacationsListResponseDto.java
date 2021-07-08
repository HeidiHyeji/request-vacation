package com.vacation.heidi.springboot.web.dto;

import com.vacation.heidi.springboot.domain.vacations.Vacations;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VacationsListResponseDto {
    private Long id;
    private Long uid;
    private String type;
    private String startDate;
    private String endDate;
    private float useDay;
    private String comment;
    private String cancelYN;

    public VacationsListResponseDto(Vacations entity) {
        this.id = entity.getId();
        this.uid = entity.getUid();
        this.type = entity.getType();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.useDay = entity.getUseDay();
        this.comment = entity.getComment();
        this.cancelYN = entity.getCancelYN();
    }
}
