package blaybus.domain.time.presentation.dto.res.detailtime;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.time.presentation.dto.res.detailtime.detail.ConsultingDetail;
import blaybus.domain.time.presentation.dto.res.detailtime.detail.DesignerDetail;
import blaybus.domain.time.presentation.dto.res.detailtime.detail.UserDetail;
import blaybus.domain.time.presentation.dto.res.listtime.TimeResponse;
import blaybus.domain.time.presentation.dto.res.listtime.timeconsulting.DesignerView;
import blaybus.domain.time.presentation.dto.res.listtime.timeconsulting.TimeConsulting;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;

import java.time.LocalDateTime;

public record DetailTimeResponse (
        UserDetail userDetail,

        DesignerDetail designerDetail,

        ConsultingDetail consultingDetail,

        String meetLink,

        LocalDateTime startTime
){
    public static DetailTimeResponse of(UserDetail userDetail, DesignerDetail designerDetail, ConsultingDetail consultingDetail, String meetLink, LocalDateTime startTime) {
        return new DetailTimeResponse(userDetail, designerDetail, consultingDetail, meetLink, startTime);
    }
}
