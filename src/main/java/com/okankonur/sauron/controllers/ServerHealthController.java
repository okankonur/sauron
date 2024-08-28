package com.okankonur.sauron.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okankonur.sauron.services.ServerHealthCheckService;

import java.util.Map;

@RestController
public class ServerHealthController {

    @Autowired
    private ServerHealthCheckService serverHealthCheckService;

    @GetMapping("/api/servers-health")
    public Map<String, String> getServersHealth() {
        return serverHealthCheckService.checkServersHealth();
    }
}

