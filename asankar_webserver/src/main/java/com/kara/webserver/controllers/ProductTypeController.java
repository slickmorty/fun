package com.kara.webserver.controllers;

import com.kara.webserver.data.Data;
import com.kara.webserver.datatypes.ProductType;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.repositories.PrTypeRepository;
import com.kara.webserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producttype")
class ProductTypeController {

  @Autowired
  private PrTypeRepository prTypeRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public @ResponseBody
  Iterable<ProductType> getProductTypeList() {
    Data.syncProductTypes(prTypeRepository.findAll());
    return Data.productTypes;
  }

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<ProductType> getAllPrTypes() {
    return prTypeRepository.findAll();
  }

  //TODO Addin need to get user type in the future
  @PostMapping("/add")
  public @ResponseBody
  String addNewProductType(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name) {

    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();
      if (user.getPassWord().equals(passWord)) {
        ProductType n = new ProductType(name);
        Data.productTypes.add(n);
        prTypeRepository.save(n);
        Data.syncProductTypes(prTypeRepository.findAll());
        return "done";
      } else return "no access";
    } else return "no access";
  }

  @PostMapping("/delete")
  String deleteProductType(@RequestParam int userName , @RequestParam String passWord , @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && prTypeRepository.findById(id).isPresent()){
        ProductType productType = prTypeRepository.findById(id).get();
        Data.productTypes.remove(productType);
        prTypeRepository.deleteById(id);
        Data.syncProductTypes(prTypeRepository.findAll());
        return "done";
      }
      else return "no access";
    }
    else return "no access";
  }

  @PostMapping("/update")
  String updateProductType(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name ,
                           @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && prTypeRepository.findById(id).isPresent()){
        ProductType productType=prTypeRepository.findById(id).get();
        productType.setName(name);
        prTypeRepository.save(productType);
        Data.syncProductTypes(prTypeRepository.findAll());
        return "done";
      }
      else return "no access";
    }
    return "no access";
  }

}
