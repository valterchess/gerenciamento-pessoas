package one.digitalinnovation.gerenciamentodepessoas.security;

import one.digitalinnovation.gerenciamentodepessoas.model.CadastroGerenciador;
import one.digitalinnovation.gerenciamentodepessoas.repository.CadastroGerenciadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CadastroGerenciadorRepository gerenciadorRepository;

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        Optional<CadastroGerenciador> gerenciador = gerenciadorRepository.findByUsuario(userName);
        gerenciador.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

        return gerenciador.map(UserDetailsImpl::new).get();
    }
}
