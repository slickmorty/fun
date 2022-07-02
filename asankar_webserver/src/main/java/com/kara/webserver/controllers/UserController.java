package com.kara.webserver.controllers;


import com.kara.webserver.data.Data;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.keyvaluepairs.AllowPermission;
import com.kara.webserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<User> getAllusers() {
    Data.users.clear();
    for(User user : userRepository.findAll()){
      User n = new User();
      n.setIdentification(user.getIdentification());
      n.setFamilyName(user.getFamilyName());
      n.setName(user.getName());
      n.setUserType(user.getUserType());
      n.setUserName(0);
      n.setPassWord(null);
      Data.users.add(n);
    }
    //TODO Adding console to all of them
//    System.out.print(Data.users);
    return Data.users;
  }


  @GetMapping(path = "/auth/username={username}&password={passWord}")
  AllowPermission getUserAuth(@PathVariable int username, @PathVariable String passWord) {

    boolean flag = false;
    if (userRepository.findById(username).isPresent()) {
      User user = userRepository.findById(username).get();
      if (user.getPassWord().equals(passWord)) {
        flag = true;
      }
    }
    return AllowPermission.getInstance(flag);
  }

  @GetMapping(path = "/get/username={username}&password={passWord}")
  User getUserInfo(@PathVariable int username, @PathVariable String passWord){
    if(userRepository.findById(username).isPresent())
    {
      User user = userRepository.findById(username).get();
      if( user.getPassWord().equals(passWord)) {
        user.setPassWord(null);
        user.setUserName(-1);
        return user;
      }
      else return null;
    }
    else
      return null;
  }

  @PostMapping("/exist")
  public @ResponseBody
  String userExist(@RequestParam int userName, @RequestParam String passWord, @RequestParam int identification) {

    Data.syncUsers(userRepository.findAll());
    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();
      if (user.getPassWord().equals(passWord)) {
        for(User user1 : Data.users){
          if (user1.getIdentification() == identification)
            return "yes";
        }
      }
    }
    return "no";
  }

  @PostMapping("/add")
  public @ResponseBody
  String addNewUser(@RequestParam int admin, @RequestParam String adminPass, @RequestParam int userName,
                       @RequestParam String passWord, @RequestParam String name,@RequestParam String familyName,
                    @RequestParam int identification,@RequestParam String userType) {

    if (userRepository.findById(admin).isPresent()) {
      User user = userRepository.findById(admin).get();
      if (user.getPassWord().equals(adminPass)) {
        User n = new User( userName,  passWord, name,  familyName, identification ,  userType) ;
        userRepository.save(n);
        System.out.println("name is " +name+"     "+"and family name is " +familyName);
        return "done";
      } else return "no access";
    } else return "no access";
  }
}

