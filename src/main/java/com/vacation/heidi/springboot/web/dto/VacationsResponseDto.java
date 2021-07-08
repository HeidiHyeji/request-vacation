package com.vacation.heidi.springboot.web.dto;

import com.vacation.heidi.springboot.domain.vacations.Vacations;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class VacationsResponseDto {

    private Long id;
    private Long uid;//사용자키값
    private String type;//휴가구분:연차,반차,반반차
    private String startDate; //휴가시작일
    private String endDate; //휴가종료일
    private float useDay; //휴가사용일수
    private String comment; //코멘트
    private String cancelYN;//휴가 취소여부

    public VacationsResponseDto(Vacations entity) {
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
