package com.vacation.heidi.springboot.service;

import com.vacation.heidi.springboot.domain.vacations.Vacations;
import com.vacation.heidi.springboot.domain.vacations.VacationsRepository;
import com.vacation.heidi.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VacationsService {
    private final VacationsRepository vacationsRepository;

    @Transactional
    public Long save(VacationsSaveRequestDto vacationDto) {
        return vacationsRepository.save(vacationDto.toEntity()).getId();
    }

    @Transactional
    public Long cancel(Long id, VacationsCancelRequestDto requestDto) {
        Vacations vacations = vacationsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 휴가신청내역이 없습니다. id=" + id));

        vacations.cancel(requestDto.getCancelYN());

        return id;
    }

    @Transactional(readOnly = true)
    public VacationsResponseDto findById(Long id) {
        Vacations entity = vacationsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 휴가신청내역이 없습니다. id=" + id));

        return new VacationsResponseDto(entity);
    };

    @Transactional(readOnly = true)
    public List<VacationsListResponseDto> findByUid(Long uid) {
        return vacationsRepository.findByUid(uid).stream()
                .map(VacationsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VacationsListResponseDto> findAllDesc() {
        return vacationsRepository.findAllDesc().stream()
                .map(VacationsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
