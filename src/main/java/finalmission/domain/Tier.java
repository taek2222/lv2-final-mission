package finalmission.domain;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Tier {
    IRON4("아이언4", 1),
    IRON3("아이언3", 2),
    IRON2("아이언2", 3),
    IRON1("아이언1", 4),

    BRONZE4("브론즈4", 5),
    BRONZE3("브론즈3", 6),
    BRONZE2("브론즈2", 7),
    BRONZE1("브론즈1", 8),

    SILVER4("실버4", 9),
    SILVER3("실버3", 10),
    SILVER2("실버2", 11),
    SILVER1("실버1", 12),

    GOLD4("골드4", 13),
    GOLD3("골드3", 14),
    GOLD2("골드2", 15),
    GOLD1("골드1", 16),

    PLATINUM4("플래티넘4", 17),
    PLATINUM3("플래티넘3", 18),
    PLATINUM2("플래티넘2", 19),
    PLATINUM1("플래티넘1", 20),

    DIAMOND4("다이아몬드4", 21),
    DIAMOND3("다이아몬드3", 22),
    DIAMOND2("다이아몬드2", 23),
    DIAMOND1("다이아몬드1", 24),

    MASTER("마스터", 25),
    GRANDMASTER("그랜드마스터", 26),
    CHALLENGER("챌린저", 27);

    private final String name;
    private final int weight;

    Tier(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public static Tier findByWeight(int weight) {
        return Arrays.stream(values())
                .filter(tier -> tier.weight == weight)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
