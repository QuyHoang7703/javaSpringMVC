package vn.hoidanit.laptopshop.controller;

import java.util.List;

// import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class UserController {
    //DI: dependence injection
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model){
        List<User> arrUsers = this.userService.getAllUsersByEmail("quy@gmail.com");
        System.out.println(arrUsers);
        String test = this.userService.handleHello();
        model.addAttribute("eric", test);
        model.addAttribute("hoidanit", "Hello from controller");
        
        return "hello" ;
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model){
        String test = this.userService.handleHello();
        model.addAttribute("newUser", new User());
        return "admin/user/table-user" ;
    }

    @RequestMapping("/admin/user/create") //GET
    public String getCreateUserPage(Model model){
        String test = this.userService.handleHello();
        model.addAttribute("newUser", new User());
        return "admin/user/create" ;
    }

    @RequestMapping(value="/admin/user/create", method=RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit){
        System.out.println("run java: "+ hoidanit);
        this.userService.handleSaveUser(hoidanit);
        return "hello";
    }
}

// @RestController
// public class UserController {
//     //DI: dependence injection
//     private UserService userService;
    
//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     @GetMapping("/")
//     public String getHomePage(){
//         return this.userService.handleHello() ;
//     }
// }
