package br.com.acrani.cadastroCliente.services.impl;

import br.com.acrani.cadastroCliente.models.UserLogin;
import br.com.acrani.cadastroCliente.repositories.UserLoginRepository;
import br.com.acrani.cadastroCliente.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserLoginRepository userLoginRepository;

    public UserDetailsServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserLogin userLogin = userLoginRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado. Username:  " + username));

        return new User(userLogin.getUsername(),userLogin.getPassword(), userLogin.getAuthorities());
    }
}
