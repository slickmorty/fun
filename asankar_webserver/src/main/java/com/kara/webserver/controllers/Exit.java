package com.kara.webserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.System.exit;

@RestController
@RequestMapping("end")
class Exit {

  @GetMapping(path = "/username={username}&password={passWord}")
  void endProgram(@PathVariable String username , @PathVariable String passWord){
    if ( username.equals("endtheprogram") && passWord.equals("1152015fuckoffplz:D")) {
      System.out.println("The Program Is ending");
      exit(0);
    }
  }

}
