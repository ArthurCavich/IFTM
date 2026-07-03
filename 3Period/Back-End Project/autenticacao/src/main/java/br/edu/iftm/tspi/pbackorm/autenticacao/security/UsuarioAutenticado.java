package br.edu.iftm.tspi.pbackorm.autenticacao.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.iftm.tspi.pbackorm.autenticacao.domain.Usuario;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsuarioAutenticado implements UserDetails {

    private final Usuario usuario;

    @Override
    public String getUsername() {
        return usuario.getLogin();
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

}
