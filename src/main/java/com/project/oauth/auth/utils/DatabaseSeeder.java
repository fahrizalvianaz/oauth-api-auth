package com.project.oauth.auth.utils;



import com.project.oauth.auth.model.Role;
import com.project.oauth.auth.model.RolePath;
import com.project.oauth.auth.model.Users;
import com.project.oauth.auth.repository.RolePathRepository;
import com.project.oauth.auth.repository.RoleRepository;
import com.project.oauth.auth.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Service
public class DatabaseSeeder implements ApplicationRunner {

    private static final String TAG = "DatabaseSeeder {}";

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UsersRepository usersRepository;


    @Autowired
    private RolePathRepository rolePathRepository;

    private String defaultPassword = "password";

    private String[] users = new String[]{
            "admin@mail.com:ROLE_SUPERUSER ROLE_USER ROLE_ADMIN",
            "user@mail.com:ROLE_USER"
    };

    private String[] clients = new String[]{
            "my-client-apps:ROLE_READ ROLE_WRITE", // mobile
            "my-client-web:ROLE_READ ROLE_WRITE" // web
    };

    private String[] roles = new String[] {
            "ROLE_SUPERUSER:user_role:^/.*:GET|PUT|POST|PATCH|DELETE|OPTIONS",
            "ROLE_ADMIN:user_role:^/.*:GET|PUT|POST|PATCH|DELETE|OPTIONS",
            "ROLE_USER:user_role:^/.*:GET|PUT|POST|PATCH|DELETE|OPTIONS",
            "ROLE_READ:oauth_role:^/.*:GET|PUT|POST|PATCH|DELETE|OPTIONS",
            "ROLE_WRITE:oauth_role:^/.*:GET|PUT|POST|PATCH|DELETE|OPTIONS"
    };


    @Override
    @Transactional
    public void run(ApplicationArguments applicationArguments) {
        this.insertRoles();
        Users user = new Users();
        user.setUsername("user");
        user.setPassword("$2a$12$ropNanHtYdMoMaYcq7nN9eFHFSVF3rMfF13W3vjtLnRuc2r2IrEjy");
        user.setEnabled(true);
        user.setEmail("user@gmail.com");
        usersRepository.save(user);
    }

    @Transactional
    public void insertRoles() {
        for (String role: roles) {
            String[] str = role.split(":");
            String name = str[0];
            String type = str[1];
            String pattern = str[2];
            String[] methods = str[3].split("\\|");
            Role oldRole = roleRepository.findOneByName(name);
            if (null == oldRole) {
                oldRole = new Role();
                oldRole.setName(name);
                oldRole.setType(type);
                oldRole.setRolePaths(new ArrayList<>());
                for (String m: methods) {
                    String rolePathName = name.toLowerCase()+"_"+m.toLowerCase();
                    RolePath rolePath = rolePathRepository.findOneByName(rolePathName);
                    if (null == rolePath) {
                        rolePath = new RolePath();
                        rolePath.setName(rolePathName);
                        rolePath.setMethod(m.toUpperCase());
                        rolePath.setPattern(pattern);
                        rolePath.setRole(oldRole);
                        rolePathRepository.save(rolePath);;
                        oldRole.getRolePaths().add(rolePath);
                    }
                }
            }

            roleRepository.save(oldRole);
        }
    }
}

