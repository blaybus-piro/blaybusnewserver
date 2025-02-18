package blaybus.domain.time.application.service;

import blaybus.domain.time.presentation.dto.req.DetailRequestDTO;
import blaybus.domain.time.presentation.dto.res.detailtime.DetailTimeResponse;
import blaybus.domain.time.presentation.dto.res.listtime.TimeResponse;

import java.util.List;

public interface TimeService {

    List<String> getTime();
}
