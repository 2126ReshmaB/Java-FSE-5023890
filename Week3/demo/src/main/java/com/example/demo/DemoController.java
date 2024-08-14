package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class DemoController {
  @RequestMapping("/")
  String displayDefaultMessage(){
    return "Hellow Spring";
  }
  
}
