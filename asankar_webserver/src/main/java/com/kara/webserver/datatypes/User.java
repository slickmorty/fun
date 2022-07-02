package com.kara.webserver.datatypes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {


  //Variables
//  private @Id @GeneratedValue Integer id;

  private @Id int userName;
  private String passWord=null;
  private String name=null;
  private String familyName=null;
  private int identification=-1;
  private String userType=null;


  //Constructors
  public User(int userName, String passWord, String name, String familyName, int identification, String userType) {
    this.userName = userName;
    this.passWord = passWord;
    this.name = name;
    this.familyName = familyName;
    this.identification = identification;
    this.userType = userType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public int getIdentification() {
    return identification;
  }

  public void setIdentification(int identification) {
    this.identification = identification;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public User() {
  }


  //Functions

  public int getUserName() {
    return userName;
  }

  public void setUserName(int userName) {
    this.userName = userName;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }


}
