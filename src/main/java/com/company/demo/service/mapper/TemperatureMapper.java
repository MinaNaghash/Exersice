package com.company.demo.service.mapper;

import com.company.demo.data.entity.TemperatureDetailEntity;
import com.company.demo.service.dto.TemperatureDto;
import org.mapstruct.Mapper;

@Mapper
public interface TemperatureMapper {
    TemperatureDto temperatureEntityToTemperatureDto(TemperatureDetailEntity entity);
    TemperatureDetailEntity temperatureDtoToTemperatureEntity(TemperatureDto dto);

}
