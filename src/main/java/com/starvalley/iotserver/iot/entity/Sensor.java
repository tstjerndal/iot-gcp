/**
 * Created by tommy on 2018-04-07.
 */
package com.starvalley.iotserver.iot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sensor  extends AuditModel{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String code;
    @NotBlank
    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private Set<SensorData> sensorDatas = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SensorData> getSensorDatas() {
        return sensorDatas;
    }

    public void setSensorDatas(Set<SensorData> sensorDatas) {
        this.sensorDatas = sensorDatas;
    }

}