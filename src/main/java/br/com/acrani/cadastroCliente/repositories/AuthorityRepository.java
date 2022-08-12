package br.com.acrani.cadastroCliente.repositories;

import br.com.acrani.cadastroCliente.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
