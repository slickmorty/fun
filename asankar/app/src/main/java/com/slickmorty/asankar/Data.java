package com.slickmorty.asankar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.slickmorty.asankar.data.BOM;
import com.slickmorty.asankar.data.Constants;
import com.slickmorty.asankar.data.Instruction;
import com.slickmorty.asankar.data.OP;
import com.slickmorty.asankar.data.Product;
import com.slickmorty.asankar.data.ProductType;
import com.slickmorty.asankar.data.ProductionLine;
import com.slickmorty.asankar.data.Station;
import com.slickmorty.asankar.data.UserProfile;
import com.slickmorty.asankar.data.Work;

import org.json.JSONException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {

  public static ArrayList<ProductionLine> productionLines = new ArrayList<>();
  public static ArrayList<Station> stations = new ArrayList<>();
  public static ArrayList<OP> ops = new ArrayList<>();
  public static ArrayList<BOM> boms = new ArrayList<>();
  public static ArrayList<Instruction> instructions = new ArrayList<>();
  public static ArrayList<ProductType> productTypes = new ArrayList<>();
  public static ArrayList<Product> products = new ArrayList<>();
  public static ArrayList<UserProfile> userProfiles = new ArrayList<>();
  public static ArrayList<Work> works = new ArrayList<>();
  public static UserProfile userProfile;
  public static int isAuthorized = 0;
  public static int isWorksUpdated =0;


  //FUNCTIONS
//  public void syncAll() {
//    for (ProductionLine productionLine : productionLines)
//      productionLine.sync(productionLine.getPreviousName(), productionLine.getNextName());
//
//    for (Station station : stations)
//      station.sync(station.getProductionLineName(), station.getPreviousName(), station.getNextName());
//
//    for (OP op : ops)
//      op.sync(op.getProductTypeName(), op.getStationName(), op.getPreviousName(), op.getNextName(), op.getbOMName(), op.getInstructionName());
//
//    for (Instruction instruction : instructions)
//      instruction.sync(instruction.getOpName());
//
//    for (BOM bom : boms)
//      bom.sync(bom.getOpName());
//  }

  /*
  USER PROFILE INFO
   */
  public static void getUserProfileInfo(final Context context) {

    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/user/get/username=" + userProfile.getUserName() + "&password=" + userProfile.getPassWord();

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
      response -> {
        try {
          userProfile.setName(response.getString("name"));
          userProfile.setFamilyName(response.getString("familyName"));
          userProfile.setIdentification(response.getInt("identification"));
          userProfile.setUserType(response.getString("userType"));
        } catch (JSONException e) {
          Log.d("tag", "error in json onresponse get user profile info");
        }
      }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show());
    queue.add(jsonObjectRequest);
  }

  //-------------------------------------------------------------------------------------USERS
  public static void getUsersList(final Context context) {
    userProfiles.clear();
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/user/all";
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      response -> {
        Log.d("tag", response.toString());
        for (int i = 0; i < response.length(); i++) {
          try {
            userProfiles.add(new UserProfile());
            userProfiles.get(i).setName(response.getJSONObject(i).get("name").toString());
            userProfiles.get(i).setFamilyName(response.getJSONObject(i).get("familyName").toString());
            userProfiles.get(i).setUserType(response.getJSONObject(i).getString("userType"));
            userProfiles.get(i).setIdentification(response.getJSONObject(i).getInt("identification"));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getUsersList(context);
    });
    queue.add(jsonArrayRequest);
  }

  /*
  user auth
   */

  public static void getUserAuth(Context context , String userName , String passWord){
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();

    String url = Constants.getUrl() + "/user/auth/username=" + userName + "&password=" + passWord;
    isAuthorized = 0;
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
      response -> {
        try {
          if (response.get("permission").equals("granted")) {
            Data.userProfile = new UserProfile(Integer.parseInt(userName), passWord);
            Log.d("tag" , Data.userProfile.getUserName() + Data.userProfile.getPassWord() );
            isAuthorized =1;
          } else {
            Toast.makeText(context, "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_LONG).show();
            isAuthorized =2;
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }, error -> {
        Toast.makeText(context,"خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
        isAuthorized =2;
        Log.d("tag" , ""+error );
      });
    queue.add(jsonObjectRequest);
  }





  /*
  PRODUCTION LINE /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  TODO PRODUCTION LINE LIST UPDATE AND REMOVE
  TODO PRODUCTION ADD DELETE UPDATE NEED TO BE CHECKED HERE
   */

  public static void getPrLineList(final Context context) {
    productionLines.clear();
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/productionline/all";
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      response -> {
        Log.d("tag", response.toString());
        for (int i = 0; i < response.length(); i++) {
          try {
            productionLines.add(new ProductionLine(Integer.parseInt(response.getJSONObject(i).get("id").toString()), response.getJSONObject(i).get("name").toString()));
            productionLines.get(i).setNextName(response.getJSONObject(i).get("next").toString());
            productionLines.get(i).setPreviousName(response.getJSONObject(i).get("previous").toString());
            productionLines.get(i).setWeight(response.getJSONObject(i).getDouble("weight"));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getPrLineList(context);

    });
    queue.add(jsonArrayRequest);
  }

  public static void addToPrLinesList(final Context context, final String name, final String previous, final String next
    , final double weight) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/productionline/add";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getPrLineList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("next", next);
        params.put("previous", previous);
        params.put("weight", "" + weight);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void updatePrLineList(final Context context, final ProductionLine productionLine) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();

    String url = Constants.getUrl() + "/productionline/update";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getPrLineList(context);
      } else
        Toast.makeText(context, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + productionLine.getId());
        params.put("name", productionLine.getName());
        params.put("next", productionLine.getNextName());
        params.put("previous", productionLine.getPreviousName());
        params.put("weight", "" + productionLine.getWeight());
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void deletePrLineById(final Context context, final int id) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/productionline/delete";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getPrLineList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + id);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  /*
  STATIONS ------------------------------------------------------------------------------------------------------------------------------------------
  TODO STATION ADD UPDATE REMOVE
  TODO STATION ADD UPDATE REMOVE NEED TO BE CHECKED HERE
   */
  public static void getStationsList(final Context context) {
    stations.clear();
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/station/all";

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      response -> {
        Log.d("tag", response.toString());
        for (int i = 0; i < response.length(); i++) {
          try {
            stations.add(new Station(response.getJSONObject(i).getInt("id"), response.getJSONObject(i).getString("name"),
              response.getJSONObject(i).getString("productionLine")));
            stations.get(i).setNextName(response.getJSONObject(i).getString("next"));
            stations.get(i).setPreviousName(response.getJSONObject(i).getString("previous"));
            stations.get(i).setWeight(response.getJSONObject(i).getDouble("weight"));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

      }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getStationsList(context);
    });
    queue.add(jsonArrayRequest);
  }

  public static void addToStationList(final Context context, final String name, final String productionLine, final String previous, final String next
    , final double weight) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/station/add";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getStationsList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("next", next);
        params.put("previous", previous);
        params.put("weight", "" + weight);
        params.put("productionLine", productionLine);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void deleteStationById(final Context context, final int id) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/station/delete";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getStationsList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + id);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void updateStationList(final Context context, final Station station) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();

    String url = Constants.getUrl() + "/station/update";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getStationsList(context);
      } else
        Toast.makeText(context, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + station.getId());
        params.put("productionLine", station.getProductionLineName());
        params.put("name", station.getName());
        params.put("next", station.getNextName());
        params.put("previous", station.getPreviousName());
        params.put("weight", "" + station.getWeight());
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  /*
  OP ------------------------------------------------------------------------------------------------------------------------------------------------
  TODO OP ADD REMOVE UPDATE
  TODO OP ADD REMOVE UPDATE NEED TO BE CHECKED HERE
   */
  public static void getOPsList(final Context context) {
    ops.clear();
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/op/all";

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      response -> {
        Log.d("tag", response.toString());
        for (int i = 0; i < response.length(); i++) {
          try {
            ops.add(new OP(response.getJSONObject(i).getInt("id"), response.getJSONObject(i).getString("name"),
              response.getJSONObject(i).getString("productType"), response.getJSONObject(i).getString("station")));
            ops.get(i).setNextName(response.getJSONObject(i).getString("next"));
            ops.get(i).setPreviousName(response.getJSONObject(i).getString("previous"));
            ops.get(i).setbOMName(response.getJSONObject(i).getString("bOM"));
            ops.get(i).setInstructionName(response.getJSONObject(i).getString("instruction"));
            ops.get(i).setWeight(response.getJSONObject(i).getDouble("weight"));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

      }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getOPsList(context);
    });
    queue.add(jsonArrayRequest);
  }

  public static void addToOPsList(final Context context, final String name, final String productType, final String station, final String bOM,
                                  final String instruction, final String previous, final String next, final double weight) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/op/add";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getOPsList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("next", next);
        params.put("previous", previous);
        params.put("weight", "" + weight);
        params.put("station", station);
        params.put("productType", productType);
        params.put("bOM", bOM);
        params.put("instruction", instruction);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void deleteOPById(final Context context, final int id) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/op/delete";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getOPsList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + id);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void updateOPList(final Context context, final OP op) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();

    String url = Constants.getUrl() + "/op/update";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getOPsList(context);
      } else
        Toast.makeText(context, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + op.getId());
        params.put("station", op.getStationName());
        params.put("productType", op.getProductTypeName());
        params.put("name", op.getName());
        params.put("next", op.getNextName());
        params.put("bOM", op.getbOMName());
        params.put("instruction", op.getInstructionName());
        params.put("previous", op.getPreviousName());
        params.put("weight", "" + op.getWeight());
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  /*
  PFODUCT ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   */
  public static void getProductsList(final Context context) {
    products.clear();
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/product/all";

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      response -> {
        Log.d("tag", response.toString());
        for (int i = 0; i < response.length(); i++) {
          try {
            products.add(new Product(response.getJSONObject(i).getInt("id"), response.getJSONObject(i).getInt("productID"),
              response.getJSONObject(i).getString("productType")));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getProductsList(context);
    });
    queue.add(jsonArrayRequest);
  }

  public static void addToProductsList(final Context context, final int productID, final String productType) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/product/add";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getProductsList(context);
      }
    }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getProductsList(context);
    }) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("productID", "" + productID);
        params.put("productType", productType);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void deleteProducById(final Context context, final int id) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/product/delete";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getProductsList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + id);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void updateProductsList(final Context context, final Product product) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();

    String url = Constants.getUrl() + "/product/update";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getProductsList(context);
      } else
        Toast.makeText(context, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + product.getId());
        params.put("productID", "" + product.getProductID());
        params.put("productType", product.getProductTypeName());
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  /*
  PRODUCT TYPES ----------------------------------------------------------------------------------------------------------------------------------------------
   */
  public static void getProductTypesList(final Context context) {
    productTypes.clear();
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/producttype/all";

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      response -> {
        Log.d("tag", response.toString());
        for (int i = 0; i < response.length(); i++) {
          try {
            productTypes.add(new ProductType(response.getJSONObject(i).getInt("id"), response.getJSONObject(i).get("name").toString()));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getProductTypesList(context);
    });
    queue.add(jsonArrayRequest);
  }

  public static void addToProductTypeList(final Context context, final String name) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/producttype/add";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getProductTypesList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void deletProducTypeById(final Context context, final int id) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/producttype/delete";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getProductTypesList(context);
      }
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + id);
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void updateProductTypeList(final Context context, final ProductType productType) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();

    String url = Constants.getUrl() + "/producttype/update";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getProductTypesList(context);
      } else
        Toast.makeText(context, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
    }, error -> Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show()) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + productType.getId());
        params.put("name", productType.getName());
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  /*
  Works ---------------------------------------------------------------------------------------------------------------------------------------------
   */

  public static void getWorkList(final Context context) {
    works.clear();
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/work/get/username=" + userProfile.getUserName() + "&password=" + userProfile.getPassWord();
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
      response -> {
        Log.d("tag", response.toString());
        for (int i = 0; i < response.length(); i++) {
          try {
            works.add(new Work(response.getJSONObject(i).getString("opName"), response.getJSONObject(i).getInt("productID"),
              response.getJSONObject(i).getInt("workerID"), new Date(response.getJSONObject(i).getLong("longplanStartDate")),
              new Date(response.getJSONObject(i).getLong("longPlanFinishDate"))));
            works.get(i).setId(response.getJSONObject(i).getInt("id"));
            works.get(i).setWorkerStartDate(new Date(response.getJSONObject(i).getLong("longWorkerStartDate")));
            works.get(i).setWorkerFinishDate(new Date(response.getJSONObject(i).getLong("longWorkerFinishDate")));
            works.get(i).setFinishDate(new Date(response.getJSONObject(i).getLong("longFinishDate")));
            works.get(i).setQcCheck(response.getJSONObject(i).getBoolean("qcCheck"));
            works.get(i).setWorkerCheck(response.getJSONObject(i).getBoolean("workerCheck"));
            works.get(i).setPlanDescription(response.getJSONObject(i).getString("planDescription"));
            works.get(i).setQcDescription(response.getJSONObject(i).getString("qcDescription"));
            works.get(i).setWorkerDescription(response.getJSONObject(i).getString("workerDescription"));
            works.get(i).setProgress(response.getJSONObject(i).getDouble("progress"));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      getWorkList(context);
    });
    queue.add(jsonArrayRequest);
  }


  public static void addToWorksList(final Context context, final Work work) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    String url = Constants.getUrl() + "/work/add";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getWorkList(context);
      }
    }, error -> {
      Log.d("TAG" , error.toString());
      Toast.makeText(context, "خطای اتصال به شبکهsdsd", Toast.LENGTH_LONG).show();
    }) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("opName", work.getOpName());
        params.put("productID", "" + work.getProductID());
        params.put("workerID", "" + work.getWorkerID());
        params.put("planStartDate", "" + work.getPlanStartDate().getTime());
        params.put("planFinishDate", "" + work.getPlanFinishDate().getTime());
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        params.put("planDescription" , work.getPlanDescription());
        return params;
      }
    };
    queue.add(stringRequest);
  }

  public static void updateWorkList(final Context context, final Work work) {
    NetworkModule networkModule = NetworkModule.getInstance(context);
    RequestQueue queue = networkModule.getRequestQueue();
    isWorksUpdated = 0;
    String url = Constants.getUrl() + "/work/update";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
      if (response.equals("done")) {
        isWorksUpdated = 1;
        Toast.makeText(context, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
        getWorkList(context);
      } else {
        Toast.makeText(context, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
        isWorksUpdated= 2;
      }
    }, error -> {
      Toast.makeText(context, "خطای اتصال به شبکه", Toast.LENGTH_LONG).show();
      isWorksUpdated= 2;
    }) {

      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id" , ""+work.getId());
        params.put("opName", work.getOpName());
        params.put("productID", "" + work.getProductID());
        params.put("workerID", "" + work.getWorkerID());
        params.put("planStartDate", "" + work.getPlanStartDate().getTime());
        params.put("planFinishDate", "" + work.getPlanFinishDate().getTime());
        params.put("workerStartDate", "" + work.getWorkerStartDate().getTime());
        params.put("workerFinishDate", "" + work.getWorkerFinishDate().getTime());
        params.put("finishDate", "" + work.getFinishDate().getTime());
        params.put("qcCheck", "" + work.isQcCheck());
        params.put("workerCheck", "" + work.isWorkerCheck());
        params.put("progress", "" + work.getProgress());
        params.put("planDescription" , work.getPlanDescription());
        params.put("qcDescription" , work.getQcDescription());
        params.put("workerDescription" , work.getWorkerDescription());
        params.put("userName", "" + userProfile.getUserName());
        params.put("passWord", userProfile.getPassWord());
        return params;
      }
    };
    queue.add(stringRequest);
  }

}

