package com.example.wifinetworks.dao;

import com.example.wifinetworks.dm.WifiNetwork;
import com.example.wifinetworks.services.WifiNetworksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WifiNetworksDao implements Dao<WifiNetwork> {

    private WifiNetworksRepository wifiNetworksRepository;

    @Autowired
    public WifiNetworksDao(WifiNetworksRepository wifiNetworksRepository) {
        this.wifiNetworksRepository = wifiNetworksRepository;
    }

    @Override
    public Optional<WifiNetwork> findById(Long id) {
        return wifiNetworksRepository.getNetworkByID(id);
    }

    @Override
    public boolean create(WifiNetwork entity) {
        wifiNetworksRepository.updateNetworks(entity);
        return true;
    }

    @Override
    public boolean update(WifiNetwork entity) {
        wifiNetworksRepository.updateNetworks(entity);
        return true;
    }
}
