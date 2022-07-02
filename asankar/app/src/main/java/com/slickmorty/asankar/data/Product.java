package com.slickmorty.asankar.data;


public class Product {

  //Vars
  private int id;
  private int productID;
  private String productTypeName;
  private ProductType productType =null;

  //Constructor
  public Product(int id, int productID, String productTypeName) {
    this.id = id;
    this.productID = productID;
    this.productTypeName = productTypeName;
  }

  //Fucntions
  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }


  public void setProductID(int productID) {
    this.productID = productID;
  }
  public int getProductID() {
    return productID;
  }

  //  public void sync(String productTypeName){
//    for(int i = 0; i< Data.productTypes.size(); i++){
//      if(Data.productTypes.get(i).getName().equals(productTypeName)){
//        productType= Data.productTypes.get(i);
//        break;
//      }
//    }
//  }

}
