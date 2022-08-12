package br.com.acrani.cadastroCliente.repositories;

import br.com.acrani.cadastroCliente.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findByUsername(String username);
}
