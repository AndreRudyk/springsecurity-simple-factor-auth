package andrii.rudyk.springsecuritysimplefactorauth;

import andrii.rudyk.springsecuritysimplefactorauth.model.AppUser;
import andrii.rudyk.springsecuritysimplefactorauth.repo.AppUserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder){
        AppUser appUserIvan = new AppUser();
        appUserIvan.setUsername("Ivan");
        appUserIvan.setPassword(passwordEncoder.encode("Ivan123"));
        appUserIvan.setRole("ROLE_ADMIN");
        appUserIvan.setEnabled(true);
        appUserRepo.save(appUserIvan);

        AppUser appUserBohdan = new AppUser();
        appUserBohdan.setUsername("Bohdan");
        appUserBohdan.setPassword(passwordEncoder.encode("Bohdan123"));
        appUserBohdan.setEnabled(true);
        appUserBohdan.setRole("ROLE_USER");
        appUserRepo.save(appUserBohdan);

    }
}
