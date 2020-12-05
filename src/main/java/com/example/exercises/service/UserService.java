package com.example.exercises.service;

import com.example.exercises.domain.forms.UserCreatorForm;
import com.example.exercises.domain.model.Role;
import com.example.exercises.domain.model.User;
import com.example.exercises.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
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

    public void createUser(UserCreatorForm userCreatorForm){
        User user = new User();
        user.setFirstName(userCreatorForm.getFirstName());
        user.setLastName(userCreatorForm.getLastName());
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(userCreatorForm.getPassword()));
        user.setUsername(userCreatorForm.getUsername());
        userRepository.save(user);
    }

    public void createAdmin(UserCreatorForm userCreatorForm){
        User user = new User();
        user.setFirstName(userCreatorForm.getFirstName());
        user.setLastName(userCreatorForm.getLastName());
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN"), new Role("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(userCreatorForm.getPassword()));
        user.setUsername(userCreatorForm.getUsername());
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

    public void saveImage(MultipartFile imageFile) throws Exception {
        String folder = "src/main/resources/static/photos/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }
}
