package com.slickmorty.asankar.data;


public class Instruction {

  //Vars
  private String name;

  private String opName;
  private OP op = null;

  //Constructor
  public Instruction(String name , String opName) {
    this.name = name;
    this.opName= opName;
  }

  //Functions
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String getOpName() {
    return opName;
  }

  public void setOpName(String opName) {
    this.opName = opName;
  }

  public OP getOp() {
    return op;
  }

  public void setOp(OP op) {
    this.op = op;
  }

//  public void sync(String opName){
//    for(int i=0; i< Data.ops.size();i++){
//      if(Data.ops.get(i).getName().equals(opName)){
//        op= Data.ops.get(i);
//        break;
//      }
//    }
//  }

}
