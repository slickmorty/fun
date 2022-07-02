package com.slickmorty.asankar.activities.engineering;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.data.BOM;
import com.slickmorty.asankar.data.Instruction;
import com.slickmorty.asankar.data.OP;
import com.slickmorty.asankar.data.ProductType;
import com.slickmorty.asankar.data.ProductionLine;
import com.slickmorty.asankar.data.Station;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EngineeringAddActivity extends AppCompatActivity {
  //Vars
  private EditText editText1;
  private EditText editText2;
  private EditText editText3;
  private EditText editText4;
  private EditText editText5;
  private EditText editText6;
  private EditText editText7;
  private EditText editText8;

  private Button buttonOP;
  private Button buttonProductionLine;
  private Button buttonProductType;
  private Button buttonStation;
  private int selectedIndex=-1;

  private CharSequence[] options;

  private ImageView confirm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_engineering_add);
    //TODO CHECK EDIT TEXT NUMBERS
    editText1 = findViewById(R.id.eng_edtext_1);
    editText2 = findViewById(R.id.eng_edtext_2);
    editText3 = findViewById(R.id.eng_edtext_3);
    editText4 = findViewById(R.id.eng_edtext_4);
    editText5 = findViewById(R.id.eng_edtext_5);
    editText6 = findViewById(R.id.eng_edtext_6);
    editText7 = findViewById(R.id.eng_edtext_7);
    editText8 = findViewById(R.id.eng_edtext_8);


    buttonOP = findViewById(R.id.eng_btn_addop);
    buttonProductionLine = findViewById(R.id.eng_btn_addprline);
    buttonProductType = findViewById(R.id.eng_btn_addproducttype);
    buttonStation = findViewById(R.id.eng_btn_addstation);
    confirm = findViewById(R.id.eng_imgbtn_confirm);

    //TODO ADDING EDITTEXT INPUT TYPE
    /*
    Add Product Type
     */

    buttonProductType.setOnClickListener(view -> {
      confirm.setTag("add product type");
      Log.d("tag", "    " + confirm.getTag().toString());
      confirm.setVisibility(View.VISIBLE);

      editText2.setVisibility(View.GONE);
      editText3.setVisibility(View.GONE);
      editText4.setVisibility(View.GONE);
      editText5.setVisibility(View.GONE);
      editText6.setVisibility(View.GONE);
      editText7.setVisibility(View.GONE);
      editText8.setVisibility(View.GONE);


      editText1.setText(null);
      editText1.setVisibility(View.VISIBLE);
      editText1.setHint("نام پروژه");
    });
    /*
    Adding Production Line
     */
    buttonProductionLine.setOnClickListener(view -> {
      confirm.setTag("add production line");
      Log.d("tag", "    " + confirm.getTag().toString());
      confirm.setVisibility(View.VISIBLE);

      editText5.setVisibility(View.GONE);
      editText6.setVisibility(View.GONE);
      editText7.setVisibility(View.GONE);
      editText8.setVisibility(View.GONE);

      editText1.setVisibility(View.VISIBLE);
      editText2.setVisibility(View.VISIBLE);
      editText3.setVisibility(View.VISIBLE);
      editText4.setVisibility(View.VISIBLE);


      editText1.setText(null);
      editText2.setText(null);
      editText3.setText(null);
      editText4.setText(null);

      editText1.setHint("نام خط تولید جدید");
      editText2.setHint("خط تولید بعدی");
      editText3.setHint("نام خط تولید قبلی");
      editText4.setHint("وزن خط تولید");
    });

    /*
    Adding Station
     */
    buttonStation.setOnClickListener(view -> {
      confirm.setTag("add station");
      Log.d("tag", "    " + confirm.getTag().toString());
      confirm.setVisibility(View.VISIBLE);

      editText6.setVisibility(View.GONE);
      editText7.setVisibility(View.GONE);
      editText8.setVisibility(View.GONE);


      editText1.setVisibility(View.VISIBLE);
      editText2.setVisibility(View.VISIBLE);
      editText3.setVisibility(View.VISIBLE);
      editText4.setVisibility(View.VISIBLE);
      editText5.setVisibility(View.VISIBLE);


      editText1.setText(null);
      editText2.setText(null);
      editText3.setText(null);
      editText4.setText(null);
      editText5.setText(null);

      editText1.setHint("نام ایستگاه جدید");
      editText2.setHint("خط تولید");
      editText3.setHint("نام ایستگاه قبلی");
      editText4.setHint("نام ایستگاه بعدی");
      editText5.setHint("وزن");
    });


    /*
    Adding OP
     */

    buttonOP.setOnClickListener(view -> {
      confirm.setTag("add op");
      Log.d("tag", "    " + confirm.getTag().toString());
      confirm.setVisibility(View.VISIBLE);


      editText1.setVisibility(View.VISIBLE);
      editText2.setVisibility(View.VISIBLE);
      editText3.setVisibility(View.VISIBLE);
      editText4.setVisibility(View.VISIBLE);
      editText5.setVisibility(View.VISIBLE);
      editText6.setVisibility(View.VISIBLE);
      editText7.setVisibility(View.VISIBLE);
      editText8.setVisibility(View.VISIBLE);


      editText1.setText(null);
      editText2.setText(null);
      editText3.setText(null);
      editText4.setText(null);
      editText5.setText(null);
      editText6.setText(null);
      editText7.setText(null);
      editText8.setText(null);

      editText1.setHint("نام OP جدید");
      editText2.setHint("نام پروژه");
      editText3.setHint("ایستگاه");
      editText4.setHint("BOM");
      editText5.setHint("دستورالعمل");
      editText6.setHint("نام OP قبلی");
      editText7.setHint("نام OP بعدی");
      editText8.setHint("وزن");
    });

    /*
    Confirm button
     */

    //TODO ADDING SELECT OPTION FOR THE SELCITONG OF THE PRODUCTION LINE
    confirm.setOnClickListener(view -> {
      closeKeyboard();
      if(confirm.getTag()==null){
        return;
      }

      if ("add product type".equals(confirm.getTag().toString())) {
        for (ProductType productType : Data.productTypes) {
          if (productType.getName().equals(editText1.getText().toString())) {
            Toast.makeText(getApplicationContext(), "این محصول قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
            confirm.setVisibility(View.GONE);
            editText1.setVisibility(View.GONE);
            return;
          }
        }
        if(editText1.getText().toString().equals("")){
          editText1.setText("null");
          Toast.makeText(getApplicationContext() , "نام نمیتواند خالی باشد" , Toast.LENGTH_SHORT).show();
          return;
        }
        Data.addToProductTypeList(getApplicationContext(), editText1.getText().toString());
        confirm.setVisibility(View.GONE);
        editText1.setVisibility(View.GONE);
      }

      else if("add production line".equals(confirm.getTag().toString())){
        for(ProductionLine productionLine : Data.productionLines){
          if(productionLine.getName().equals(editText1.getText().toString())){
            Toast.makeText(getApplicationContext() , "این خط تولید قبلا ثبت شده است" , Toast.LENGTH_SHORT).show();
            confirm.setVisibility(View.GONE);
            editText1.setVisibility(View.GONE);
            editText2.setVisibility(View.GONE);
            editText3.setVisibility(View.GONE);
            editText4.setVisibility(View.GONE);

            return;
          }
        }
        if(editText1.getText().toString().equals("")){
          editText1.setText("null");
          Toast.makeText(getApplicationContext() , "نام نمیتواند خالی باشد" , Toast.LENGTH_SHORT).show();
          return;
        }
        if(editText2.getText().toString().equals("")){
          editText2.setText("null");
        }
        if(editText3.getText().toString().equals("")){
          editText3.setText("null");
        }
        if(editText4.getText().toString().equals("")){
          editText4.setText("0");
        }
        Data.addToPrLinesList(getApplicationContext() , editText1.getText().toString() , editText3.getText().toString()
          ,editText2.getText().toString(), Double.parseDouble(editText4.getText().toString()));
        confirm.setVisibility(View.GONE);
        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);
      }

      else if("add station".equals(confirm.getTag().toString())){
        for(Station station : Data.stations){
          if(station.getName().equals(editText1.getText().toString())){
            Toast.makeText(getApplicationContext() , "این ایستگاه قبلا ثبت شده است" , Toast.LENGTH_SHORT).show();
            confirm.setVisibility(View.GONE);
            editText1.setVisibility(View.GONE);
            editText2.setVisibility(View.GONE);
            editText3.setVisibility(View.GONE);
            editText4.setVisibility(View.GONE);
            editText5.setVisibility(View.GONE);
            return;
          }
        }
        if(editText1.getText().toString().equals("")){
          editText1.setText("null");
          Toast.makeText(getApplicationContext() , "نام نمیتواند خالی باشد" , Toast.LENGTH_SHORT).show();
          return;
        }
        if(editText2.getText().toString().equals("")){
          editText2.setText("null");
        }
        else{
          boolean flag = true;
          for (ProductionLine productionLine : Data.productionLines){
            if (productionLine.getName().equals(editText2.getText().toString())){
              flag = false;
            }
          }
          if(flag) {
            Toast.makeText(getApplicationContext() , "چنین خط تولیدی وجود ندارد" , Toast.LENGTH_SHORT ).show();
            return;
          }
        }
        if(editText3.getText().toString().equals("")){
          editText3.setText("null");
        }
        if(editText4.getText().toString().equals("")){
          editText4.setText("null");
        }
        if(editText5.getText().toString().equals("")){
          editText5.setText("0");
        }
        Data.addToStationList(getApplicationContext() , editText1.getText().toString() , editText2.getText().toString()
          ,editText3.getText().toString(), editText4.getText().toString(),Double.parseDouble(editText5.getText().toString()));
        confirm.setVisibility(View.GONE);
        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);
        editText5.setVisibility(View.GONE);
      }
      else if("add op".equals(confirm.getTag().toString())){
        for(OP op : Data.ops){
          if(op.getName().equals(editText1.getText().toString())){
            Toast.makeText(getApplicationContext() , "این OP قبلا ثبت شده است" , Toast.LENGTH_SHORT).show();
            confirm.setVisibility(View.GONE);
            editText1.setVisibility(View.GONE);
            editText2.setVisibility(View.GONE);
            editText3.setVisibility(View.GONE);
            editText4.setVisibility(View.GONE);
            editText5.setVisibility(View.GONE);
            editText6.setVisibility(View.GONE);
            editText7.setVisibility(View.GONE);
            editText8.setVisibility(View.GONE);
            return;
          }
        }
        if(editText1.getText().toString().equals("")){
          editText1.setText("null");
          Toast.makeText(getApplicationContext() , "نام نمیتواند خالی باشد" , Toast.LENGTH_SHORT).show();
          return;
        }
        if(editText2.getText().toString().equals("")){
          editText2.setText("null");
        }
        else{
          boolean flag = true;
          for (ProductType productType : Data.productTypes){
            if (productType.getName().equals(editText2.getText().toString())){
              flag = false;
            }
          }
          if(flag) {
            Toast.makeText(getApplicationContext() , "چنین محصولی وجود ندارد" , Toast.LENGTH_SHORT ).show();
            return;
          }
        }
        if(editText3.getText().toString().equals("")){
          editText3.setText("null");
        }
        else{
          boolean flag = true;
          for (Station station : Data.stations){
            if (station.getName().equals(editText3.getText().toString())){
              flag = false;
            }
          }
          if(flag) {
            Toast.makeText(getApplicationContext() , "چنین ایستگاهی وجود ندارد" , Toast.LENGTH_SHORT ).show();
            return;
          }
        }
        if(editText4.getText().toString().equals("")){
          editText4.setText("null");
        }
        else{
          boolean flag = true;
          for (BOM bom : Data.boms){
            if (bom.getName().equals(editText4.getText().toString())){
              flag = false;
            }
          }
          if(flag) {
            Toast.makeText(getApplicationContext() , "چنین BOM وجود ندارد" , Toast.LENGTH_SHORT ).show();
            return;
          }
        }
        if(editText5.getText().toString().equals("")){
          editText5.setText("null");
        }
        else{
          boolean flag = true;
          for (Instruction instruction : Data.instructions){
            if (instruction.getName().equals(editText5.getText().toString())){
              flag = false;
            }
          }
          if(flag) {
            Toast.makeText(getApplicationContext() , "چنین دستورالعملی وجود ندارد" , Toast.LENGTH_SHORT ).show();
            return;
          }
        }
        if(editText6.getText().toString().equals("")){
          editText6.setText("null");
        }
        if(editText7.getText().toString().equals("")){
          editText7.setText("null");
        }
        if(editText8.getText().toString().equals("")){
          editText8.setText("0");
        }
        Data.addToOPsList(getApplicationContext() , editText1.getText().toString() , editText2.getText().toString()
          ,editText3.getText().toString(), editText4.getText().toString(),editText5.getText().toString(),editText6.getText().toString(),
          editText7.getText().toString(),Double.parseDouble(editText8.getText().toString()));
        confirm.setVisibility(View.GONE);
        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);
        editText5.setVisibility(View.GONE);
        editText6.setVisibility(View.GONE);
        editText7.setVisibility(View.GONE);
        editText8.setVisibility(View.GONE);
      }
    });



//    button2.setOnClickListener(v -> {
//      if("add op".equals(confirm.getTag().toString())){
//        int i=0;
//        options = new CharSequence[Data.productTypes.size()];
//        for(ProductType productType :Data.productTypes){
//          options[i] = productType.getName();
//          i++;
//        }
//        productTypeAllertDialog(options);
//      }
//      else if("add station".equals(confirm.getTag().toString())){
//        int i=0;
//        options = new CharSequence[Data.productionLines.size()];
//        for(ProductionLine productionLine :Data.productionLines){
//          options[i] = productionLine.getName();
//          i++;
//        }
//        productionLineAllertDialog(options);
//      }
//    });
//
//    button3.setOnClickListener(v -> {
//      int i=0;
//      options = new CharSequence[Data.stations.size()];
//      for(Station station :Data.stations){
//        options[i] = station.getName();
//        i++;
//      }
//      stationAllertDialog(options);
//    });

    editText2.setOnLongClickListener(v -> {
      if("add op".equals(confirm.getTag().toString())){
        int i=0;
        options = new CharSequence[Data.productTypes.size()];
        for(ProductType productType :Data.productTypes){
          options[i] = productType.getName();
          i++;
        }
        productTypeAllertDialog(options);
      }
      else if("add station".equals(confirm.getTag().toString())){
        int i=0;
        options = new CharSequence[Data.productionLines.size()];
        for(ProductionLine productionLine :Data.productionLines){
          options[i] = productionLine.getName();
          i++;
        }
        productionLineAllertDialog(options);
      }
      return true;
    });

    editText3.setOnLongClickListener(v -> {
      if("add op".equals(confirm.getTag().toString())){
      int i=0;
      options = new CharSequence[Data.stations.size()];
      for(Station station :Data.stations){
        options[i] = station.getName();
        i++;
      }
      stationAllertDialog(options);
      }
      return true;
    });

  }

  private void closeKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  /*************************************************************************************************************************************************/

  private void stationAllertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if(selectedIndex<0)
        return;
      editText3.setText(Data.stations.get(selectedIndex).getName().replace("null" , ""));
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  /*************************************************************************************************************************************************/

  private void productionLineAllertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if(selectedIndex<0)
        return;
      editText2.setText(Data.productionLines.get(selectedIndex).getName().replace("null" , ""));
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  /*************************************************************************************************************************************************/

  private void productTypeAllertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if(selectedIndex<0)
        return;
      editText2.setText(Data.productTypes.get(selectedIndex).getName().replace("null" , ""));
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }
}