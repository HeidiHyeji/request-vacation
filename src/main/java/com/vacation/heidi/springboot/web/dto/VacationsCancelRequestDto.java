package com.vacation.heidi.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VacationsCancelRequestDto {
    private Long uid;
    private float useDay;
    private String cancelYN;

    @Builder
    public VacationsCancelRequestDto(Long uid, float useDay, String cancelYN) {
        this.uid = uid;
        this.useDay = useDay;
        this.cancelYN = cancelYN;
    }
}
