package edu.miu.groupx.security.securityservice.service;

import edu.miu.groupx.security.securityservice.model.AuthUserDetails;
import edu.miu.groupx.security.securityservice.model.User;
import edu.miu.groupx.security.securityservice.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDetailsRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("no user called ..."));
        UserDetails userDetails = new AuthUserDetails(user.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }

    public void saveUser(User user) {
        repository.save(user);
    }
}
