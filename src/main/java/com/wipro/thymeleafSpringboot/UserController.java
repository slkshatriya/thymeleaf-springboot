package com.wipro.thymeleafSpringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class UserController {

    @GetMapping("/users")
    public String showUserForm() {
        return "user-form";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/users/" + id;
        User user = restTemplate.getForObject(url, User.class);
        model.addAttribute("users", user);
        return "users";
    }

    @PostMapping("/users")
    public String postUserForm(@RequestParam("id") Long id) {
        return "redirect:/users/" + id;
    }

}