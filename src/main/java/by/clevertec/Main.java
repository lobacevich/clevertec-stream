package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> x.getAge() >= 10 && x.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);

    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> x.getOrigin().equals("Japanese"))
                .peek(x -> x.setBread(x.getGender().equals("Female") ? x.getBread().toUpperCase() : x.getBread()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> x.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(x -> x.startsWith("A"))
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .filter(x -> x.getGender().equals("Female"))
                        .count()
        );
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .filter(x -> x.getAge() >= 20 && x.getAge() <= 30)
                        .anyMatch(x -> x.getOrigin().equals("Hungarian"))
        );

    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .allMatch(x -> x.getGender().equals("Male")
                                || x.getGender().equals("Female"))
        );
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .noneMatch(x -> x.getOrigin().equals("Oceania"))
        );
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .sorted(Comparator.comparing(Animal::getBread))
                        .limit(100)
                        .mapToInt(Animal::getAge)
                        .max()
                        .orElse(-1)
        );
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .mapToInt(x -> x.getBread().toCharArray().length)
                        .min()
                        .orElse(-1)
        );
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .mapToInt(Animal::getAge)
                        .sum()
        );
    }

    public static double task11() {
        List<Animal> animals = Util.getAnimals();
                double averageAge = animals.stream()
                        .filter(x -> x.getOrigin().equals("Indonesian"))
                        .mapToDouble(Animal::getAge)
                        .average()
                        .orElse(-1);
        System.out.println(averageAge);
        return averageAge;
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(x -> x.getGender().equals("Male")
                        && ChronoUnit.YEARS.between(x.getDateOfBirth(), LocalDate.now()) >= 18
                        && ChronoUnit.YEARS.between(x.getDateOfBirth(), LocalDate.now()) <= 27)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
        houses.stream()
                .flatMap(x -> (x.getPersonList()).stream())
                .sorted((o1, o2) -> {
                    House hospital = houses.stream()
                            .filter(x -> x.getBuildingType().equals("Hospital"))
                            .findFirst()
                            .get();
                    if (hospital.getPersonList().contains(o1)) {
                        return -1;
                    } else if (hospital.getPersonList().contains(o2)) {
                        return 1;
                    } else if (ChronoUnit.YEARS.between(o1.getDateOfBirth(), LocalDate.now()) < 18 ||
                            ChronoUnit.YEARS.between(o1.getDateOfBirth(), LocalDate.now()) > 65) {
                        return -1;
                    } else if (ChronoUnit.YEARS.between(o2.getDateOfBirth(), LocalDate.now()) < 18 ||
                            ChronoUnit.YEARS.between(o2.getDateOfBirth(), LocalDate.now()) > 65) {
                        return 1;
                    }
                    return 0;
                })
                .limit(500)
                .forEach(System.out::println);
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
        double[] array = new double[6];
        cars.stream()
                .peek(x -> {
                    if (x.getCarMake().equals("Jaguar") || x.getColor().equals("White")) {
                        array[0] = array[0] + x.getMass();
                    }
                })
                .filter(x -> !x.getCarMake().equals("Jaguar") && !x.getColor().equals("White"))
                .peek(x -> {
                    if (x.getMass() < 1500 || x.getCarMake().equals("BMW") || x.getCarMake().equals("Lexus")
                            || x.getCarMake().equals("Chrysler") || x.getCarMake().equals("Toyota")) {
                        array[1] = array[1] + x.getMass();
                    }
                })
                .filter(x -> x.getMass() >= 1500 && !x.getCarMake().equals("BMW")
                        && !x.getCarMake().equals("Lexus") && !x.getCarMake().equals("Chrysler")
                        && !x.getCarMake().equals("Toyota"))
                .peek(x -> {
                    if (x.getColor().equals("Black") && x.getMass() > 4000 || x.getCarMake().equals("GMC")
                            || x.getCarMake().equals("Dodge")) {
                        array[2] = array[2] + x.getMass();
                    }
                })
                .filter(x -> !(x.getColor().equals("Black") && x.getMass() <= 4000) && !x.getCarMake().equals("GMC")
                        && !x.getCarMake().equals("Dodge"))
                .peek(x -> {
                    if (x.getReleaseYear() < 1982 || x.getCarMake().equals("Civic") || x.getCarMake().equals("Cherokee")) {
                        array[3] = array[3] + x.getMass();
                    }
                })
                .filter(x -> x.getReleaseYear() >= 1982 && !x.getCarMake().equals("Civic")
                        && !x.getCarMake().equals("Cherokee"))
                .peek(x -> {
                    if (!x.getColor().equals("Yellow") && !x.getColor().equals("Red") && !x.getColor().equals("Green")
                            && !x.getColor().equals("Blue") || x.getPrice() > 40000) {
                        array[4] = array[4] + x.getMass();
                    }
                })
                .filter(x -> (x.getColor().equals("Yellow") || x.getColor().equals("Red") || x.getColor().equals("Green")
                        || x.getColor().equals("Blue")) && x.getPrice() <= 40000)
                .peek(x -> {
                    if (x.getVin().contains("59")) {
                        array[5] = array[5] + x.getMass();
                    }
                })
                .collect(Collectors.toList());
        System.out.println(
                Arrays.stream(array)
                        .map(x -> x / 1000 * 7.14)
                        .peek(System.out::println)
                        .sum()
        );
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        System.out.println(
                flowers.stream()
                        .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                        .sorted(Comparator.comparing(Flower::getPrice))
                        .sorted(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed())
                        .filter(x -> x.getCommonName().charAt(0) >= 'C'
                                && x.getCommonName().charAt(0) <= 'S'
                                && x.isShadePreferred()
                                && x.getFlowerVaseMaterial().contains("Glass")
                                && x.getFlowerVaseMaterial().contains("Aluminum")
                                && x.getFlowerVaseMaterial().contains("Steel"))
                        .mapToDouble(x -> x.getPrice() + x.getWaterConsumptionPerDay() * 5 * 365 * 1.39 / 1000000)
                        .sum()
        );
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(x -> x.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(System.out::println);
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        System.out.println(
                students.stream()
                        .collect(Collectors.groupingBy(Student::getFaculty,
                                Collectors.averagingDouble(Student::getAge)))
        );
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        examinations.stream()
                .filter(x -> x.getExam3() > 4)
                .map(ex -> students.stream()
                        .filter(st -> st.getId() == ex.getId())
                        .findFirst()
                        .get())
                .forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        System.out.println(
                examinations.stream()
                        .map(ex -> {
                            Map<String, String> map = new HashMap<>();
                            map.put("faculty", students.stream()
                                    .filter(st -> st.getId() == ex.getId())
                                    .findFirst()
                                    .get()
                                    .getFaculty());
                            map.put("exam1", String.valueOf(ex.getExam1()));
                            return map;
                        })
                        .collect(Collectors.groupingBy(x -> x.get("faculty"),
                                Collectors.averagingDouble(x -> Integer.parseInt(x.get("exam1")))))
        );
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        System.out.println(
                students.stream()
                        .collect(Collectors.groupingBy(Student::getGroup,
                                Collectors.counting()))
        );
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        System.out.println(
                students.stream()
                        .collect(Collectors.groupingBy(Student::getFaculty,
                                Collectors.mapping(Student::getAge,
                                        Collectors.minBy(Integer::compareTo))))
        );
    }
}
