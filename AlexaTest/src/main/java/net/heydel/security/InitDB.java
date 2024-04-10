package net.heydel.security;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.heydel.user.domain.User;
import net.heydel.user.domain.UserRepository;

@Component
public class InitDB {

    @Autowired
    private UserRepository userReposiotry;

    private boolean debug = true;
    @Transactional
    public void init(){
        if(debug){
            System.out.println("InitDB.init()");
            User admin = new User("Admin", "admin", "test123", new HashSet<UserRole>(
					Arrays.asList(UserRole.ADMIN)));
            userReposiotry.save(admin);
        }
    }
}
