package com.sistecredito.api.utils;

public enum Endpoints {
    URL_MEMBERS("members"),
    URL_UPLOAD("upload");


    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
