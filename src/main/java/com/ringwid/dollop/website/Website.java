package com.ringwid.dollop.website;

import com.gargoylesoftware.htmlunit.WebClient;

public abstract class Website {

    private Status status = Status.OFFLINE;
    private WebClient webClient;

    public Website(WebClient webClient) {
        this.webClient = webClient;
    }

    public abstract void handleLogin(String username, String password);

    public enum Status {
        OFFLINE, LOGGED_IN, LOGIN_ERROR
    }

    public abstract String getAddress();

    public abstract String getName();

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public WebClient getWebClient() {
        return webClient;
    }

}
