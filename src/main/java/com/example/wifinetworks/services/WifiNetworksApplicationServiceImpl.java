package com.example.wifinetworks.services;

import com.example.wifinetworks.dao.WifiNetworksDao;
import com.example.wifinetworks.dm.Device;
import com.example.wifinetworks.dm.WifiNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WifiNetworksApplicationServiceImpl implements WifiNetworksApplicationService {

    private WifiNetworksDao wifiNetworksDao;

    @Autowired
    public WifiNetworksApplicationServiceImpl(WifiNetworksDao wifiNetworksDao) {
        this.wifiNetworksDao = wifiNetworksDao;
    }

    @Override
    public Optional<WifiNetwork> findNetworkByID(Long id) {
        return wifiNetworksDao.findById(id);
    }

    /**
     * I assume that maintaining a dedicated devices table has no added value for now,
     * since the requirement does not include any actions or reports from the device point of view.
     */
    @Override
    public void connectDevice(WifiNetwork network, Device device) throws ConflictingRequestException{

        Optional<WifiNetwork> persistedNetwork = findNetworkByID(network.getId());
        if(!persistedNetwork.isPresent()) {
            network.addDevice(device);
            wifiNetworksDao.create(network);
            return;
        }

        WifiNetwork networkToUpdate = persistedNetwork.get();
        if(!networkToUpdate.getAuth().equals(network.getAuth())){
            throw new ConflictingRequestException("Network authentication type does not match the requested authentication type!");
        }

        if(networkToUpdate.getDevices().contains(device)){
            throw new ConflictingRequestException("Device is already connected to this network!");
        }
        networkToUpdate.addDevice(device);
        wifiNetworksDao.update(networkToUpdate);

    }

    @Override
    public void reportDeviceThroughput(Long networkId, Device device) throws ConflictingRequestException{

        Optional<WifiNetwork> persistedNetwork = findNetworkByID(networkId);
        if(!persistedNetwork.isPresent()){
            throw new ConflictingRequestException("Network does not exist!");
        }

        Optional<Device> deviceToUpdate = persistedNetwork.get().getDevices().stream().filter(x -> x.equals(device)).findAny();
        if(!deviceToUpdate.isPresent()){
            throw new ConflictingRequestException("The reporting device is not connected to this network!");
        }

        deviceToUpdate.get().setThroughput(device.getThroughput());
        Double averageThroughput = persistedNetwork.get().getDevices().stream().collect(Collectors.averagingLong(Device::getThroughput));
        WifiNetwork networkToUpdate = persistedNetwork.get();
        networkToUpdate.setAverageThroughput(averageThroughput);
        wifiNetworksDao.update(networkToUpdate);
    }
}
