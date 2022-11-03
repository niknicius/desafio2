package tech.dock.paymentapi.services;

import org.springframework.stereotype.Service;
import tech.dock.paymentapi.core.base.BaseRepository;
import tech.dock.paymentapi.core.base.BaseService;
import tech.dock.paymentapi.models.Person;
import tech.dock.paymentapi.repository.PersonRepository;

@Service
public class PersonService extends BaseService<Person, Integer> {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    protected BaseRepository<Person, Integer> getRepository() {
        return this.personRepository;
    }
}
