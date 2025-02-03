package Salesforelikeapp.Salesforelikeapp.service;

import Salesforelikeapp.Salesforelikeapp.model.User; // Your custom User model
import Salesforelikeapp.Salesforelikeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); // Retrieve the user from the repository
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // You can add roles or authorities here if needed, for example:
        // List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())  // The password should be hashed and validated
                .authorities(Collections.emptyList()) // No roles for now, you can add them later
                .build();
    }
}
