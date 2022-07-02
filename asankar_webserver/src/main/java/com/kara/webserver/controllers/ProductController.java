package com.kara.webserver.controllers;

import com.kara.webserver.data.Data;
import com.kara.webserver.datatypes.Product;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.repositories.ProductRepository;
import com.kara.webserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
class ProductController {

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public @ResponseBody
  Iterable<Product> getProductList() {
    Data.syncProducts(productRepository.findAll());
    return Data.products;
  }

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<Product> getAllProducts() {
    return productRepository.findAll();
  }

  //TODO Addin need to get user type in the future
  @PostMapping("/add")
  public @ResponseBody
  String addNewProduct(@RequestParam int userName, @RequestParam String passWord, @RequestParam int productID
    , @RequestParam String productType) {

    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();
      if (user.getPassWord().equals(passWord)) {
        Product n = new Product(productID, productType);
        Data.products.add(n);
        productRepository.save(n);
        Data.syncProducts(productRepository.findAll());
        return "done";
      } else return "no access";
    } else return "no access";
  }

  @PostMapping("/delete")
  String deleteProduct(@RequestParam int userName, @RequestParam String passWord, @RequestParam int id) {
    if (userRepository.findById(userName).isPresent()) {
      if (userRepository.findById(userName).get().getPassWord().equals(passWord) && productRepository.findById(id).isPresent()) {
        Product product = productRepository.findById(id).get();
        Data.products.remove(product);
        productRepository.deleteById(id);
        Data.syncProducts(productRepository.findAll());
        return "done";
      } else return "no access";
    } else return "no access";
  }

  @PostMapping("/update")
  String updateProduct(@RequestParam int userName, @RequestParam String passWord, @RequestParam int productID
    , @RequestParam String productType, @RequestParam int id) {
    if (userRepository.findById(userName).isPresent()) {
      if (userRepository.findById(userName).get().getPassWord().equals(passWord) && productRepository.findById(id).isPresent()) {
        Product product = productRepository.findById(id).get();
        product.setProductID(productID);
        product.setProductType(productType);
        productRepository.save(product);
        Data.syncProducts(productRepository.findAll());
        return "done";
      } else return "no access";
    }
    return "no access";
  }
}
