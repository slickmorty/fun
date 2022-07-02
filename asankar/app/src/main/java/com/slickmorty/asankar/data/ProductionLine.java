package com.slickmorty.asankar.data;

public class ProductionLine  {
  //Vars
  private int id;
  private String name;

  private String previousName =null;
  private ProductionLine previous = null;

  private String nextName = null;
  private ProductionLine next = null;

  private double weight = 0;

  //Constructors


  public ProductionLine(int id, String name) {
    this.id=id;
    this.name = name;
  }

  //Functions
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ProductionLine that = (ProductionLine) o;

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

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }


  public ProductionLine getPrevious() {
    return previous;
  }

  public void setPrevious(ProductionLine previous) {
    this.previous = previous;
  }

  public ProductionLine getNext() {
    return next;
  }

  public void setNext(ProductionLine next) {
    this.next = next;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

//  public void sync(String previousName , String nextName){
//
//    for (int i = 0; i < Data.productionLines.size(); i++) {
//      ProductionLine productionLine = Data.productionLines.get(i);
//      if (productionLine.getName().equals(previousName))
//        previous = productionLine;
//      else if (productionLine.getName().equals(nextName))
//        next = productionLine;
//    }
//  }
}
