package com.kara.webserver.datatypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
  //Vars
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
  private int productID;
  private String productType;

//Constructor

  public Product( int productID, String productType) {
    this.productID = productID;
    this.productType = productType;
  }

  public Product() {
  }


  //Fucntions
  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }
}
