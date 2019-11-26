package br.com.sicredi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.models.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, String>{

	Associado findByCpf(String cpf);
	Optional<Associado> findById(Long id);
	
}
