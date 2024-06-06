package dev.attaphong.ecommerce_app_study.service;

import org.springframework.http.HttpHeaders;

public class HeaderService {
    private static volatile HeaderService instance;

    private final HttpHeaders globalHeaders;

    private HeaderService(){
        globalHeaders = new HttpHeaders();
        globalHeaders.add("Content-Type","application/json");
    }

    public static HeaderService getInstance(){
        if(instance == null){
            synchronized (HeaderService.class){
                if(instance == null){
                    instance = new HeaderService();
                }
            }
        }
        return instance;
    }

    public HttpHeaders getGlobalHeaders(){
        return globalHeaders;
    }
}
