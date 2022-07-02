package com.slickmorty.asankar.data;


public class  BOM {

  //Variables
  private String Name;

  private String opName;
  private OP op = null;

  //Constructors
  public BOM(String Name , String opName) {
    this.Name = Name;
    this.opName= opName;
  }

  //Functions
  public String getName() {
    return Name;
  }

  public void setName(String name) {
    this.Name = name;
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
//    for(int i = 0; i< Data.ops.size(); i++){
//      if(Data.ops.get(i).getName().equals(opName)){
//        op= Data.ops.get(i);
//        break;
//      }
//    }
//  }

}
