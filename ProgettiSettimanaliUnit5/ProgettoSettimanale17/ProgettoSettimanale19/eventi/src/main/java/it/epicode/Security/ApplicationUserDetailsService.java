package it.epicode.Security;

import it.epicode.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    UtenteRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = users.findOneByUsername(username).orElseThrow();
        var applicationUser = SecurityUserDetails.build(user);
        return applicationUser;
    }
}
