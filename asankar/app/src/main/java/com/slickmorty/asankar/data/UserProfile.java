package com.slickmorty.asankar.data;

public class UserProfile {

  //Variables
  private String name ;
  private String familyName;
  private int identification;
  private String userType;
  private int userName = -1;
  private String passWord = null;

  //Constructors

  public UserProfile(int userName, String passWord) {
    this.userName = userName;
    this.passWord = passWord;
  }
  public UserProfile(){

  }

  //Functions
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
