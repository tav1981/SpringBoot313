
package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String printUsers(Model model) {
        List<User> users = userService.listUsers();

        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/admin/add")
    public String creatUsersForm(User user, Model model){
        List<Role> roles = roleService.listRole();
        //model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "add";
    }

    @PostMapping(value = "/admin/add")
    public String createUsers(@RequestParam String username,
                              @RequestParam String email, @RequestParam String password, @RequestParam Set<Role> roles
    ) {
        String encoderPassword = new BCryptPasswordEncoder(12).encode(password);
        User user = new User(username, email, "{bcrypt}" + encoderPassword, roles);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/edit/{id}")
    public String editUsersForm(@PathVariable("id") Long id, Model model){
        User user = userService.getForId(id);
        List<Role> roles = roleService.listRole();

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "edit";
    }

    @PostMapping(value = "admin/edit")
    public String editUsers(@RequestParam("id") User oldUser,
                            @RequestParam String username,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam Set<Role> roles) {

        if (password.indexOf("{bcrypt}") != -1) {
            oldUser.setPassword(password);
        } else {
            String encoderPassword = new BCryptPasswordEncoder(12).encode(password);
            oldUser.setPassword("{bcrypt}" + encoderPassword);
        }
        oldUser.setRoles(roles);
        oldUser.setUsername(username);
        oldUser.setEmail(email);
        userService.add(oldUser);
        return "redirect:/admin";
    }



    @GetMapping(value = "admin/delete/{deleteId:\\d+}")
    public String deleteUser(@PathVariable Long deleteId) {

        userService.dell(deleteId);
        return "redirect:/admin";
    }
}

