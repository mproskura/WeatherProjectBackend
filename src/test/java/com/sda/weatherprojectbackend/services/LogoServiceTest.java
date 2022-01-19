package com.sda.weatherprojectbackend.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class LogoServiceTest {

    private final LogoService logoService;

    LogoServiceTest(LogoService logoService) {
        this.logoService = logoService;
    }

    @Test
    void getLogoImage() throws IOException {
        logoService.getLogoImage(1);
    }
}