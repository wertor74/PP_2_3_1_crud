package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome (Model model) {
        String message = "CRUD приложение";
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping(value = "/users")
    public String showUsers(Model model) {
        model.addAttribute("user", userService.showUsers());
        return "users";
    }

    @GetMapping(value = "/{id}")
    public String showUserFromId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserFromId(id));
        return "user";
    }

    @GetMapping(value = "/add")
    public String addNewUser(@ModelAttribute("user") User user) {
        return "add";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserFromId(id));
        return "edit";
    }

    @PatchMapping(value = "/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }
}