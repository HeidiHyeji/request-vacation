package com.vacation.heidi.springboot.web;

import com.vacation.heidi.springboot.service.UserVacationService;
import com.vacation.heidi.springboot.web.dto.UserVacationListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SchedulerController {

    private final float defaultVacation = 15;

    @Autowired
    private UserVacationService userVacationService;

    @Scheduled(cron = "0 0 0 1 1 ?") //매년 1월1일 부여
    public void initVacationJob(){
        List<UserVacationListResponseDto> userVacationDto = userVacationService.findAllDesc();
        for(UserVacationListResponseDto dto : userVacationDto){
            userVacationService.update(dto.getUid(),-defaultVacation);
        }
    }
}
