package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.entities.WeatherSourceEntity;
import com.sda.weatherprojectbackend.repositories.WeatherSourceRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class LogoService {

    private final WeatherSourceRepository weatherSourceRepository;

    public LogoService(WeatherSourceRepository weatherSourceRepository) {
        this.weatherSourceRepository = weatherSourceRepository;
    }

    public Optional<ByteArrayResource> getLogoImage(long weatherServiceId) {
        Optional<WeatherSourceEntity> byId = weatherSourceRepository.findById(weatherServiceId);
        if (byId.isPresent()) {
            String imageFileName = byId.get().getLogoFileName();
            try {
                InputStream resourceAsStream = LogoService.class.getResourceAsStream("/logos/" + imageFileName);
                byte[] bytes = resourceAsStream.readAllBytes();
                return Optional.of(new ByteArrayResource(bytes));
            } catch (IOException e) {
                System.out.println("Picture loading exception: " + e);
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
