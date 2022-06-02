package org.spring.ensapay.service;

import org.spring.ensapay.entity.JwtRequest;
import org.spring.ensapay.entity.JwtResponse;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.repository.UserRepository;
import org.spring.ensapay.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userPhone = jwtRequest.getUserPhone();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userPhone, userPassword);

        UserDetails userDetails = loadUserByUsername(userPhone);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findById(userPhone).get();
        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String userPhone) throws UsernameNotFoundException {
        User user = userRepository.findById(userPhone).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserPhone(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with E-mail: " + userPhone);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userPhone , String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userPhone, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
