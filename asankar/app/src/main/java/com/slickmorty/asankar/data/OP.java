package com.slickmorty.asankar.data;

public class OP {

  //Vars
  private int id;
  private String name;

  private String productTypeName ;
  private ProductType productType = null;

  private String stationName;
  private Station station= null;

  private String nextName = null;
  private OP next = null;

  private String previousName = null;
  private OP previous = null;

  private double weight = 0;

  private String instructionName = null;
  private Instruction instruciton = null;

  private String bOMName = null;
  private BOM bOM = null;

  //Constructors

  public OP(int id, String name, String productType, String station) {
    this.id = id;
    this.name = name;
    this.productTypeName = productType;
    this.stationName = station;
  }

  //Functions

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OP op = (OP) o;

    return getId() == op.getId();
  }

  @Override
  public int hashCode() {
    return getId();
  }

  public String getInstructionName() {
    return instructionName;
  }

  public void setInstructionName(String instructionName) {
    this.instructionName = instructionName;
  }

  public String getbOMName() {
    return bOMName;
  }

  public void setbOMName(String bOMName) {
    this.bOMName = bOMName;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public String getNextName() {
    return nextName;
  }

  public void setNextName(String nextName) {
    this.nextName = nextName;
  }

  public OP getPrevious() {
    return previous;
  }

  public void setPrevious(OP previous) {
    this.previous = previous;
  }

  public BOM getbOM() {
    return bOM;
  }

  public void setbOM(BOM bOM) {
    this.bOM = bOM;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OP getNext() {
    return next;
  }

  public void setNext(OP next) {
    this.next = next;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public Instruction getInstruciton() {
    return instruciton;
  }

  public void setInstruciton(Instruction instruciton) {
    this.instruciton = instruciton;
  }

  public Station getStation() {
    return station;
  }

  public void setStation(Station station) {
    this.station = station;
  }

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  public String getPreviousName() {
    return previousName;
  }

  public void setPreviousName(String previousName) {
    this.previousName = previousName;
  }

//  public void sync(String productTypeName,String stationName , String previousName,String nextName , String bOMName , String instructionName){
//
//    for(int i=0; i<Data.productTypes.size();i++){
//      ProductType productType = Data.productTypes.get(i);
//      if(productType.getName().equals(productTypeName)){
//        this.productType= productType;
//        break;
//      }
//    }
//
//    for (int i = 0; i < Data.stations.size(); i++) {
//      Station station = Data.stations.get(i);
//      if (station.getName().equals(stationName)){
//        this.station = station;
//        break;
//      }
//    }
//
//    for(int i=0;i<Data.ops.size();i++){
//      com.morty.version_01.data.OP op = Data.ops.get(i);
//      if (op.getName().equals(previousName))
//        previous = op;
//      else if(op.getName().equals(nextName))
//        next = op;
//    }
//
//    for (int i = 0; i < Data.boms.size(); i++) {
//      BOM bom = Data.boms.get(i);
//      if (bom.getName().equals(bOMName)){
//        this.bOM = bom;
//        break;
//      }
//    }
//
//    for (int i = 0; i < Data.instructions.size(); i++) {
//      com.morty.version_01.data.Instruction instruction = Data.instructions.get(i);
//      if (instruction.getName().equals(instructionName)){
//        this.instruciton = instruction;
//        break;
//      }
//    }
//  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
