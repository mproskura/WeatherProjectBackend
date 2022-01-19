package com.sda.weatherprojectbackend.services;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathTest {

    @Test
    void path() {
        URL url = getClass().getClassLoader().getResource("YrNo.png");
        File file = new File(url.getPath());
        assertTrue(file.isFile());
        assertTrue(file.canRead());
    }
}
