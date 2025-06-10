package finalmission.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private Line line;

    public void update(String name, Tier tier, Line line) {
        validateName(name);
        this.name = name;
        this.tier = tier;
        this.line = line;
    }

    private void validateName(String name) {
        if (name.length() < 2) {
            throw new IllegalArgumentException("크루 이름은 2글자 이상이어야 합니다.");
        }
    }

    public String getTier() {
        return tier.getName();
    }

    public String getLine() {
        return line.getLine();
    }
}
