package com.kara.webserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserverApplication {
  @Autowired

  public static void main(String[] args) {
    SpringApplication.run(WebserverApplication.class, args);

  }

}
