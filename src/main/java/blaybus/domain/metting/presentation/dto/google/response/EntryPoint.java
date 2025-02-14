package blaybus.domain.metting.presentation.dto.google.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryPoint {
    private String entryPointType; // video, phone, etc.
    private String uri;           // Meet 링크일 경우 "https://meet.google.com/..."
    // ...
}