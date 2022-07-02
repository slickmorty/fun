package com.kara.webserver.keyvaluepairs;


public class AllowPermission {

  private static AllowPermission instance;
  private String permission;

  public AllowPermission(boolean permission) {
    if(permission)
      this.permission="granted";
    else
      this.permission="notgranted";
  }


  public String getPermission() {
    return permission;
  }

  public void setPermission(boolean permission) {
    if(permission)
      this.permission="granted";
    else
      this.permission="notgranted";
  }

  public static AllowPermission getInstance(boolean permission) {
    if (instance == null)
      instance = new AllowPermission(permission);
    else
      instance.setPermission(permission);
    return instance;
  }
}
