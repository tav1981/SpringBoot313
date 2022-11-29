package web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "/index")
    public String homePage() {
        return "index";
    }



    /*@GetMapping("/user")
    public String userPage(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "юзер :" + principal.getName() + " и : " + authentication.getName();
    }*/
}
