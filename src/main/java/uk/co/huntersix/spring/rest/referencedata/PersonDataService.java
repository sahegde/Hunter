package uk.co.huntersix.spring.rest.referencedata;

import org.springframework.stereotype.Service;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonDataService {
    public static final List<Person> PERSON_DATA = new ArrayList<>(Arrays.asList(
        new Person("Mary", "Smith"),
        new Person("Brian", "Archer"),
        new Person("Collin", "Brown")
    ));

    public Person findPerson(String lastName, String firstName) {
        List<Person> people = PERSON_DATA.stream()
            .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                && p.getLastName().equalsIgnoreCase(lastName))
            .collect(Collectors.toList());

        return people.size() != 0 ? people.get(0) : null;
    }

    public List<Person> findPersonWithSurname(String lastName) {
        // Return an array of Person objects. It doesn't matter if there is one match or multiple matches.
        return PERSON_DATA.stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public Person addPerson(String lastName, String firstName) {

        boolean isPersonPresent = PERSON_DATA.stream()
                .anyMatch(p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName));

        if (isPersonPresent) {
            // The below statement should be replaced with log statements.
            System.out.println("No operation needed");
            return null;
        }

        Person newPerson = new Person(firstName, lastName);
        PERSON_DATA.add(newPerson);
        return newPerson;
    }
}
