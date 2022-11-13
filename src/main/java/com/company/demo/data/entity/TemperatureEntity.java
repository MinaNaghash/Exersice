package com.company.demo.data.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * This class is used as a temperature details, It has 1->N relation to Temperature Entity
 * @author mina.mn@gmail.com
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "TEMPERATURE_GROUP")
@SequenceGenerator(name = "temperatureGroupSequence", sequenceName = "TEMP_GROUP_SEQ",
        allocationSize = 1)
public class TemperatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temperatureGroupSequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * name of group
     */
    @Column(name = "CODE_LIST_CODE", nullable = false, unique = true)
    private String codeListCode;

    @OneToMany(mappedBy = "codeListCode", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<TemperatureDetailEntity> temperatures;
}
