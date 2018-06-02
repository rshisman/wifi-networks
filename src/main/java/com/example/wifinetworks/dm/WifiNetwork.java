package com.example.wifinetworks.dm;

import com.example.wifinetworks.controller.NetworkDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class WifiNetwork implements Serializable {

    private static final long serialVersionUID = 436933635106433534L;

    private Long id;
    private NetworkAuthType auth;
    private Double averageThroughput = 0d;
    private Collection<Device> devices = new HashSet<>();

    public WifiNetwork(){}

    public WifiNetwork(Long id, NetworkAuthType auth) {
        this.id = id;
        this.auth = auth;
    }

    public Long getId() {
        return id;
    }

    public Double getAverageThroughput() {
        return averageThroughput;
    }

    public void setAverageThroughput(Double averageThroughput) {
        this.averageThroughput = averageThroughput;
    }

    @JsonFormat()
    public NetworkAuthType getAuth() {
        return auth;
    }

    public void setAuth(NetworkAuthType auth) {
        this.auth = auth;
    }

    public Collection<Device> getDevices() {
        return devices;
    }

    public void setDevices(Collection<Device> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WifiNetwork that = (WifiNetwork) o;
        return Objects.equals(id, that.id) &&
                auth == that.auth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, auth);
    }

    @Override
    public String toString() {
        return "WifiNetwork{" +
                "id=" + id +
                ", auth=" + auth +
                ", averageThroughput=" + averageThroughput +
                ", devices=" + devices +
                '}';
    }

    public void addDevice(Device device){
        if(device != null){
            devices.add(device);
        }
    }

    private Double getAverageThroughputForDisplay(){
        return Math.round(averageThroughput * 100.0) / 100.0;
    }

    @JsonIgnore
    public NetworkDTO getNetworkDTO(){

        Set<String> deviceIDs = devices.stream().map(Device::getId).collect(Collectors.toSet());
        return new NetworkDTO(id, auth.name().toLowerCase(), getAverageThroughputForDisplay(), deviceIDs);
    }
}
