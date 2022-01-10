package com.sda.weatherprojectbackend.components.weather;

import com.sda.weatherprojectbackend.models.ForecastDetails;
import com.sda.weatherprojectbackend.models.ForecastLocation;
import org.apache.tomcat.jni.Local;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class YrnoForecastComponentTest {


    @ParameterizedTest
    @MethodSource("dateProvider")
    void getForecastDetailsTest(LocalDate inputDate) {
        YrnoForecastComponent yrnoForecastComponent = new YrnoForecastComponent();
        ForecastLocation location = ForecastLocation.builder()
                .latitude(1)
                .longitude(1)
                .build();

        Optional<ForecastDetails> forecast = yrnoForecastComponent.getForecast(location, inputDate);
        if(forecast.isPresent()) System.out.println(forecast);
    }

    private static Stream<Arguments> dateProvider(){
     return Stream.of(
                Arguments.of(LocalDate.now()),
                Arguments.of(LocalDate.now().plusDays(1)),
                Arguments.of(LocalDate.now().plusDays(3))
        );
    }
}