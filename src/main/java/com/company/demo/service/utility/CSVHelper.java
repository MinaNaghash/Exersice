package com.company.demo.service.utility;

import com.company.demo.common.enums.SourceEnum;
import com.company.demo.service.dto.TemperatureDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    private final static String TYPE = "text/csv";


    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<TemperatureDto> csvToTemperatures(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<TemperatureDto> temperatures = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                TemperatureDto temperatureDto = new TemperatureDto();
                temperatureDto.setSource(SourceEnum.ZIP);
                temperatureDto.setCodeListCode(csvRecord.get(1));
                temperatureDto.setCode(csvRecord.get(2));
                temperatureDto.setDisplayValue(csvRecord.get(3));
                temperatureDto.setLongDescription(csvRecord.get(4));
                temperatureDto.setFromDate(LocalDate.parse(csvRecord.get(5)));
                temperatureDto.setToDate(LocalDate.parse(csvRecord.get(6)));
                temperatureDto.setSortingPriority(Integer.valueOf(csvRecord.get(7)));
                temperatures.add(temperatureDto);
            }

            return temperatures;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

}