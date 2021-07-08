package com.vacation.heidi.springboot.domain.vacations;

import com.vacation.heidi.springboot.domain.BaseTimeEntity;
import com.vacation.heidi.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserVacation extends BaseTimeEntity {
    @Id
    private Long uid;

    @Column(nullable = false)
    private float remainDays; //남은휴가갯수

    @Builder
    public UserVacation(Long uid, float remainDays) {
        this.uid = uid;
        this.remainDays = remainDays;
    }
    public UserVacation update(float remainDays) {
        this.remainDays = remainDays;
        return this;
    }

}
