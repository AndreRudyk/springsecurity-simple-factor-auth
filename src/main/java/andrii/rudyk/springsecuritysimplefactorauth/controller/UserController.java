package andrii.rudyk.springsecuritysimplefactorauth.controller;

import andrii.rudyk.springsecuritysimplefactorauth.model.Token;
import andrii.rudyk.springsecuritysimplefactorauth.repo.AppUserRepo;
import andrii.rudyk.springsecuritysimplefactorauth.repo.TokenRepo;
import andrii.rudyk.springsecuritysimplefactorauth.service.UserService;
import andrii.rudyk.springsecuritysimplefactorauth.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    private UserService userService;
    private TokenRepo tokenRepo;
    private AppUserRepo appUserRepo;

    public UserController(UserService userService, TokenRepo tokenRepo, AppUserRepo appUserRepo) {
        this.userService = userService;
        this.tokenRepo=tokenRepo;
        this.appUserRepo=appUserRepo;
    }
    //    @GetMapping("/hello")
//    @ResponseBody
//    public String hello(){
//        return "hello";
//    }

    @GetMapping("/hello")
    public String forUser(Principal principal, Model model){
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "hello";
    }

    @GetMapping("/sign-up")
    public String signup(Model model){
        model.addAttribute("user", new AppUser());
        return "sign-up";
    }
    @PostMapping("/register")
    public String register(AppUser appUser){
        userService.addUser(appUser);
        //System.out.println(appUser);
        return "sign-up";
    }

    @GetMapping("/token")
    @ResponseBody
    private String signup(@RequestParam String value){
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "hello";
    }


}
