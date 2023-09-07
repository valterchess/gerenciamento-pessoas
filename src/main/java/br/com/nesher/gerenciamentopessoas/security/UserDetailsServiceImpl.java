package br.com.nesher.gerenciamentopessoas.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nesher.gerenciamentopessoas.domain.entity.Supervisor;
import br.com.nesher.gerenciamentopessoas.repository.SupervisorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SupervisorRepository gerenciadorRepository;

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        Optional<Supervisor> gerenciador = gerenciadorRepository.findByEmail(userName);
        gerenciador.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

        return gerenciador.map(UserDetailsImpl::new).get();
    }
}
