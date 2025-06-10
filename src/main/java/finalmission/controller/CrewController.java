package finalmission.controller;

import finalmission.controller.dto.CrewCreateRequest;
import finalmission.controller.dto.CrewInfoResponse;
import finalmission.controller.dto.CrewUpdateRequest;
import finalmission.service.CrewService;
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
@RequestMapping("/v1/crew")
public class CrewController {

    private final CrewService crewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CrewInfoResponse createCrew(@RequestBody CrewCreateRequest request) {
        return crewService.saveCrew(request);
    }

    @PatchMapping
    public CrewInfoResponse updateCrew(@RequestBody CrewUpdateRequest request) {
        return crewService.updateCrew(request);
    }

    @DeleteMapping("/{crewId}")
    public void deleteCrew(@PathVariable Long crewId) {
        crewService.deleteCrew(crewId);
    }
}
