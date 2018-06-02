package com.example.wifinetworks.controller;

import com.example.wifinetworks.dm.NetworkAuthType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ConnectDeviceDTO implements Serializable {

    private static final long serialVersionUID = 4564001196835919054L;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("network_id")
    private Long networkId;

    private NetworkAuthType auth;

    public ConnectDeviceDTO(){}

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

    public NetworkAuthType getAuth() {
        return auth;
    }

    public void setAuth(NetworkAuthType auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "ConnectDeviceDTO{" +
                "deviceId='" + deviceId + '\'' +
                ", networkId=" + networkId +
                ", auth=" + auth +
                '}';
    }
}
