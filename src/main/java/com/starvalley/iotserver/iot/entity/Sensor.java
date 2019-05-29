/**
 * Created by tommy on 2018-04-07.
 */
package com.starvalley.iotserver.iot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Sensor {
    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String name;

    private String serveName;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private Set<SensorData> sensorDatas = new HashSet<>();

    private Sensor() { } // JPA only

    public Sensor(final String name, final String code, final String serveName) {
        this.name = name;
        this.code = code;
        this.serveName = serveName;
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

    public String getServeName() {
        return serveName;
    }

    public void setServeName(String serveName) {
        this.serveName = serveName;
    }
}