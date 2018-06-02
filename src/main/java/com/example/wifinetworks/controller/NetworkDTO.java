package com.example.wifinetworks.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Collection;

@JsonPropertyOrder({ "id", "auth", "averageThroughput", "devices"})
public class NetworkDTO implements Serializable {

    private static final long serialVersionUID = 3133685242528333520L;

    private Long id;
    private String auth;

    @JsonProperty("avg_throughput")
    private Double averageThroughput = 0d;

    private Collection<String> devices;

    public NetworkDTO(){}
    public NetworkDTO(Long id, String auth, Double averageThroughput, Collection<String> devices) {
        this.id = id;
        this.auth = auth;
        this.averageThroughput = averageThroughput;
        this.devices = devices;
    }

    public Long getId() {
        return id;
    }

    public String getAuth() {
        return auth;
    }

    public Double getAverageThroughput() {
        return averageThroughput;
    }

    public Collection<String> getDevices() {
        return devices;
    }

    @Override
    public String toString() {
        return "NetworkDTO{" +
                "id=" + id +
                ", auth=" + auth +
                ", averageThroughput=" + averageThroughput +
                ", devices=" + devices +
                '}';
    }
}
