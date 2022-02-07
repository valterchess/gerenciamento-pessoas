package one.digitalinnovation.gerenciamentodepessoas.domain.security;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.contributors.Supervisor;
import one.digitalinnovation.gerenciamentodepessoas.domain.repository.contributors.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
