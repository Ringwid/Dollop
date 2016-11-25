package com.ringwid.dollop.network;

import com.ringwid.dollop.Dollop;
import com.ringwid.dollop.website.Website;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkManager {

    private final Dollop dollop;
    private ConcurrentHashMap<String, Website> websites = new ConcurrentHashMap<>();

    public NetworkManager(Dollop dollop) {
        this.dollop = dollop;
    }

    public void login(Website website, String username, String password) {
        websites.put(website.getName(), website);
        website.handleLogin(username, password);
    }

}
