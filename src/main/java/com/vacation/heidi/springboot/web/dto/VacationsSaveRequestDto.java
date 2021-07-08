package com.vacation.heidi.springboot.web.dto;

import com.vacation.heidi.springboot.domain.vacations.Vacations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VacationsSaveRequestDto {
    private Long uid;
    private String type;
    private String startDate;
    private String endDate;
    private float useDay;
    private String comment;

    @Builder
    public VacationsSaveRequestDto(Long uid, String type, String startDate, String endDate, float useDay, String comment) {
        this.uid = uid;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.useDay = useDay;
        this.comment = comment;
    }

    public Vacations toEntity() {
        return Vacations.builder()
                .uid(uid)
                .type(type)
                .startDate(startDate)
                .endDate(endDate)
                .useDay(useDay)
                .comment(comment)
                .build();
    }

}
