package finalmission.controller;

import finalmission.controller.dto.request.CivilWarScheduleCreateRequest;
import finalmission.controller.dto.request.CivilWarScheduleUpdateRequest;
import finalmission.controller.dto.response.CivilWarScheduleInfoResponse;
import finalmission.service.CivilWarScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/civil-war-date")
public class CivilWarScheduleController {

    private final CivilWarScheduleService civilWarScheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CivilWarScheduleInfoResponse createCivilWarSchedule(@RequestBody CivilWarScheduleCreateRequest request) {
        return civilWarScheduleService.saveCivilWarSchedule(request);
    }

    @PatchMapping
    public CivilWarScheduleInfoResponse updateCivilWarSchedule(@RequestBody CivilWarScheduleUpdateRequest request) {
        return civilWarScheduleService.updateCivilWarSchedule(request);
    }

    @DeleteMapping("/{civilWarScheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCivilWarSchedule(@PathVariable Long civilWarScheduleId) {
        civilWarScheduleService.deleteCivilWarSchedule(civilWarScheduleId);
    }
}
