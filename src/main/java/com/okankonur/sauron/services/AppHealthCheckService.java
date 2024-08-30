package com.okankonur.sauron.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.okankonur.sauron.models.AppInfo;
import com.okankonur.sauron.models.AppServerConfig;;

@Service
public class AppHealthCheckService {

    private final RestTemplate restTemplate;

    public AppHealthCheckService() {
        this.restTemplate = new RestTemplate();
    }

    public List<AppInfo> checkAppHealth() throws StreamReadException, DatabindException, IOException {
       
        ObjectMapper objectMapper = new ObjectMapper();
        AppServerConfig appServerConfig = objectMapper.readValue(
                new ClassPathResource("app-server-config.json").getInputStream(),
                new TypeReference<AppServerConfig>() {}
        );

        List<AppInfo> appsWithStatus = new ArrayList<>();

        for (AppInfo currentApp : appServerConfig.getApps()) {
            try {
                ResponseEntity<String> response = restTemplate.exchange(currentApp.getUrl(), HttpMethod.GET, null, String.class);
                if(response.getStatusCode().is2xxSuccessful()){
                    currentApp.setStatus("UP");
                }
                else{
                    currentApp.setStatus("DOWN");
                }
            } catch (Exception e) {
                currentApp.setStatus("DOWN");
            }
            appsWithStatus.add(currentApp);
        }

        return appsWithStatus;
    }
}
