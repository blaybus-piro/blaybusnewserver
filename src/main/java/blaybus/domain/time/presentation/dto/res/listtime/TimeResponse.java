package blaybus.domain.time.presentation.dto.res.listtime;

import blaybus.domain.time.presentation.dto.res.listtime.timeconsulting.DesignerView;
import blaybus.domain.time.presentation.dto.res.listtime.timeconsulting.TimeConsulting;

public record TimeResponse(

        // 예약 시간, 대면 비대면 정보 있음
        TimeConsulting timeConsulting,

        // 미트 링크
        String googleLink,

        // 디자이너 이름 및 사진
        DesignerView designerView

) {
    public static TimeResponse of(TimeConsulting timeConsulting, String googleLink, DesignerView designerView) {
        return new TimeResponse(timeConsulting, googleLink, designerView);
    }

}
