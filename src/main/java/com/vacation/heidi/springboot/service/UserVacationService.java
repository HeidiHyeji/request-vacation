package com.vacation.heidi.springboot.service;

import com.vacation.heidi.springboot.domain.vacations.UserVacation;
import com.vacation.heidi.springboot.domain.vacations.UserVacationRepository;
import com.vacation.heidi.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserVacationService {
    private final UserVacationRepository userVacationRepository;
    private final float defaultVacation = 15;

    @Transactional
    public UserVacation save(UserVacationSaveRequestDto userVacationDto) {
        return userVacationRepository.save(userVacationDto.toEntity());
    }

    @Transactional
    public UserVacation update(Long id, float useDay) {
        UserVacation userVacation = userVacationRepository.findById(id)
                .map(entity -> entity.update(entity.getRemainDays()-useDay))
                .orElse(new UserVacation(id,defaultVacation-useDay));
        return userVacationRepository.save(userVacation);
        /*UserVacation userVacation = userVacationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("휴가일이 없습니다. id=" + id));

        userVacation.update(userVacation.getRemainDays()-useDay);

        return id;*/
    }

    @Transactional(readOnly = true)
    public UserVacationResponseDto findById(Long id) {
        UserVacation entity = userVacationRepository.findById(id)
                .orElse(new UserVacation(id,defaultVacation));

        return new UserVacationResponseDto(entity);
    };

    @Transactional(readOnly = true)
    public List<UserVacationListResponseDto> findAllDesc() {
        return userVacationRepository.findAllDesc().stream()
                .map(UserVacationListResponseDto::new)
                .collect(Collectors.toList());
    }
}
