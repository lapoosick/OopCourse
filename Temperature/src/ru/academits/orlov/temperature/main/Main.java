package ru.academits.orlov.temperature.main;

import ru.academits.orlov.temperature.model.Converter;
import ru.academits.orlov.temperature.model.TemperatureConverter;
import ru.academits.orlov.temperature.view.DesktopView;
import ru.academits.orlov.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new DesktopView(converter);

        view.start();
    }
}