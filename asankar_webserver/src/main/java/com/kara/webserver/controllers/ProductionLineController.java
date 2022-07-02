package com.kara.webserver.controllers;


import com.kara.webserver.data.Data;
import com.kara.webserver.datatypes.ProductionLine;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.repositories.PrLineRepository;
import com.kara.webserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/productionline")
class ProductionLineController {

  @Autowired
  private PrLineRepository prLineRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public @ResponseBody
  Iterable<ProductionLine> getPrLinesList() {
      Data.syncProductionLines(prLineRepository.findAll());
      return Data.productionLines;
  }

  @GetMapping("/all")
  public @ResponseBody
  Iterable<ProductionLine> getAllPrLines() {
    return prLineRepository.findAll();
  }

  //TODO Addin need to get user type in the future
  @PostMapping("/add")
  public @ResponseBody
  String addNewPrLine(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name,
                      @RequestParam String previous, @RequestParam String next, @RequestParam double weight) {

    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();
      if (user.getPassWord().equals(passWord)) {
        ProductionLine productionLine = new ProductionLine();
        productionLine.setName(name);
        productionLine.setPrevious(previous);
        productionLine.setNext(next);
        productionLine.setWeight(weight);
        prLineRepository.save(productionLine);
        Data.syncProductionLines(prLineRepository.findAll());
        return "done";
      } else
        return "no access";
    } else
      return "no access";
  }

  @PostMapping("/delete")
  String deletePrLine(@RequestParam int userName, @RequestParam String passWord, @RequestParam int id) {
    if (userRepository.findById(userName).isPresent()) {
      if (userRepository.findById(userName).get().getPassWord().equals(passWord) && prLineRepository.findById(id).isPresent()) {
        prLineRepository.deleteById(id);
        Data.syncProductionLines(prLineRepository.findAll());
        return "done";
      } else return "no access";
    } else return "no access";
  }

  @PostMapping("/update")
  String updatePrLine(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name,
                      @RequestParam String previous, @RequestParam String next, @RequestParam double weight,
                      @RequestParam int id) {

    if (userRepository.findById(userName).isPresent()) {
      if (userRepository.findById(userName).get().getPassWord().equals(passWord) && prLineRepository.findById(id).isPresent()) {
        ProductionLine productionLine=prLineRepository.findById(id).get();
        productionLine.setName(name);
        productionLine.setPrevious(previous);
        productionLine.setNext(next);
        productionLine.setWeight(weight);
        prLineRepository.save(productionLine);
        Data.syncProductionLines(prLineRepository.findAll());
        return "done";
      } else return "no access";
    }
    return "no access";
  }
}
