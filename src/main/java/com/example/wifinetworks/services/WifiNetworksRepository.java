package com.example.wifinetworks.services;

import com.example.wifinetworks.dm.WifiNetwork;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WifiNetworksRepository{

    private static final String REPOSITORY_FILE_LOCATION = "REPOSITORY_FILE_LOCATION";
    private static final String REPOSITORY_FILE = "networks.repo.file";
    private Map<Long, WifiNetwork> networksRepository = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){

        ObjectMapper jsonMapper = new ObjectMapper();
        Path repoFilePath = Paths.get(System.getenv(REPOSITORY_FILE_LOCATION), REPOSITORY_FILE);
        if(Files.exists(repoFilePath)) {
            try {
                String repoJson = new String(Files.readAllBytes(repoFilePath));
                networksRepository = jsonMapper.readValue(repoJson, new TypeReference<Map<Long, WifiNetwork>>() {});
            } catch (Exception e) {
                //do some logging here as I don't want to prevent the application from starting.
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateNetworks(WifiNetwork entity){
        networksRepository.put(entity.getId(), entity);
        saveNetworksRepository();
    }

    public Optional<WifiNetwork> getNetworkByID(Long id){
        return Optional.ofNullable(networksRepository.get(id));
    }

    private void saveNetworksRepository(){
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            String repoJson = jsonMapper.writeValueAsString(networksRepository);
            Path repoFilePath = Paths.get(System.getenv(REPOSITORY_FILE_LOCATION), REPOSITORY_FILE);
            Files.write(repoFilePath, repoJson.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Save to networks repository failed!!!");
        }
    }
}