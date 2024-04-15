package ru.academits.orlov.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = List.of(
                new Person("Сергей", 88),
                new Person("Иван", 49),
                new Person("Антон", 34),
                new Person("Антон", 28),
                new Person("Пётр", 20),
                new Person("Пётр", 26));

        // получить список уникальных имен
        List<String> uniqueNamesList = personsList.stream()
                .map(Person::getName)
                .distinct()
                .toList();
        Set<String> uniqueNamesSet = personsList.stream()
                .map(Person::getName)
                .collect(Collectors.toSet());

        // вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.
        System.out.println(uniqueNamesList.stream().collect(Collectors.joining(", ", "Имена: ", ".")));
        System.out.println(uniqueNamesSet.stream().collect(Collectors.joining(", ", "Имена: ", ".")));

        // получить список людей младше 18, посчитать для них средний возраст
        OptionalDouble personsUnder18AverageAge = personsList.stream()
                .filter(person -> person.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        System.out.println(personsUnder18AverageAge.isPresent()
                ? "Средний возраст людей младше 18: " + personsUnder18AverageAge.getAsDouble()
                : "В списке нет людей младше 18.");

        // при помощи группировки получить Мар, в котором ключи - имена, а значения - средний возраст
        Map<String, Double> averageAgesByNames = personsList.stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        System.out.println("Имена со средним возрастом их обладателей: " + averageAgesByNames);

        // получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        personsList.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .forEach(System.out::println);
    }
}