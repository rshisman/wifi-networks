package com.example.wifinetworks.services;


import com.example.wifinetworks.dm.Device;
import com.example.wifinetworks.dm.WifiNetwork;

import java.util.Optional;

public interface WifiNetworksApplicationService {

    Optional<WifiNetwork> findNetworkByID(Long id);

    void connectDevice(WifiNetwork network, Device device) throws Exception;

    void reportDeviceThroughput(Long networkId, Device device) throws Exception;
}
