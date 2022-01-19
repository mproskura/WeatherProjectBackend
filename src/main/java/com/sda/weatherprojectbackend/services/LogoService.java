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
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class LogoService {

    private final WeatherSourceRepository weatherSourceRepository;

    public LogoService(WeatherSourceRepository weatherSourceRepository) {
        this.weatherSourceRepository = weatherSourceRepository;
    }

    public Optional<ByteArrayResource> getLogoImage(long weatherServiceId)  {
        Optional<WeatherSourceEntity> byId = weatherSourceRepository.findById(weatherServiceId);
        if (byId.isPresent()) {
            String imageFileName = byId.get().getLogoFileName();
            try {
                File imageFile = ResourceUtils.getFile("classpath:logos/" + imageFileName);
                byte[] bytes = Files.readAllBytes(imageFile.toPath());
                return Optional.of(new ByteArrayResource(bytes));
            } catch (IOException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
