package org.shoeshop.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
      
    	Client user = clientRepository.findByUsername(username);
               
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        
         
        return new MyUserDetails(user);
    }
}
