package nl.linkit.productmngt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import nl.linkit.productmngt.model.AppUser;
import nl.linkit.productmngt.model.AuthorityType;
import nl.linkit.productmngt.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("admin".equals(username)) {
            User user = new User("admin", "$2a$10$ECYjmstA15WKOGqs/mf0aujYaGGY6ZmUB4E.kSQDlObBtntJRTf7u",
                    new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority(AuthorityType.ADMIN_ROLE.name()))));
            return user;

        } else {
            AppUser appUser = appUserRepository.findByUsername(username);

            if (appUser == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            log.debug("loadUserByUsername() : {}", username);
            return new User(appUser.getUsername(), appUser.getPassword(), true, true, true, true,
                    appUser.getAuthorities().stream()
                            .map(authority -> new SimpleGrantedAuthority(authority.getName().toString()))
                            .collect(Collectors.toList())
            );
        }
    }
}
