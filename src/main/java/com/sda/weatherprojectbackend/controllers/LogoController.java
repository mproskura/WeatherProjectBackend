package com.sda.weatherprojectbackend.controllers;

import com.sda.weatherprojectbackend.services.LogoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@CrossOrigin
@RestController
public class LogoController {

    private final LogoService logoService;

    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }


    @GetMapping(value = "/logo  /{weatherServiceId}", produces = MediaType.IMAGE_PNG_VALUE)
    Resource downloadImage(@PathVariable Long weatherServiceId) {
        Optional<ByteArrayResource> logoImageOptional = logoService.getLogoImage(weatherServiceId);
        if (logoImageOptional.isPresent()) {
            return logoImageOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
