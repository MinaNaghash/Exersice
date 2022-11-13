package com.company.demo.service.mapper;

import com.company.demo.data.entity.TemperatureDetailEntity;
import com.company.demo.data.entity.TemperatureEntity;
import com.company.demo.service.dto.TemperatureDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface TemperatureMapper {
    @Mapping(source = "codeListCode", target = "codeListCode", qualifiedByName = "temperatureToCodeListCodeString")
    TemperatureDto temperatureEntityToTemperatureDto(TemperatureDetailEntity entity);

    @Mapping(source = "codeListCode", target = "codeListCode", ignore = true)
    TemperatureDetailEntity temperatureDtoToTemperatureEntity(TemperatureDto dto);

    @Named("temperatureToCodeListCodeString")
    static String temperatureToCodeListCodeString(TemperatureEntity temperatureEntity) {
        return temperatureEntity.getCodeListCode();
    }

}
