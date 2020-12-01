package com.example.exercises.service;

import com.example.exercises.domain.forms.UserCreatorForm;
import com.example.exercises.domain.model.User;
import com.example.exercises.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException("Username: " + s + " not found."));
    }

    // --------------------------------------

    public void createUser(String username, String password, List<String> roles){
        User user = new User();
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void updateUser(Integer id, UserCreatorForm editForm) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(editForm.getUsername());
        user.setPassword(passwordEncoder.encode(editForm.getPassword()));
        userRepository.save(user);
    }



}
