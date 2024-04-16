package io.superfarm;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder;

    private static final String SECRET_KEY = "very_secret_key";
    private static final long EXPIRATION_TIME = 900000; // 15 minutes

    public String registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return "User already exists!";
        }

        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, encodedPassword);
        userRepository.save(newUser);
        return "User registered successfully!";
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "User not found!";
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            return generateToken(username);
        } else {
            return "Invalid password!";
        }
    }

    private String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
}

