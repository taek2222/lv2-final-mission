package finalmission.service;

import finalmission.controller.dto.request.CivilWarScheduleCreateRequest;
import finalmission.controller.dto.request.CivilWarScheduleUpdateRequest;
import finalmission.controller.dto.response.CivilWarScheduleInfoResponse;
import finalmission.domain.CivilWarSchedule;
import finalmission.domain.repository.CivilWarScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CivilWarScheduleService {

    private final CivilWarScheduleRepository civilWarScheduleRepository;

    public CivilWarScheduleInfoResponse saveCivilWarSchedule(CivilWarScheduleCreateRequest request) {
        CivilWarSchedule saveCivilWarSchedule = request.toCivilWarSchedule();
        civilWarScheduleRepository.save(saveCivilWarSchedule);

        return new CivilWarScheduleInfoResponse(saveCivilWarSchedule);
    }

    public CivilWarScheduleInfoResponse updateCivilWarSchedule(CivilWarScheduleUpdateRequest request) {
        CivilWarSchedule civilWarSchedule = civilWarScheduleRepository.findById(request.id())
                .orElseThrow(RuntimeException::new);

        civilWarSchedule.update(
                request.startSchedule(),
                request.endSchedule()
        );

        return new CivilWarScheduleInfoResponse(civilWarSchedule);
    }

    public void deleteCivilWarSchedule(Long civilWarScheduleId) {
        civilWarScheduleRepository.deleteById(civilWarScheduleId);
    }
}
