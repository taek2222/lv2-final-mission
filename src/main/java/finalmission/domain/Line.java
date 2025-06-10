package finalmission.domain;

import lombok.Getter;

@Getter
public enum Line {
    TOP("탑"),
    JUNGLE("정글"),
    MID("미드"),
    ADC("원딜"),
    SUPPORT("서폿");

    private final String line;

    Line(String line) {
        this.line = line;
    }
}
