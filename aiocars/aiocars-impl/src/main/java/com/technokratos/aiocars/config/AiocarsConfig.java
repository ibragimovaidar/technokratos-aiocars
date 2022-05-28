package com.technokratos.aiocars.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.aiocars.constant.AiocarsConstants;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiocarsConfig {

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory(new PrecisionModel(), AiocarsConstants.POSTGIS_SRID);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
