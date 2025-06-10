package finalmission.controller.dto;

public record CrewUpdateRequest(
        Long id,
        String name,
        String tier,
        String line
) {
    
}
