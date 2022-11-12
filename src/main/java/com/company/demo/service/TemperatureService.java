package com.company.demo.service;

import com.company.demo.service.dto.TemperatureDto;

import java.util.List;

/**
 * This is a service class for temperature, can be used for calling crud operations.
 * @author mina.mn@gmail.com
 */
public interface TemperatureService {

    /**
     * is used to get all temperatures in database.
     * @return list of temperatures
     */
    List<TemperatureDto> findAll();

    /**
     * is used to get a single temperature base on single unique code
     * @param code is a unique value to get a temperatures
     * @return a temperature base on unique given code
     */
    TemperatureDto findByCode(String code);

    /**
     * create a temperature in the database
     * @param temperatures
     */
    void createTemperature(List<TemperatureDto> temperatures);

    /**
     * delete all temperatures data from database
     */
    void deleteTemperature();
}
