package uk.co.huntersix.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;
import java.util.List;

@RestController
public class PersonController {
    private PersonDataService personDataService;

    public PersonController(@Autowired PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping("/person/{lastName}/{firstName}")
    public ResponseEntity<Person> person(@PathVariable(value="lastName") String lastName,
                                         @PathVariable(value="firstName") String firstName) {
        Person person = personDataService.findPerson(lastName, firstName);

        if (person == null) {
            // Below line should be replaced by logger statements.
            System.out.println("Person not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/person/{lastName}")
    public ResponseEntity<List<Person>> personWithSurname(@PathVariable(value="lastName") String lastName) {
        List<Person> people = personDataService.findPersonWithSurname(lastName);
        if (people == null) {
            // Below line should be replaced by logger statements.
            System.out.println("no Person found with this surname");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping("/person/{lastName}/{firstName}")
    public ResponseEntity<Person> addPerson(@PathVariable(value="lastName") String lastName,
                            @PathVariable(value="firstName") String firstName) {
        Person person = personDataService.addPerson(lastName, firstName);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
}
