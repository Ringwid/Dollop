package com.ringwid.dollop.website;

public abstract class Website {

    private Status status;

    public abstract void handleLogin(String username, String password);

    public enum Status {
        OFFLINE, LOGGED_IN
    }

    public abstract String getAddress();

    public abstract String getName();

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
