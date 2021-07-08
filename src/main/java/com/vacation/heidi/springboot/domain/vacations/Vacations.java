package com.vacation.heidi.springboot.domain.vacations;

import com.vacation.heidi.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Vacations extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long uid;//사용자키값

    @Column(length = 10, nullable = false)
    private String type;//휴가구분:연차,반차,반반차

    @Column(nullable = false)
    private String startDate; //휴가시작일

    private String endDate; //휴가종료일

    @Column(nullable = false)
    private float useDay; //휴가사용일수

    @Column(columnDefinition = "TEXT")
    private String comment; //코멘트

    private String cancelYN;//휴가 취소여부

    @Builder
    public Vacations(Long uid,String type, String startDate, String endDate, float useDay, String comment) {
        this.uid = uid;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.useDay = useDay;
        this.comment = comment;
    }
    public void cancel(String cancelYN) {
        this.cancelYN = cancelYN;
    }

}
