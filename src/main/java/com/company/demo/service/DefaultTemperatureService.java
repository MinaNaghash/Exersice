package com.company.demo.service;

import com.company.demo.data.entity.TemperatureDetailEntity;
import com.company.demo.data.entity.TemperatureEntity;
import com.company.demo.data.repository.TemperatureDetailRepository;
import com.company.demo.data.repository.TemperatureRepository;
import com.company.demo.service.dto.TemperatureDto;
import com.company.demo.service.mapper.TemperatureMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * DefaultTemperatureService, is used for crud operation on temperature class.
 *
 * @author mina.mn@gmail.com
 */
@Service
@Transactional
@Log4j2
public class DefaultTemperatureService implements TemperatureService {

    private final TemperatureDetailRepository temperatureDetailRepository;

    private final TemperatureRepository temperatureRepository;
    private final TemperatureMapper temperatureMapper;

    public DefaultTemperatureService(TemperatureDetailRepository temperatureDetailRepository,
                                     TemperatureMapper temperatureMapper,
                                     TemperatureRepository temperatureRepository) {
        this.temperatureDetailRepository = temperatureDetailRepository;
        this.temperatureMapper = temperatureMapper;
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public List<TemperatureDto> findAll() {
        List<TemperatureDetailEntity> temperatures = temperatureDetailRepository.findAll();
        return temperatures.stream().map((s) -> temperatureMapper.temperatureEntityToTemperatureDto(s)).
                                    collect(Collectors.toList());
    }

    @Override
    public TemperatureDto findByCode(String code) {
        Optional<TemperatureDetailEntity> temperature = temperatureDetailRepository.findByCode(code);
        if(temperature.isEmpty()){
            return null;
        }
        return  temperatureMapper.temperatureEntityToTemperatureDto(temperature.get());
    }

    @Override
    public void createTemperature(List<TemperatureDto> temperatures) {
        Map<String, List<TemperatureDto>> categoriesData = temperatures.stream().collect(Collectors.groupingBy(TemperatureDto::getCodeListCode));
        for (String type : categoriesData.keySet()) {
            Optional<TemperatureEntity> codeListCode = temperatureRepository.findByCodeList(type);
            if(codeListCode.isEmpty()){
                TemperatureEntity temperatureEntity = new TemperatureEntity();
                temperatureEntity.setCodeListCode(type);
                temperatureRepository.save(temperatureEntity);
            }

            Set<TemperatureDetailEntity> temperatureDetailEntities = categoriesData.get(type).stream().map((t) -> temperatureMapper.temperatureDtoToTemperatureEntity(t)).collect(Collectors.toSet());
            temperatureDetailRepository.saveAll(temperatureDetailEntities);
        }

    }

    @Override
    public void deleteTemperature() {
        temperatureDetailRepository.deleteAll();
    }
}
