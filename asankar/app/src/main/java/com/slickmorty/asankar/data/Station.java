package com.slickmorty.asankar.data;

public class Station {

  //Vars
  private int id;
  private String name;

  private String previousName = null;
  private Station previous = null;

  private String nextName = null;
  private Station next = null;

  private double weight = 0;

  private String productionLineName;
  private ProductionLine productionLine;


  //Constructor
  public Station(int id, String name, String productionLineName) {
    this.id = id;
    this.name = name;
    this.productionLineName = productionLineName;
    //    this.productionLine.addToArray(this);
  }


  //Fucnctions

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Station station = (Station) o;

    return getId() == station.getId();
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

  public Station getPrevious() {
    return previous;
  }

  public void setPrevious(Station previous) {
    this.previous = previous;
  }

  public Station getNext() {
    return next;
  }

  public void setNext(Station next) {
    this.next = next;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public ProductionLine getProductionLine() {
    return productionLine;
  }

  public void setProductionLine(ProductionLine productionLine) {
    this.productionLine = productionLine;
  }

  public String getProductionLineName() {
    return productionLineName;
  }

  public void setProductionLineName(String productionLineName) {
    this.productionLineName = productionLineName;
  }

  public String getPreviousName() {
    return previousName;
  }

  public void setPreviousName(String previousName) {
    this.previousName = previousName;
  }

  public String getNextName() {
    return nextName;
  }

  public void setNextName(String nextName) {
    this.nextName = nextName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

//  public void sync(String productionLineName, String previousName, String nextName) {
//    for (int i = 0; i < Data.stations.size(); i++) {
//      Station station = Data.stations.get(i);
//      if (station.getName().equals(previousName))
//        previous = station;
//      else if (station.getName().equals(nextName))
//        next = station;
//    }
//    for (int i = 0; i < Data.productionLines.size(); i++) {
//      ProductionLine productionLine = Data.productionLines.get(i);
//      if (productionLine.getName().equals(productionLineName))
//        this.productionLine = productionLine;
//    }
//  }
}