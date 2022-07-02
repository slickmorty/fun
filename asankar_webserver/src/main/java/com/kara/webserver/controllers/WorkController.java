package com.kara.webserver.controllers;

import com.kara.webserver.data.Data;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.datatypes.Work;
import com.kara.webserver.repositories.UserRepository;
import com.kara.webserver.repositories.WorkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;

@RestController
@RequestMapping("/work")
class WorkController {

  @Autowired
  private WorkRepository workRepository;
  @Autowired
  private UserRepository userRepository;

  //TODO ADD SECURITY
  //TODO when works are done they need to be deleted
  @GetMapping(path = "/get/username={userName}&password={passWord}")
  public @ResponseBody
  Iterable<Work> getWorks(@PathVariable int userName, @PathVariable String passWord) {
    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();

      if (user.getPassWord().equals(passWord)) {
        if (user.getUserType().equals("production")){
          ArrayList<Work> works = new ArrayList<>();
          for(Work work : workRepository.findAll()){
              if(work.getWorkerID() == user.getIdentification())
                works.add(work);
          }
          return works;
        }
        else if(user.getUserType().equals("qc")||user.getUserType().equals("admin")
          ||user.getUserType().equals("planning")||user.getUserType().equals("engineering"))
          return workRepository.findAll();
      }
    }
    return null;
  }

  @PostMapping("/add")
  public @ResponseBody
  String addNewWork(@RequestParam int userName, @RequestParam String passWord, @RequestParam String opName,
                    @RequestParam int productID, @RequestParam int workerID, @RequestParam long planStartDate,
                    @RequestParam long planFinishDate , @RequestParam String planDescription) {
    if (userRepository.findById(userName).isPresent()) {
      User user = userRepository.findById(userName).get();
      if (user.getPassWord().equals(passWord)) {
        Work n = new Work(opName, productID,workerID,new Date(planStartDate),new Date(planFinishDate));
        n.setPlanDescription(planDescription);
        workRepository.save(n);
        Data.syncWorks(workRepository.findAll());
        return "done";
      }
    }
    return "no access";
  }

  @PostMapping("/delete")
  String deleteWork(@RequestParam int userName , @RequestParam String passWord , @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && workRepository.findById(id).isPresent()){
        Work work = workRepository.findById(id).get();
        Data.works.remove(work);
        workRepository.deleteById(id);
        return "done";
      }
      else return "no access";
    }
    else return "no access";
  }

  @PostMapping("/update")
  String updateWork(@RequestParam int userName, @RequestParam String passWord, @RequestParam String opName,
                    @RequestParam int productID, @RequestParam int workerID, @RequestParam long planStartDate,
                    @RequestParam long planFinishDate ,@RequestParam long workerStartDate ,@RequestParam long workerFinishDate ,
                    @RequestParam long finishDate ,@RequestParam boolean qcCheck ,@RequestParam boolean workerCheck ,
                    @RequestParam double progress , @RequestParam String qcDescription , @RequestParam String planDescription ,
                    @RequestParam String workerDescription , @RequestParam int id){
    if(userRepository.findById(userName).isPresent()){
      if(userRepository.findById(userName).get().getPassWord().equals(passWord) && workRepository.findById(id).isPresent()){
        Work work = workRepository.findById(id).get();
        work.setOpName(opName);
        work.setProductID(productID);
        work.setWorkerID(workerID);
        work.setPlanStartDate(new Date(planStartDate));
        work.setPlanFinishDate(new Date(planFinishDate));
        work.setWorkerStartDate(new Date(workerStartDate));
        work.setWorkerFinishDate(new Date(workerFinishDate));
        work.setFinishDate(new Date(finishDate));
        work.setQcCheck(qcCheck);
        work.setWorkerCheck(workerCheck);
        work.setProgress(progress);
        work.setPlanDescription(planDescription);
        work.setQcDescription(qcDescription);
        work.setWorkerDescription(workerDescription);
        workRepository.save(work);
        Data.syncWorks(workRepository.findAll());
        return "done";
      }
      else return "no access";
    }
    return "no access";
  }

}
