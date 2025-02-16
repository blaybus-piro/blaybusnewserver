package blaybus.domain.map.application.service.impl;

import blaybus.domain.map.application.exception.GeocodeAPIConnectException;
import blaybus.domain.map.application.exception.MultiResponseException;
import blaybus.domain.map.application.service.PositionCreateService;
import blaybus.domain.map.domain.entity.Position;
import blaybus.domain.map.domain.repository.PositionRepository;
import blaybus.domain.map.infra.feignclient.GeocodingFeignClient;
import blaybus.domain.map.presentation.dto.response.GeocodingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class PositionCreateServiceImpl implements PositionCreateService {

    private final GeocodingFeignClient geocodingFeignClient;
    private final PositionRepository positionRepository;
    @Value("${google.map.api-key}")
    private String apiKey;

    @Override
    public void createPosition(String addressName) {
        String encodedAddressName = URLEncoder.encode(addressName, StandardCharsets.UTF_8);
        GeocodingResponse geocodingResponse = geocodingFeignClient.geocode(encodedAddressName, apiKey);
        if(geocodingResponse.results() == null) {
            throw new GeocodeAPIConnectException();
        }
        if((long) geocodingResponse.results().size() != 1) {
            throw new MultiResponseException();
        }
        Position position = Position.builder()
                .name(addressName)
                .latitude(geocodingResponse.results().get(0).geometry().location().lat())
                .longitude(geocodingResponse.results().get(0).geometry().location().lng())
                .build();

        positionRepository.save(position);
    }

}
