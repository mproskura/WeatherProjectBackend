package com.sda.weatherprojectbackend.services;

import com.sda.weatherprojectbackend.components.weather.IWeatherForecastComponent;
import com.sda.weatherprojectbackend.components.weather.OpenWeatherComponent;
import com.sda.weatherprojectbackend.components.weather.TomorrowIoComponent;
import com.sda.weatherprojectbackend.components.weather.YrnoForecastComponent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ComponentFactory {
    enum WeatherComponent {
        OPENWEATHER("OpenWeather", new OpenWeatherComponent()),
        YRNOFORECAST("Yr.no", new YrnoForecastComponent()),
        TOMORROWIO("Tomorrow.io", new TomorrowIoComponent());

        private String name;
        private IWeatherForecastComponent component;

        public IWeatherForecastComponent getComponent() {
            return component;
        }

        WeatherComponent(String name, IWeatherForecastComponent component) {
            this.name = name;
            this.component = component;
        }

    }

    private static final Map<String, IWeatherForecastComponent> map = new HashMap<>();

    public static Optional<IWeatherForecastComponent> get(String name) {
        if (map.containsKey(name)) {
            return Optional.of(map.get(name));
        }

        for (WeatherComponent component : WeatherComponent.values()) {
            if (component.name.equals(name)) {
                map.put(name, component.getComponent());
                return Optional.of(map.get(name));
            }
        }

        return Optional.empty();
    }

}
