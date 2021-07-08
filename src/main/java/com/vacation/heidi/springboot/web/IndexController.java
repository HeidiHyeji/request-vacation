package com.vacation.heidi.springboot.web;

import com.vacation.heidi.springboot.config.auth.LoginUser;
import com.vacation.heidi.springboot.config.auth.dto.SessionUser;
import com.vacation.heidi.springboot.service.UserVacationService;
import com.vacation.heidi.springboot.service.VacationsService;
import com.vacation.heidi.springboot.web.dto.UserVacationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final VacationsService vacationsService;
    private final UserVacationService userVacationService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model,@LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("vacations", vacationsService.findByUid(user.getUid()));
            model.addAttribute("userVacation",userVacationService.findById(user.getUid()));
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/vacations/save")
    public String vacationsSave(Model model,@LoginUser SessionUser user) {
        UserVacationResponseDto userVacationDto = userVacationService.findById(user.getUid());
        if(userVacationDto.getRemainDays() <= 0 ){
            return "index";
        }
        model.addAttribute("userVacation",userVacationDto);
        return "vacations-save";
    }


}
