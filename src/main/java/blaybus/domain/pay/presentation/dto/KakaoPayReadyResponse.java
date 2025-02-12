package blaybus.domain.pay.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class KakaoPayReadyResponse {
    private String tid;
    private String next_redirect_pc_url;
    private String next_redirect_mobile_url;
    private Date created_at;

}
