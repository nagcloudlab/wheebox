package com.example.demo;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    private final FakeUserRepository userRepo;
    private final CustomUserDetailsService userDetailsService;

    public SignupController(FakeUserRepository userRepo, CustomUserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam String username,
                                @RequestParam String password) {

        // 1. Prevent duplicate usernames
        if (userRepo.exists(username)) {
            return "redirect:/signup?error=exists";
        }

        // 2. Save user in our repo (mock database)
        UserEntity newUser = new UserEntity(username, "{noop}"+password, "USER");
        userRepo.save(newUser);

        // 3. Load user details from our custom service
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 4. Programmatically log in the new user
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/";
    }
}
