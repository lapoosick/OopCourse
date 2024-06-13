package ru.academits.orlov.temperature.view;

import ru.academits.orlov.temperature.model.Converter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DesktopView implements View {
    private static final String CELSIUS = "градусы Цельсия";
    private static final String FAHRENHEIT = "градусы Фаренгейта";
    private static final String KELVIN = "кельвины";

    private final Converter converter;
    private JFrame frame;

    public DesktopView(Converter converter) {
        this.converter = converter;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(this::createAndShowAppWindow);
    }

    private void createAndShowAppWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        frame = new JFrame("Конвертер температур");
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());

        frame.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());

        JLabel label;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        label = new JLabel("Исходное значение");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;

        pane.add(label, gridBagConstraints);

        JTextField initialValueField = new JTextField(12);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        pane.add(initialValueField, gridBagConstraints);

        JComboBox<String> initialUnitsComboBox = getTemperatureUnitsComboBox();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        pane.add(initialUnitsComboBox, gridBagConstraints);

        label = new JLabel("Результат");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        pane.add(label, gridBagConstraints);

        JTextField resultTextField = new JTextField(12);
        resultTextField.setEditable(false);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        pane.add(resultTextField, gridBagConstraints);

        JComboBox<String> resultUnitsComboBox = getTemperatureUnitsComboBox();
        resultUnitsComboBox.setSelectedIndex(1);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        pane.add(resultUnitsComboBox, gridBagConstraints);

        JButton convertButton = new JButton("Конвертировать");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        pane.add(convertButton, gridBagConstraints);

        convertButton.addActionListener(e -> {
            try {
                double initialValue = Double.parseDouble(initialValueField.getText());
                double resultValue = 0;

                if (Objects.equals(initialUnitsComboBox.getSelectedItem(), resultUnitsComboBox.getSelectedItem())) {
                    resultValue = initialValue;
                } else if (Objects.equals(initialUnitsComboBox.getSelectedItem(), CELSIUS)) {
                    if (Objects.equals(resultUnitsComboBox.getSelectedItem(), FAHRENHEIT)) {
                        resultValue = converter.convertCelsiusToFahrenheit(initialValue);
                    } else if (Objects.equals(resultUnitsComboBox.getSelectedItem(), KELVIN)) {
                        resultValue = converter.convertCelsiusToKelvin(initialValue);
                    }
                } else if (Objects.equals(initialUnitsComboBox.getSelectedItem(), FAHRENHEIT)) {
                    if (Objects.equals(resultUnitsComboBox.getSelectedItem(), CELSIUS)) {
                        resultValue = converter.convertFahrenheitToCelsius(initialValue);
                    } else if (Objects.equals(resultUnitsComboBox.getSelectedItem(), KELVIN)) {
                        resultValue = converter.convertFahrenheitToKelvin(initialValue);
                    }
                } else if (Objects.equals(initialUnitsComboBox.getSelectedItem(), KELVIN)) {
                    if (Objects.equals(resultUnitsComboBox.getSelectedItem(), CELSIUS)) {
                        resultValue = converter.convertKelvinToCelsius(initialValue);
                    } else if (Objects.equals(resultUnitsComboBox.getSelectedItem(), FAHRENHEIT)) {
                        resultValue = converter.convertKelvinToFahrenheit(initialValue);
                    }
                }

                String resultFieldText = String.format("%.2f", resultValue);

                resultTextField.setText(resultFieldText);
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(frame, "Температура должна быть числом.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JComboBox<String> getTemperatureUnitsComboBox() {
        return new JComboBox<>(new String[]{CELSIUS, FAHRENHEIT, KELVIN});
    }
}
