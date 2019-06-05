package com.starvalley.iotserver.iot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


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

    /*@JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private Set<SensorData> sensorDatas = new HashSet<>();
*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "server_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Server server;

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

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }


    /*
    public Set<SensorData> getSensorDatas() {

        return sensorDatas;
    }

    public void setSensorDatas(Set<SensorData> sensorDatas) {
        this.sensorDatas = sensorDatas;
    }
    */


}