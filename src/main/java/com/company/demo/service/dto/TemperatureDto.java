package com.company.demo.service.dto;

import com.company.demo.common.enums.SourceEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TemperatureDto implements Serializable {
    private String code;
    private String displayValue;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private SourceEnum source;
    private Integer sortingPriority;
    private String codeListCode;
}
