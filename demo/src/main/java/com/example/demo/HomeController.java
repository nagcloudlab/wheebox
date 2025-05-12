package com.example.demo;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal, HttpServletRequest request) {

        // String user = SecurityContextHolder.getContext().getAuthentication().getName();
        // System.out.println("User: " + user);
        // List<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        //         .stream()
        //         .map(Object::toString)
        //         .toList();
        // System.out.println("Roles: " + roles);

        // // You can also use the Principal object directly
        // if (principal != null) {
        //     System.out.println("Principal: " + principal.getName());
        // } else {
        //     System.out.println("Principal is null");
        // }

        // // check user role
        // if (request.isUserInRole("ADMIN")) {
        //     System.out.println("User is in role ADMIN");
        // } else {
        //     System.out.println("User is NOT in role ADMIN");
        // }


        return "home"; // This will resolve to src/main/resources/templates/home.html
    }

}
