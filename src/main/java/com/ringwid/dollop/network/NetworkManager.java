package com.ringwid.dollop.network;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ringwid.dollop.Dollop;
import com.ringwid.dollop.website.Ebay;
import com.ringwid.dollop.website.Website;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkManager {

    private static HashMap<String, Class<? extends Website>> supportList = Maps.newHashMap();

    static {
        supportList.put("eBay", Ebay.class);
    }

    private final Dollop dollop;
    private WebClient webClient;
    private Website website;

    public NetworkManager(Dollop dollop, String website) {
        this.dollop = dollop;

        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getCache().setMaxSize(0);

        try {
            Constructor constructor = supportList.get(website).getDeclaredConstructor(this.getClass());
            this.website = (Website) constructor.newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(String username, String password) {
        website.handleLogin(username, password);
    }

}
