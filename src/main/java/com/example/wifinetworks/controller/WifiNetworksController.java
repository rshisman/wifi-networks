package com.example.wifinetworks.controller;

import com.example.wifinetworks.dm.Device;
import com.example.wifinetworks.dm.WifiNetwork;
import com.example.wifinetworks.services.BadRequestException;
import com.example.wifinetworks.services.ConflictingRequestException;
import com.example.wifinetworks.services.WifiNetworksApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="api/network")
public class WifiNetworksController {

    private WifiNetworksApplicationService wifiNetworksApplicationService;

    @Autowired
    public WifiNetworksController(WifiNetworksApplicationService wifiNetworksApplicationService) {
        this.wifiNetworksApplicationService = wifiNetworksApplicationService;
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<NetworkDTO> getNetworkById(@RequestParam("id") Long id){

        Optional<WifiNetwork> fetchedNetwork = wifiNetworksApplicationService.findNetworkByID(id);
        if((fetchedNetwork).isPresent()){
            return new ResponseEntity<>(fetchedNetwork.get().getNetworkDTO(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new NetworkDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/connect", consumes = "application/json")
    public ResponseEntity<String> connectDevice(@RequestBody ConnectDeviceDTO connectDeviceDTO){

        WifiNetwork requestedNetwork = new WifiNetwork(connectDeviceDTO.getNetworkId(), connectDeviceDTO.getAuth());
        Device deviceToConnect = new Device(connectDeviceDTO.getDeviceId());

        try {
            wifiNetworksApplicationService.connectDevice(requestedNetwork, deviceToConnect);
        }catch (ConflictingRequestException cre){
            return new ResponseEntity<>(cre.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Device was successfully connected.", HttpStatus.OK);
    }

    @PostMapping(value = "/report", consumes = "application/json")
    public ResponseEntity<String> reportThroughput(@RequestBody ReportThroughputDTO reportThroughputDTO){

        Device reportingDevice = new Device(reportThroughputDTO.getDeviceId(), reportThroughputDTO.getThroughput());

        try {
            wifiNetworksApplicationService.reportDeviceThroughput(reportThroughputDTO.getNetworkId(), reportingDevice);
        }catch (BadRequestException bre){
            return new ResponseEntity<>(bre.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (ConflictingRequestException cre){
            return new ResponseEntity<>(cre.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Device throughput was successfully updated.", HttpStatus.OK);
    }

}