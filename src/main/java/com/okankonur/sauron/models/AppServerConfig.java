package com.okankonur.sauron.models;

import java.util.List;

public class AppServerConfig {
    
    private List<AppInfo> apps;

    public List<AppInfo> getApps() {
        return apps;
    }

    public void setApps(List<AppInfo> apps) {
        this.apps = apps;
    }
}
