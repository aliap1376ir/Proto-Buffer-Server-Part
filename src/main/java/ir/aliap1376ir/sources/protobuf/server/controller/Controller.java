package ir.aliap1376ir.sources.protobuf.server.controller;

import ir.aliap1376ir.sources.protobuf.server.object.PersonOuterClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    PersonOuterClass.People people = PersonOuterClass.People.newBuilder().build();

    @PostMapping(path = "/register", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    private PersonOuterClass.Person registerPerson(@RequestBody PersonOuterClass.Person person) {
        person = person.toBuilder().setId(people.getPersonCount()).build();
        people = people.toBuilder().addPerson(person).build();
        System.out.println("Client Register a new one");
        return person;
    }

    @GetMapping(path = "/all", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    private PersonOuterClass.People getAllPeople() {
        System.out.println("Client Request for all!");
        return people;
    }
}
