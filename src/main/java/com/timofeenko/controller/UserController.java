package com.timofeenko.controller;

import com.timofeenko.model.User;
import com.timofeenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("allUsers", userList);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String showUserWithPathVariable(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/user")
    public String showUserWithRequestParam(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/user/edit")
    public String getEditingUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-form-edit";
    }

    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user?id=" + user.getId();
    }

    @RequestMapping("/user/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/user/new")
    public String getAddingUser(@ModelAttribute User user,  Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
        return "user-form-new";
    }

    @PostMapping ("/user/new")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user?id=" + user.getId();
    }


}
