package finalmission.domain;

import lombok.Getter;

@Getter
public enum Tier {
    IRON4("아이언4"),
    IRON3("아이언3"),
    IRON2("아이언2"),
    IRON1("아이언1"),

    BRONZE4("브론즈4"),
    BRONZE3("브론즈3"),
    BRONZE2("브론즈2"),
    BRONZE1("브론즈1"),

    SILVER4("실버4"),
    SILVER3("실버3"),
    SILVER2("실버2"),
    SILVER1("실버1"),

    GOLD4("골드4"),
    GOLD3("골드3"),
    GOLD2("골드2"),
    GOLD1("골드1"),

    PLATINUM4("플래티넘4"),
    PLATINUM3("플래티넘3"),
    PLATINUM2("플래티넘2"),
    PLATINUM1("플래티넘1"),

    DIAMOND4("다이아몬드4"),
    DIAMOND3("다이아몬드3"),
    DIAMOND2("다이아몬드2"),
    DIAMOND1("다이아몬드1"),

    MASTER("마스터"),
    GRANDMASTER("그랜드마스터"),
    CHALLENGER("챌린저");

    private final String name;

    Tier(String name) {
        this.name = name;
    }
}
