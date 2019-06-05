package com.starvalley.iotserver.iot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by tommy on 2018-04-07.
 * Get the senssor info
 */

@Entity
public class SensorData extends AuditModel {
    @Id
    @GeneratedValue
    private Long Id;

    @JsonIgnore
    @ManyToOne
    private Sensor sensor;

    private Long value;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}