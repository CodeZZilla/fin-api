package kaf22.codezilla.finapi.repositories;

import kaf22.codezilla.finapi.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PagingAndSortingRepository<Person, Long> {

    Page<Person> findAll(Pageable pageable);

    @Query("select p from Person p where p.uniqueCode like concat(:code, '%')")
    Page<Person> findByUniqueCode(Pageable pageable, @Param("code") String code);
}
