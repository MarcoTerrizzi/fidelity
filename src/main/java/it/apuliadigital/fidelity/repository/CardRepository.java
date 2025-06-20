package it.apuliadigital.fidelity.repository;

import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.Card;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

}