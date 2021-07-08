package com.vacation.heidi.springboot.web;

import com.vacation.heidi.springboot.domain.vacations.UserVacation;
import com.vacation.heidi.springboot.service.UserVacationService;
import com.vacation.heidi.springboot.service.VacationsService;
import com.vacation.heidi.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VacationsApiController {

    private final VacationsService vacationsService;
    private final UserVacationService userVacationService;

    @PostMapping("/api/v1/vacations")
    public Long save(@RequestBody VacationsSaveRequestDto requestDto) {
        userVacationService.update(requestDto.getUid(),requestDto.getUseDay());
        return vacationsService.save(requestDto);
    }

    @PutMapping("/api/v1/vacations/{id}")
    public Long cancel(@PathVariable Long id, @RequestBody VacationsCancelRequestDto requestDto) {
        userVacationService.update(requestDto.getUid(),-(requestDto.getUseDay()));
        return vacationsService.cancel(id, requestDto);
    }

    @GetMapping("/api/v1/vacations/{id}")
    public VacationsResponseDto findById(@PathVariable Long id) {
        return vacationsService.findById(id);
    }

}
