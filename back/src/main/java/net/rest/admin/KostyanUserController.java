package net.rest.admin;

import net.exception.UserException;
import net.model.Role;
import net.model.User;
import net.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/api/admin/users")
@PreAuthorize("hasAuthority('KOSTYAN')")
public class KostyanUserController {

    private UsersService usersService;

    public KostyanUserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {
        usersService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        Set<Role> userRoles = new HashSet<Role>();
        Role[] roles = Role.values();
        for (Role role : roles) {
            userRoles.contains(Role.valueOf())
        }
    }
}
