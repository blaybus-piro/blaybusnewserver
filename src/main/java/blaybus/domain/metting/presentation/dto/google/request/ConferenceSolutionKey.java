package blaybus.domain.metting.presentation.dto.google.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceSolutionKey {
    // API가 요구하는 필드 이름과 대소문자를 맞춤
    @JsonProperty("type")
    private String type;
}