package com.slickmorty.asankar.data;

public class ProductType {

//Vars
  private int id;
  private String name;

//Constructor
  public ProductType(int id,String name) {
    this.id=id;
    this.name = name;
  }

//Functions

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ProductType that = (ProductType) o;

    return getId() == that.getId();
  }

  @Override
  public int hashCode() {
    return getId();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
