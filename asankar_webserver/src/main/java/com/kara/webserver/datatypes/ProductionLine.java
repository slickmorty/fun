package com.kara.webserver.datatypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ProductionLine {

  //Vars

  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
  private String name;
  private String previous=null;
  private String next= null;
  private double weight = 0;

//Constructor

  public ProductionLine(String name) {
    this.name = name;
  }

  public ProductionLine() {  }


  //Functions
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
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

    ProductionLine that = (ProductionLine) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
