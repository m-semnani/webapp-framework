package nl.linkit.productmngt.service;

import java.util.ArrayList;

import nl.linkit.productmngt.model.AppUser;
import nl.linkit.productmngt.model.SpringUser;
import nl.linkit.productmngt.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

        if ("root".equals(username)) {
            return new User("root", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());

        } else {
            AppUser user = appUserRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            log.debug("loadUserByUsername() : {}", username);
            return new SpringUser(user);
        }
    }
}
