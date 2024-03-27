package ru.academits.orlov.lambdas_main;

import ru.academits.orlov.lambdas.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Сергей", 8);
        Person person2 = new Person("Иван", 4);
        Person person3 = new Person("Антон", 34);
        Person person4 = new Person("Антон", 28);
        Person person5 = new Person("Пётр", 20);
        Person person6 = new Person("Пётр", 26);

        Supplier<Stream<Person>> personStreamSupplier = () -> Stream.of(person1, person2, person3, person4, person5, person6);

        // получить список уникальных имен
        List<String> uniqueNamesList = personStreamSupplier.get()
                .map(Person::getName)
                .distinct()
                .toList();

        Set<String> uniqueNamesSet = personStreamSupplier.get()
                .map(Person::getName)
                .collect(Collectors.toSet());

        // вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.
        System.out.println(uniqueNamesList.stream().collect(Collectors.joining(", ", "Имена: ", ".")));
        System.out.println(uniqueNamesSet.stream().collect(Collectors.joining(", ", "Имена: ", ".")));

        // получить список людей младше 18, посчитать для них средний возраст
        double personsUnder18AverageAge = personStreamSupplier.get()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.averagingInt(Person::getAge));

        System.out.println("Средний возраст людей младше 18: " + personsUnder18AverageAge);

        // при помощи группировки получить Мар, в котором ключи - имена, а значения - средний возраст
        Map<String, Double> averageAgesByName = personStreamSupplier.get()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        System.out.println("Имена со средним возрастом их обладателей: " + averageAgesByName);

        // получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        personStreamSupplier.get()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .forEach(System.out::println);
    }
}