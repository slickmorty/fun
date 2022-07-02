package com.kara.webserver.controllers;


import com.kara.webserver.data.Data;
import com.kara.webserver.datatypes.Station;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.repositories.StationRepository;
import com.kara.webserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("station")
class StationController {

  @Autowired
  private StationRepository stationRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public @ResponseBody
  Iterable<Station> getStationList() {
    Data.syncStations(stationRepository.findAll());
    return Data.stations;
  }

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<Station> getAllStations() {
    return stationRepository.findAll();
  }

  //TODO Addin need to get user type in the future
  @PostMapping("/add")
  public @ResponseBody
  String addNewStation(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name,
                       @RequestParam String previous, @RequestParam String next, @RequestParam double weight,
                       @RequestParam String productionLine) {
    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();
      if (user.getPassWord().equals(passWord)) {
        Station n = new Station(name, productionLine);
        n.setPrevious(previous);
        n.setNext(next);
        n.setWeight(weight);
        stationRepository.save(n);
        Data.syncStations(stationRepository.findAll());
        return "done";
      } else return "no access";
    } else return "no access";
  }

  @PostMapping("/delete")
  String deleteStation(@RequestParam int userName , @RequestParam String passWord , @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && stationRepository.findById(id).isPresent()){
        stationRepository.deleteById(id);
        Data.syncStations(stationRepository.findAll());
        return "done";
      }
      else return "no access";
    }
    else return "no access";
  }

  @PostMapping("/update")
  String updateStation(@RequestParam int userName, @RequestParam String passWord, @RequestParam String name,
                       @RequestParam String previous, @RequestParam String next, @RequestParam double weight,
                       @RequestParam String productionLine , @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && stationRepository.findById(id).isPresent()){
        Station station = stationRepository.findById(id).get();
        station.setName(name);
        station.setProductionLine(productionLine);
        station.setPrevious(previous);
        station.setNext(next);
        station.setWeight(weight);
        stationRepository.save(station);
        Data.syncStations(stationRepository.findAll());
        return "done";
      }
      else return "no access";
    }
    return "no access";
  }
}
