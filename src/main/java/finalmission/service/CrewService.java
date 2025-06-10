package finalmission.service;

import finalmission.controller.dto.CrewCreateRequest;
import finalmission.controller.dto.CrewInfoResponse;
import finalmission.domain.Crew;
import finalmission.domain.repository.CrewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrewService {

    private final CrewRepository crewRepository;

    public CrewInfoResponse saveCrew(CrewCreateRequest request) {
        Crew saveCrew = request.toCrew();
        crewRepository.save(saveCrew);

        return new CrewInfoResponse(saveCrew);
    }
}
