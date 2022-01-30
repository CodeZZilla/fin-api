package kaf22.codezilla.finapi.services;

import kaf22.codezilla.finapi.models.Person;
import kaf22.codezilla.finapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(long id) {
        return personRepository.findById(id);
    }

    public Person save(Person person) {
       return personRepository.save(person);
    }

    public Page<Person> findAllByPage(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Page<Person> findByUniqueCodePage(Pageable pageable, String code) {
        return personRepository.findByUniqueCode(pageable, code);
    }
}
