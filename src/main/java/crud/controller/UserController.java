package crud.controller;

import crud.dao.UserDao;
import crud.model.User;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

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
        return null;
    }

    @GetMapping(value = "/add")
    public String addNewUser(@ModelAttribute("user") User user) {
/*        User user1 = new User("Алексей", "Владимирович", "Цуцкарёв", LocalDate.of(1974, 10, 23));
        userService.addUser(user1);*/
        System.out.println("addNewUser = " + user);
        return "add";
    }

    @PostMapping(value = "/add")
    public String createUser(@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserFromId(id));
        return "edit";
    }

    @PostMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {

        return "redirect:/users";
    }
}
