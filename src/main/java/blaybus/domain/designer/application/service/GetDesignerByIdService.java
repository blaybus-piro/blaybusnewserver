package blaybus.domain.designer.application.service;

import blaybus.domain.designer.presentation.dto.response.DesignerResponseDTO;

public interface GetDesignerByIdService {
    DesignerResponseDTO execute(String id);
}

