package dev.attaphong.ecommerce_app_study.service;

import dev.attaphong.ecommerce_app_study.dto.UserDTO;
import dev.attaphong.ecommerce_app_study.model.Role;
import dev.attaphong.ecommerce_app_study.model.User;
import dev.attaphong.ecommerce_app_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(Role.USER);

        user.setUsername(userDTO.getUsername());

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setHashedPassword(hashedPassword);

        userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findUserByUsername(username);
        if(optUser.isEmpty()){
            throw new UsernameNotFoundException("not found");
        }
        return optUser.get();
    }
}
