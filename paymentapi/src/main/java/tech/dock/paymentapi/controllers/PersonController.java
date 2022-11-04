package tech.dock.paymentapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.dock.paymentapi.core.base.BaseController;
import tech.dock.paymentapi.core.base.BaseService;
import tech.dock.paymentapi.core.exception.BusinessException;
import tech.dock.paymentapi.dto.PersonDTO;
import tech.dock.paymentapi.models.Person;
import tech.dock.paymentapi.services.PersonService;

import javax.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController extends BaseController<Person, Integer, PersonDTO> {

    private final PersonService service;

    public PersonController(PersonService personService) {
        this.service = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable("id") Integer id) throws BusinessException {
        return new ResponseEntity<>(mapTo(this.service.getOne(id), PersonDTO.class), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonDTO schoolDto) throws BusinessException {
        Person person = this.service.insert(mapTo(schoolDto, Person.class));
        return new ResponseEntity<>(mapTo(person, PersonDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable("id") Integer id,
                                            @RequestBody @Valid PersonDTO schoolDto) {
        Person person = this.service.update(id, mapTo(schoolDto, Person.class));
        return new ResponseEntity<>(mapTo(person, PersonDTO.class), HttpStatus.OK);
    }

    @Override
    protected BaseService<Person, Integer> getService() {
        return service;
    }

}
