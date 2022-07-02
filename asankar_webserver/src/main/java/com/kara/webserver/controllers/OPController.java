package com.kara.webserver.controllers;

import com.kara.webserver.data.Data;
import com.kara.webserver.datatypes.OP;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.repositories.OPRepository;
import com.kara.webserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("op")
class OPController {

  @Autowired
  private OPRepository opRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public @ResponseBody
  Iterable<OP> getOPsList() {
    Data.syncOPs(opRepository.findAll());
    return Data.ops;
  }

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<OP> getAllOPs() {
    return opRepository.findAll();
  }


  //TODO Addin need to get user type in the future
  @PostMapping("/add")
  public @ResponseBody
  String addNewOP(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name,
                  @RequestParam String previous, @RequestParam String next, @RequestParam double weight,
                  @RequestParam String station, @RequestParam String bOM, @RequestParam String instruction,
                  @RequestParam String productType) {
    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();
      if (user.getPassWord().equals(passWord)) {
        OP n = new OP(name, productType, station);
        n.setPrevious(previous);
        n.setNext(next);
        n.setWeight(weight);
        n.setbOM(bOM);
        n.setInstruction(instruction);
        Data.ops.add(n);
        opRepository.save(n);
        return "done";
      } else return "no access";
    } else return "no access";
  }

  @PostMapping("/delete")
  String deleteOP(@RequestParam int userName , @RequestParam String passWord , @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && opRepository.findById(id).isPresent()){
        OP op = opRepository.findById(id).get();
        Data.ops.remove(op);
        opRepository.deleteById(id);
        return "done";
      }
      else return "no access";
    }
    else return "no access";
  }

  @PostMapping("/update")
  String updateOP(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name,
                  @RequestParam String previous, @RequestParam String next, @RequestParam double weight,
                  @RequestParam String station, @RequestParam String bOM, @RequestParam String instruction,
                  @RequestParam String productType , @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && opRepository.findById(id).isPresent()){
        OP op = opRepository.findById(id).get();
        op.setName(name);
        op.setStation(station);
        op.setProductType(productType);
        op.setPrevious(previous);
        op.setNext(next);
        op.setWeight(weight);
        op.setbOM(bOM);
        op.setInstruction(instruction);
        opRepository.save(op);
        Data.syncOPs(opRepository.findAll());
        return "done";
      }
      else return "no access";
    }
    return "no access";
  }

}

