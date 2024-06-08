package dev.attaphong.ecommerce_app_study.service;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class HeaderService {
    private static volatile HeaderService instance;

    @Getter
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
}
