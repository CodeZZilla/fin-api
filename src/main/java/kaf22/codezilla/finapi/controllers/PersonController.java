package kaf22.codezilla.finapi.controllers;

import kaf22.codezilla.finapi.errors.ErrorCode;
import kaf22.codezilla.finapi.models.Person;
import kaf22.codezilla.finapi.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/person")
@Slf4j
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable long id) {
        Optional<Person> person = personService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (person.isEmpty())
            return new ResponseEntity<>(Map.of(
                    "code", ErrorCode.NO_SUCH_OBJECT.getCode(),
                    "error", ErrorCode.NO_SUCH_OBJECT.getMessage()
            ), HttpStatus.NOT_FOUND);

        response.put("id", person.get().getId());
        response.put("uniqueCode", person.get().getUniqueCode());
        response.put("firstName", person.get().getFirstName());
        response.put("lastName", person.get().getLastName());
        response.put("surName", person.get().getSurName());
        response.put("firstNameCase", person.get().getFirstNameCase());
        response.put("lastNameCase", person.get().getLastNameCase());
        response.put("surNameCase", person.get().getSurNameCase());
        response.put("dateOfBirth", person.get().getDateOfBirth());

        Set<Long> inputDocumentsIds = new HashSet<>();
        Set<Long> recipientOGDsIds = new HashSet<>();
        Set<Long> protocolItemsIds = new HashSet<>();
        Set<Long> propertysIds = new HashSet<>();
        Set<Long> mainDocumentsIds = new HashSet<>();
        Set<Long> paymentsOGDIds = new HashSet<>();
        Set<Long> judgeOGDsIds = new HashSet<>();

        if (!person.get().getInputDocuments().isEmpty()) {
            person.get().getInputDocuments().forEach(item -> inputDocumentsIds.add(item.getId()));
            response.put("inputDocuments", inputDocumentsIds);
        }

        if (!person.get().getRecipientOGDList().isEmpty()) {
            person.get().getRecipientOGDList().forEach(item -> recipientOGDsIds.add(item.getId()));
            response.put("recipientOGDs", recipientOGDsIds);
        }

        if (!person.get().getProtocolItemList().isEmpty()) {
            person.get().getProtocolItemList().forEach(i -> protocolItemsIds.add(i.getId()));
            response.put("protocolItems", protocolItemsIds);
        }

        if (!person.get().getPropertyList().isEmpty()) {
            person.get().getPropertyList().forEach(i -> propertysIds.add(i.getId()));
            response.put("propertys", propertysIds);
        }

        if (!person.get().getMainDocumentList().isEmpty()) {
            person.get().getMainDocumentList().forEach(i -> mainDocumentsIds.add(i.getId()));
            response.put("mainDocuments", mainDocumentsIds);
        }

        if (!person.get().getPaymentsOGDList().isEmpty()) {
            person.get().getPaymentsOGDList().forEach(i -> paymentsOGDIds.add(i.getId()));
            response.put("paymentsOGD", paymentsOGDIds);
        }

        if (!person.get().getJudgeOGDList().isEmpty()) {
            person.get().getJudgeOGDList().forEach(i -> judgeOGDsIds.add(i.getId()));
            response.put("judgeOGDs", judgeOGDsIds);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListPerson(@RequestParam(value = "page", required = false) Integer page,
                                           @RequestParam(value = "limit", required = false) Integer limit,
                                           @RequestParam(value = "search", required = false) String search) {

        try {
            if (page == null && limit == null) {
                Page<Person> personPage = personService.findAllByPage(PageRequest.of(1, 1));
                return ResponseEntity.ok(Map.of(
                        "count", personPage.getTotalElements()
                ));
            }

            Pageable pageable = PageRequest.of(page, limit);

            if (search != null) {
                Page<Person> personPageByUniqueCode = personService.findByUniqueCodePage(pageable, search);
                return ResponseEntity.ok(Map.of(
                                "count", personPageByUniqueCode.getTotalElements(),
                                "persons", personPageByUniqueCode.getContent()
                        )
                );
            }

            Page<Person> pageTuts = personService.findAllByPage(pageable);

            return ResponseEntity.ok(Map.of(
                    "count", pageTuts.getTotalElements(),
                    "persons", pageTuts.getContent()
            ));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(Map.of(
                    "code", ErrorCode.INCORRECT_DATA_OR_QUERY_PARAMETERS.getCode(),
                    "error", ErrorCode.INCORRECT_DATA_OR_QUERY_PARAMETERS.getMessage()
            ), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPerson(@RequestBody @Valid Person person) {
        try {
            Person savePerson = personService.save(person);
            return ResponseEntity.ok(Map.of("id", savePerson.getId()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(Map.of(
                    "code", ErrorCode.NOT_ALL_REQUIRED_FIELDS_WERE_FILLED.getCode(),
                    "message", ErrorCode.NOT_ALL_REQUIRED_FIELDS_WERE_FILLED.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editPerson(@PathVariable Long id, @RequestBody @Valid Person personBody) {
        Optional<Person> person = personService.findById(id);

        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "code", ErrorCode.INCORRECT_ID.getCode(),
                    "message", ErrorCode.INCORRECT_ID.getMessage()
            ));
        }

        person.get().setUniqueCode(personBody.getUniqueCode());
        person.get().setFirstName(personBody.getFirstName());
        person.get().setLastName(personBody.getLastName());
        person.get().setSurName(personBody.getSurName());

        person.get().setFirstNameCase(personBody.getFirstNameCase() == null ? person.get().getFirstNameCase() : personBody.getFirstNameCase());
        person.get().setLastNameCase(personBody.getLastNameCase() == null ? person.get().getLastNameCase() : personBody.getLastNameCase());
        person.get().setSurNameCase(personBody.getSurNameCase() == null ? person.get().getSurNameCase() : personBody.getSurNameCase());
        person.get().setDateOfBirth(personBody.getDateOfBirth() == null ? person.get().getDateOfBirth() : personBody.getDateOfBirth());

        return ResponseEntity.ok(personService.save(person.get()));
    }
}
