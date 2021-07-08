package com.vacation.heidi.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacation.heidi.springboot.domain.vacations.Vacations;
import com.vacation.heidi.springboot.domain.vacations.VacationsRepository;
import com.vacation.heidi.springboot.web.dto.VacationsCancelRequestDto;
import com.vacation.heidi.springboot.web.dto.VacationsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// For mockMvc

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VacationsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private VacationsRepository vacationsRepository;

    @After
    public void tearDown() throws Exception {
        vacationsRepository.deleteAll();
    }

    @Test
    public void Vacations_신청하기() throws Exception {
        //given
        String uid = "1";
        String type = "연차";
        String startDate = "2021-05-15";
        String endDate = "2021-05-16";
        String comment = "개인사정으로 인한 연차";
        VacationsSaveRequestDto requestDto = VacationsSaveRequestDto.builder()
                .uid(uid)
                .type(type)
                .startDate(startDate)
                .endDate(endDate)
                .useDay(1)
                .comment(comment)
                .build();

        String url = "http://localhost:" + port + "/api/v1/vacations";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Vacations> all = vacationsRepository.findAll();
        assertThat(all.get(0).getUid()).isEqualTo(uid);
        assertThat(all.get(0).getType()).isEqualTo(type);
        assertThat(all.get(0).getStartDate()).isEqualTo(startDate);
        assertThat(all.get(0).getEndDate()).isEqualTo(endDate);
        assertThat(all.get(0).getUseDay()).isEqualTo(1);
        assertThat(all.get(0).getComment()).isEqualTo(comment);
    }

    @Test
    public void Vacations_취소하기() throws Exception {
        //given
        String uid = "1";
        String type = "연차";
        String startDate = "2021-05-15";
        String endDate = "2021-05-16";
        String comment = "개인사정으로 인한 연차";
        Vacations savedVacations = vacationsRepository.save(Vacations.builder()
                .uid(uid)
                .type(type)
                .startDate(startDate)
                .endDate(endDate)
                .useDay(1)
                .comment(comment)
                .build());

        Long cancelId = savedVacations.getId();

        VacationsCancelRequestDto requestDto = VacationsCancelRequestDto.builder()
                .cancelYN("Y")
                .build();

        String url = "http://localhost:" + port + "/api/v1/vacations/" + cancelId;

        HttpEntity<VacationsCancelRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Vacations> all = vacationsRepository.findAll();
        assertThat(all.get(0).getCancelYN()).isEqualTo("Y");
    }
}
