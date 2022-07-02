package com.kara.webserver.datatypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OP {


  //Vars
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
  private String name;
  private String productType;
  private String station;
  private String previous = null;
  private String next = null;
  private double weight = 0f;
  private String instruction = null;
  private String bOM = null;

  //Constructor
  public OP(String name, String productType, String station) {
    this.name = name;
    this.productType = productType;
    this.station = station;
  }

  public OP() {

  }

  //Functions
  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public String getbOM() {
    return bOM;
  }

  public void setbOM(String bOM) {
    this.bOM = bOM;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruciton) {
    this.instruction = instruciton;
  }

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OP op = (OP) o;

    return id.equals(op.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }


}
