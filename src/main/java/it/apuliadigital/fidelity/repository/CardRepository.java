package it.apuliadigital.fidelity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCodFisc(String codFisc);

}