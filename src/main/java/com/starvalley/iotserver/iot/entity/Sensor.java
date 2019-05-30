/**
 * Created by tommy on 2018-04-07.
 */
package com.starvalley.iotserver.iot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
public class Sensor {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String code;
    @NotBlank
    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @NotBlank
    Date createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private Set<SensorData> sensorDatas = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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