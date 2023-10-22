package one.digitalinovation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinovation.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
