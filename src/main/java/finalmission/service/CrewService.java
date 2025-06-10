package finalmission.service;

import finalmission.controller.dto.request.CrewCreateRequest;
import finalmission.controller.dto.request.CrewUpdateRequest;
import finalmission.controller.dto.response.CrewInfoResponse;
import finalmission.domain.Crew;
import finalmission.domain.Line;
import finalmission.domain.Tier;
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

    public CrewInfoResponse updateCrew(CrewUpdateRequest request) {
        Crew crew = crewRepository.findById(request.id())
                .orElseThrow(RuntimeException::new);

        crew.update(
                request.name(),
                Tier.valueOf(request.tier()),
                Line.valueOf(request.line())
        );

        return new CrewInfoResponse(crew);
    }

    public void deleteCrew(Long crewId) {
        crewRepository.deleteById(crewId);
    }
}
