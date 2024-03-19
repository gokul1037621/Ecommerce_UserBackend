package org.example.Controller;

import org.bson.types.ObjectId;
import org.example.Model.User;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public User register(@RequestBody User user){
        User res=userService.register(user);
        return res;

    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        return userService.userLogin(userEmail, userPassword);
    }
    @GetMapping("/get_user")
    public List<User> getallmovie(){
        return userService.save();
    }

    @PostMapping("/updateuser/{id}")
    public User update(@RequestBody User user, @PathVariable String id){
        User newUser = user;
        System.out.println("Id of new user is " + newUser.getId());
        if(userService.findById(id).isEmpty()){
            User updatedUser = userService.updateUser(user);
            return updatedUser;
        }
        System.out.println("User does not exists");
        return null;
    }

    @DeleteMapping("deleteuser/{id}")
    public void deleteuser(@PathVariable String id){
        userService.deleteuser(id);
    }

}
