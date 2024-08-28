package com.okankonur.sauron.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServerHealthCheckService {

    private final RestTemplate restTemplate;

    public ServerHealthCheckService() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, String> checkServersHealth() {
        Map<String, String> serverHealthStatuses = new HashMap<>();


        String[] servers = {
            "https://httpbin.org/status/200",
            "https://httpbin.org/status/500",
            "https://jsonplaceholder.typicode.com/posts",
            "https://reqres.in/api/users/23"
        };

        for (String serverUrl : servers) {
            try {
                ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.GET, null, String.class);
                if(response.getStatusCode().is2xxSuccessful()){
                    serverHealthStatuses.put(serverUrl, "UP");
                }
                else{
                    serverHealthStatuses.put(serverUrl, "DOWN");
                }
            } catch (Exception e) {
                serverHealthStatuses.put(serverUrl, "DOWN");
            }
        }

        return serverHealthStatuses;
    }
}
