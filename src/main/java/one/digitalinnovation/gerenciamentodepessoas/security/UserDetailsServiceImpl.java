package one.digitalinnovation.gerenciamentodepessoas.security;

import one.digitalinnovation.gerenciamentodepessoas.model.Gerenciador;
import one.digitalinnovation.gerenciamentodepessoas.repository.GerenciadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GerenciadorRepository gerenciadorRepository;

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        Optional<Gerenciador> gerenciador = gerenciadorRepository.findByUsuario(userName);
        gerenciador.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

        return gerenciador.map(UserDetailsImpl::new).get();
    }
}
