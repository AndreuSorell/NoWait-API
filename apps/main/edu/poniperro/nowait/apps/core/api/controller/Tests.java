package edu.poniperro.nowait.apps.core.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class Tests {

    public Tests() {
    }

    @GetMapping("/consume/testConsume")
    public HashMap<String, Object> index(@RequestBody HashMap<String, Object> request) throws InterruptedException {
        return new HashMap<>(){{
            put("state", "test!!");
        }};
    }
}