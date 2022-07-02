package com.slickmorty.asankar.activities.production.dailyactivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.data.OP;
import com.slickmorty.asankar.data.Product;
import com.slickmorty.asankar.data.ProductionLine;
import com.slickmorty.asankar.data.Station;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DailyReportActivity extends AppCompatActivity {

  private CharSequence[] options;
  private ProductionLine[] productionLines;
  private Station[] stations;
  private Product[] products;
  private OP[] ops;
  private int selectedIndexPrline;
  private int selectedIndexStation;
  private int selectedIndexOP;
  private int selectedIndexProduct;

  private Button station;
  private Button productionLine;
  private Button op;
  private Button product;
  private Button confirm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_daily_report);

    station=findViewById(R.id.daily_btn_station);
    productionLine=findViewById(R.id.daily_btn_prl);
    op=findViewById(R.id.daily_btn_op);
    product=findViewById(R.id.daily_btn_product);
    confirm=findViewById(R.id.daily_btn_confirm);




    //-------------------------------------------------------

    productionLine.setOnClickListener(v -> {
      selectedIndexPrline=-1;
      int i=0;
      options = new CharSequence[Data.productionLines.size()];
      productionLines = new ProductionLine[Data.productionLines.size()];
      for(ProductionLine productionLine : Data.productionLines){
        options[i] = productionLine.getName();
        productionLines[i] = productionLine;
        i++;
      }
      productLineAlertDialog(options);
    });

    //-------------------------------------------------------

    station.setOnClickListener(v -> {
      selectedIndexStation=-1;
      int i=0;
      for(Station station : Data.stations){
        if(station.getProductionLineName().equals(productionLines[selectedIndexPrline].getName())){
          i++;
        }
      }

      options = new CharSequence[i];
      stations = new Station[i];
      i=0;

      for(Station station : Data.stations){
        if(station.getProductionLineName().equals(productionLines[selectedIndexPrline].getName())){
          options[i] = station.getName();
          stations[i] = station;
          i++;
        }
      }
      stationAlertDialog(options);
    });
    //-------------------------------------------------------

    product.setOnClickListener(v -> {
      selectedIndexProduct=-1;
      int i=0;
      options = new CharSequence[Data.products.size()];
      products = new Product[Data.products.size()];
      for(Product product : Data.products){
        options[i] = product.getProductTypeName()+" "+"به شماره"+" "+product.getProductID();
        products[i] = product;
        i++;
      }
      productAlertDialog(options);

    });
    //-------------------------------------------------------

    op.setOnClickListener(v -> {
      selectedIndexOP=-1;
      int i=0;

      for(OP op : Data.ops){
        if(op.getStationName().equals(stations[selectedIndexStation].getName()) &&
        op.getProductTypeName().equals(products[selectedIndexProduct].getProductTypeName())){
          i++;
        }
      }

      options = new CharSequence[i];
      ops = new OP[i];
      i=0;

      for(OP op : Data.ops){
        if(op.getStationName().equals(stations[selectedIndexStation].getName()) &&
          op.getProductTypeName().equals(products[selectedIndexProduct].getProductTypeName())){
          options[i] = op.getName();
          ops[i] = op;
          i++;
        }
      }
      opAlertDialog(options);


    });
    //-------------------------------------------------------

    confirm.setOnClickListener(v -> {
      showMessageDialog();
    });

  }

  //*********************************************************************************************************************

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  //*********************************************************************************************************************

  private void productAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndexProduct = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if(selectedIndexProduct<0)
        return;
      op.setVisibility(View.VISIBLE);

    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //*********************************************************************************************************************

  private void productLineAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndexPrline = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if(selectedIndexPrline<0)
        return;
      station.setVisibility(View.VISIBLE);

    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //*********************************************************************************************************************

  private void stationAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndexStation = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if(selectedIndexStation<0)
        return;
      product.setVisibility(View.VISIBLE);
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //*********************************************************************************************************************

  private void opAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndexOP = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if(selectedIndexOP<0)
        return;
      confirm.setVisibility(View.VISIBLE);

    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  /*/*****************************************************************************************************************************/
  private void showMessageDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View view = inflater.inflate(R.layout.layout_production_description , null);
    EditText editText1 = view.findViewById(R.id.layout_prod_des);
    builder.setView(view)
      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          confirm.setVisibility(View.GONE);
          op.setVisibility(View.GONE);
          station.setVisibility(View.GONE);
          product.setVisibility(View.GONE);
        }
      });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

}