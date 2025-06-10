package finalmission.controller.dto.request;

public record CrewUpdateRequest(
        Long id,
        String name,
        String tier,
        String line
) {

}
