package finalmission.controller;

public record MemberLoginRequest(
        String email,
        String password
) {
}
