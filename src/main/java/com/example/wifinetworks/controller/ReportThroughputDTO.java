package com.example.wifinetworks.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ReportThroughputDTO implements Serializable {

    private static final long serialVersionUID = 1003800788074353507L;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("network_id")
    private Long networkId;

    private Long throughput;

    public ReportThroughputDTO(){}

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
    }

    public Long getThroughput() {
        return throughput;
    }

    public void setThroughput(Long throughput) {
        this.throughput = throughput;
    }

    @Override
    public String toString() {
        return "ReportThroughputDTO{" +
                "deviceId='" + deviceId + '\'' +
                ", networkId=" + networkId +
                ", throughput=" + throughput +
                '}';
    }
}
