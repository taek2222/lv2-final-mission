package finalmission.controller;

import finalmission.controller.dto.CrewCreateRequest;
import finalmission.controller.dto.CrewInfoResponse;
import finalmission.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/crew")
public class CrewController {

    private final CrewService crewService;

    @PostMapping
    public CrewInfoResponse createCrew(@RequestBody CrewCreateRequest request) {
        return crewService.saveCrew(request);
    }
}
