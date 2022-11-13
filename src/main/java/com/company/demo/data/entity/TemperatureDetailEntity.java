package com.company.demo.data.entity;

import com.company.demo.common.enums.SourceEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class is used as a temperature details, It has N->1 relation to TemperatureGroup Entity
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "TEMPERATURE_DETAIL")
public class TemperatureDetailEntity implements Serializable {

    @Id
    @Column(name = "CODE", nullable = false,unique = true)
    private String code;

    /**
     * display name of temperature
     */
    @Column(name = "DISPLAY_VALUE", nullable = false)
    private String displayValue;

    /**
     * Long description and information for temperature
     */
    @Column(name="LONG_DESCRIPTION",nullable = false)
    private String longDescription;

    /**
     * start date of temperature
     */
    @Column(name="FROM_DATE",nullable = false)
    private LocalDate fromDate;

    /**
     * end date of temperature
     */
    @Column(name="TO_DATE",nullable = false)
    private LocalDate toDate;

    @Column(name = "SOURCE",nullable = false)
    private SourceEnum source;

    @Column(name = "SORTING_PRIORITY",nullable = false)
    private Integer sortingPriority;

    @JsonIgnore
    @ManyToOne(targetEntity = TemperatureEntity.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "Temperature_ID")
    private TemperatureEntity codeListCode;
}
