package com.kara.webserver.data;

import com.kara.webserver.datatypes.OP;
import com.kara.webserver.datatypes.Product;
import com.kara.webserver.datatypes.ProductType;
import com.kara.webserver.datatypes.ProductionLine;
import com.kara.webserver.datatypes.Station;
import com.kara.webserver.datatypes.User;
import com.kara.webserver.datatypes.Work;

import java.util.ArrayList;

public class Data {
  public static ArrayList<ProductionLine> productionLines = new ArrayList<>();
  public static ArrayList<Station> stations = new ArrayList<>();
  public static ArrayList<OP> ops = new ArrayList<>();
  public static ArrayList<ProductType> productTypes = new ArrayList<>();
  public static ArrayList<Product> products = new ArrayList<>();
  public static ArrayList<User> users = new ArrayList<>();
  public static ArrayList<Work> works = new ArrayList<>();


  public static void syncProductionLines(Iterable<ProductionLine> productionLines){
    Data.productionLines.clear();
    for(ProductionLine productionLine : productionLines){
      Data.productionLines.add(productionLine);
    }
  }
  public static void syncStations(Iterable<Station> stations){
    Data.stations.clear();
    for(Station station : stations){
      Data.stations.add(station);
    }
  }
  public static void syncOPs(Iterable<OP> ops){
    Data.ops.clear();
    for(OP op : ops){
      Data.ops.add(op);
    }
  }
  public static void syncProductTypes(Iterable<ProductType> productTypes){
    Data.productTypes.clear();
    for(ProductType productType : productTypes){
      Data.productTypes.add(productType);
    }
  }
  public static void syncProducts(Iterable<Product> products){
    Data.products.clear();
    for(Product product : products) Data.products.add(product);
  }
  public static void syncUsers(Iterable<User> users){
    Data.products.clear();
    for(User user : users) Data.users.add(user);
  }
  public static void syncWorks(Iterable<Work> works){
    Data.works.clear();
    for(Work work : works) Data.works.add(work);
  }
}
