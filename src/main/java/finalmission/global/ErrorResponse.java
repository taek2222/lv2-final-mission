package finalmission.global;

public record ErrorResponse(
        String message,
        int errorCode
) {
}
