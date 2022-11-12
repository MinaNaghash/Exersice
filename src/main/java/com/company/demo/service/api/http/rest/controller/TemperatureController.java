package com.company.demo.service.api.http.rest.controller;

import com.company.demo.service.TemperatureService;
import com.company.demo.service.dto.ResponseMessage;
import com.company.demo.service.dto.TemperatureDto;
import com.company.demo.service.utility.CSVHelper;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/***
 * This class used as a controller to fetch data from temperature services
 * @author mina.mn@gmail.com
 */
@RestController
@RequestMapping("/api/web/temperature/v1")
@Api(value = "controller of for temperature")
@Log4j2
public class TemperatureController {
    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    /**
     * Used to upload temperature file for UI
     * @param file base on temperature file format
     * @return message base on upload result
     */
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {

                List<TemperatureDto> temperatureDtos = CSVHelper.csvToTemperatures(file.getInputStream());
                temperatureService.createTemperature(temperatureDtos);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    /**
     * Used to fetch all temperature data from DBs.
     * @return list of temperatures if exist in database.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<TemperatureDto>> getAllTemperatures() {
        try {
            List<TemperatureDto> temperatures = temperatureService.findAll();

            if (temperatures.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(temperatures, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * used to delete all temperature data from database
     * @return message base on delete result.
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseMessage> deleteAllTemperatures() {
        try {
             temperatureService.deleteTemperature();

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("There was a failure in delete"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Used to return temperature data based on code
     * @param code
     * @return temperatureDto if exist with request code.
     */
    @PostMapping(value = "/findAllByCode")
    public ResponseEntity<TemperatureDto> findAllTemperature(@PathVariable String code) {
        TemperatureDto temperatures = temperatureService.findByCode(code);
        if(temperatures == null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(temperatures, HttpStatus.OK);
    }

}
