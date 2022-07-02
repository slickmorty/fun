package com.kara.webserver.datatypes;

public class Instruction {


  private String instructionName;

  public Instruction(String instructionName) {
    this.instructionName = instructionName;
  }



  public String getInstructionName() {
    return instructionName;
  }

  public void setInstructionName(String instructionName) {
    this.instructionName = instructionName;
  }
}
