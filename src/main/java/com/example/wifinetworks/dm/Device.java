package com.example.wifinetworks.dm;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class Device implements Serializable {

    private static final long serialVersionUID = 3558624474684426630L;

    private String id;
    private Long throughput = 0L;

    public Device(){}
    public Device(String id) {
        this.id = id;
    }

    public Device(String id, Long throughput) {
        this.id = id;
        this.throughput = throughput;
    }

    public String getId() {
        return id;
    }

    public Long getThroughput() {
        return throughput;
    }

    public void setThroughput(Long throughput) {
        this.throughput = throughput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", throughput=" + throughput +
                '}';
    }
}
