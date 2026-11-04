package com.ecom.authentication.service;

import com.ecom.authentication.dto.UserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${services.users.url}", name = "user-service-client")
public interface UserClient {

    @GetMapping("/v1/internal/authenticate")
    UserData getUserData(@RequestHeader("Authorization") String authorization);

}
