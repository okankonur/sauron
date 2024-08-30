package com.okankonur.sauron.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.okankonur.sauron.models.AppInfo;
import com.okankonur.sauron.services.AppHealthCheckService;

import java.io.IOException;
import java.util.List;


@RestController
public class AppHealthController {

    @Autowired
    private AppHealthCheckService serverHealthCheckService;

    @GetMapping("/api/health")
    public ResponseEntity<List<AppInfo>> getHealth() {
        try {
            
            List<AppInfo> apps = serverHealthCheckService.checkAppHealth();
            return new ResponseEntity<>(apps, HttpStatus.OK);

        } catch (StreamReadException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DatabindException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

