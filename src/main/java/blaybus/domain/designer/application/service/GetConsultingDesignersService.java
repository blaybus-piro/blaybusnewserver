package blaybus.domain.designer.application.service;

import blaybus.domain.designer.presentation.dto.response.DesignerResponseDTO;

import java.util.List;

public interface GetConsultingDesignersService {
    List<DesignerResponseDTO> execute(String sortOrder);
}

