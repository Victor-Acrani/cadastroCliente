package br.com.acrani.cadastroCliente.config;

import br.com.acrani.cadastroCliente.models.Authority;
import br.com.acrani.cadastroCliente.models.Cliente;
import br.com.acrani.cadastroCliente.models.UserLogin;
import br.com.acrani.cadastroCliente.models.enums.Role;
import br.com.acrani.cadastroCliente.repositories.AuthorityRepository;
import br.com.acrani.cadastroCliente.repositories.ClienteRepository;
import br.com.acrani.cadastroCliente.repositories.UserLoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final AuthorityRepository authorityRepository;
    private final UserLoginRepository userLoginRepository;
    private final PasswordEncoder passwordEncoder;

    public TestConfig(ClienteRepository clienteRepository, AuthorityRepository authorityRepository, UserLoginRepository userLoginRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.authorityRepository = authorityRepository;
        this.userLoginRepository = userLoginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // populando clientes
        Cliente cliente1 = new Cliente(null, "Joao Kleber", "63770592050", "32165270", "joao@gmail.com");
        Cliente cliente2 = new Cliente(null, "Clóvis Hill", "64175480072", "32925781", "clovis@gmail.com");
        clienteRepository.saveAll(List.of(cliente1, cliente2));

        //populando authorities
        Authority auth1 = new Authority(null, Role.ROLE_USER);
        Authority auth2 = new Authority(null, Role.ROLE_ADMIN);
        authorityRepository.saveAll(List.of(auth1, auth2));

        //populando usuarios
        UserLogin user1 = new UserLogin(null, "user",passwordEncoder.encode("123"), Set.of(auth1));
        UserLogin user2 = new UserLogin(null, "admin",passwordEncoder.encode("123"), Set.of(auth1,auth2));
        userLoginRepository.saveAll(List.of(user1, user2));
    }
}
