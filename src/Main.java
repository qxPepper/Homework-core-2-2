import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Проверил на меньшем количестве народа. Выборки из 10млн не стал печатать.
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Количество несовершеннолетних (т.е. людей младше 18 лет).
        long juveniles = persons.stream()
                .filter(v -> v.getAge() < 18)
                .count();

        //Список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> recruits = persons.stream()
                .filter(v -> v.getSex().equals(Sex.MAN))
                .filter(v -> (v.getAge() >= 18 && v.getAge() <= 27))
                .map(Person::getFamily)
                .collect(Collectors.toList());

        //Список потенциально работоспособных людей с высшим образованием.
        List<Person> highers = persons.stream()
                .filter(v -> v.getEducation().equals(Education.HIGHER))
                .filter(v -> (v.getSex().equals(Sex.MAN) && v.getAge() >= 18 && v.getAge() <= 65) ||
                        (v.getSex().equals(Sex.WOMAN) && v.getAge() >= 18 && v.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
