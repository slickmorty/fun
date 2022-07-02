package com.slickmorty.asankar.activities.engineering;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class EngineeringUpdateActivity extends AppCompatActivity {
  //Vars
  private EditText editText1;
  private EditText editText2;
  private EditText editText3;
  private EditText editText4;
  private EditText editText5;
  private EditText editText6;
  private EditText editText7;
  private EditText editText8;

  private TextView textView1;
  private TextView textView2;
  private TextView textView3;
  private TextView textView4;
  private TextView textView5;
  private TextView textView6;
  private TextView textView7;
  private TextView textView8;

  private int selectedIndex = -1;
  private CharSequence[] options;

  private Button buttonOP;
  private Button buttonProductionLine;
  private Button buttonProductType;
  private Button buttonStation;

  private ImageView confirm;
  //TODO CHECK IF THE SELECTALL WHEN FOCOUS WORKS FOR THIS AND ADDING
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_engineering_update);

    editText1 = findViewById(R.id.engup_edtext_1);
    editText2 = findViewById(R.id.engup_edtext_2);
    editText3 = findViewById(R.id.engup_edtext_3);
    editText4 = findViewById(R.id.engup_edtext_4);
    editText5 = findViewById(R.id.engup_edtext_5);
    editText6 = findViewById(R.id.engup_edtext_6);
    editText7 = findViewById(R.id.engup_edtext_7);
    editText8 = findViewById(R.id.engup_edtext_8);

    textView1 = findViewById(R.id.engup_text_1);
    textView2 = findViewById(R.id.engup_text_2);
    textView3 = findViewById(R.id.engup_text_3);
    textView4 = findViewById(R.id.engup_text_4);
    textView5 = findViewById(R.id.engup_text_5);
    textView6 = findViewById(R.id.engup_text_6);
    textView7 = findViewById(R.id.engup_text_7);
    textView8 = findViewById(R.id.engup_text_8);


    buttonOP = findViewById(R.id.engup_btn_addop);
    buttonProductionLine = findViewById(R.id.engup_btn_addprline);
    buttonProductType = findViewById(R.id.engup_btn_addproducttype);
    buttonStation = findViewById(R.id.engup_btn_addstation);

    confirm = findViewById(R.id.engup_imgbtn_confirm);

    /*
    UPDATE Product Type----------------------------------------------------------------------------------------------------------------------------------------------
     */

    buttonProductType.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        confirm.setTag("update product type");
        options = new CharSequence[Data.productTypes.size()];
        for (int i = 0; i < Data.productTypes.size(); i++) {
          options[i] = Data.productTypes.get(i).getName();
        }
        productTypeAllertDialog(options);
      }
    });

    /*
    UPDATING Production Line----------------------------------------------------------------------------------------------------------------------------------------------
     */
    buttonProductionLine.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        confirm.setTag("update production line");
        options = new CharSequence[Data.productionLines.size()];
        for (int i = 0; i < Data.productionLines.size(); i++) {
          options[i] = Data.productionLines.get(i).getName();
        }
        productionLineAllertDialoge(options);
      }
    });

    /*
    UPDATING STATION----------------------------------------------------------------------------------------------------------------------------------------------
     */
    buttonStation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        confirm.setTag("update station");
        options = new CharSequence[Data.stations.size()];
        for (int i = 0; i < Data.stations.size(); i++) {
          options[i] = Data.stations.get(i).getName();
        }
        stationAllertDialog(options);
      }
    });
    /*
      UPDATION OP----------------------------------------------------------------------------------------------------------------------------------------------
     */
    buttonOP.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        confirm.setTag("update op");
        options = new CharSequence[Data.ops.size()];
        for (int i = 0; i < Data.ops.size(); i++) {
          options[i] = Data.ops.get(i).getName();
        }
        opAlertDialog(options);
      }
    });
    /*
    Confirm Button----------------------------------------------------------------------------------------------------------------------------------------------
     */
    confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        closeKeyboard();

        if ("update product type".equals(confirm.getTag().toString())) {
          for (int i = 0; i < Data.productTypes.size(); i++) {
            if (Data.productTypes.get(i).getName().equals(editText1.getText().toString()) && selectedIndex != i) {
              Toast.makeText(getApplicationContext(), "این پروژه قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
              confirm.setVisibility(View.GONE);
              editText1.setVisibility(View.GONE);
              return;
            }
          }
          if (editText1.getText().toString().equals("")) {
            editText1.setText("null");
            Toast.makeText(getApplicationContext(), "نام نمیتواند خالی باشد", Toast.LENGTH_SHORT).show();
            return;
          }
          ProductType productType = new ProductType(Data.productTypes.get(selectedIndex).getId() , editText1.getText().toString());
          Data.updateProductTypeList(getApplicationContext(), productType);
          confirm.setVisibility(View.GONE);
          editText1.setVisibility(View.GONE);
        } //----------------------------------------------------------------------------------------------------------------------------
        else if ("update production line".equals(confirm.getTag().toString())) {
          for (int i = 0; i < Data.productionLines.size(); i++)
            if (Data.productionLines.get(i).getName().equals(editText1.getText().toString()) && i != selectedIndex) {
              Toast.makeText(getApplicationContext(), "پروژه ای با این نام قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
              confirm.setVisibility(View.GONE);
              editText1.setVisibility(View.GONE);
              editText2.setVisibility(View.GONE);
              editText3.setVisibility(View.GONE);
              editText4.setVisibility(View.GONE);
              textView1.setVisibility(View.GONE);
              textView2.setVisibility(View.GONE);
              textView3.setVisibility(View.GONE);
              textView4.setVisibility(View.GONE);
              editText4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
              return;
            }
          if (editText1.getText().toString().equals("")) {
            editText1.setText("null");
            Toast.makeText(getApplicationContext(), "نام نمیتواند خالی باشد", Toast.LENGTH_SHORT).show();
            return;
          }
          if (editText2.getText().toString().equals("")) {
            editText2.setText("null");
          }
          if (editText3.getText().toString().equals("")) {
            editText3.setText("null");
          }
          if (editText4.getText().toString().equals("")) {
            editText4.setText("0");
          }
          ProductionLine productionLine = new ProductionLine(Data.productionLines.get(selectedIndex).getId(), editText1.getText().toString());
          productionLine.setPreviousName(editText2.getText().toString());
          productionLine.setNextName(editText3.getText().toString());
          productionLine.setWeight(Double.parseDouble(editText4.getText().toString()));
          Data.updatePrLineList(getApplicationContext(), productionLine);
          confirm.setVisibility(View.GONE);
          editText1.setVisibility(View.GONE);
          editText2.setVisibility(View.GONE);
          editText3.setVisibility(View.GONE);
          editText4.setVisibility(View.GONE);
          textView1.setVisibility(View.GONE);
          textView2.setVisibility(View.GONE);
          textView3.setVisibility(View.GONE);
          textView4.setVisibility(View.GONE);
          editText4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);


        } //----------------------------------------------------------------------------------------------------------------------------
        else if ("update station".equals(confirm.getTag().toString())) {
          for (int i = 0; i < Data.stations.size(); i++) {
            if (Data.stations.get(i).getName().equals(editText1.getText().toString()) && i != selectedIndex) {
              Toast.makeText(getApplicationContext(), "این ایستگاه قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
              confirm.setVisibility(View.GONE);
              editText1.setVisibility(View.GONE);
              editText2.setVisibility(View.GONE);
              editText3.setVisibility(View.GONE);
              editText4.setVisibility(View.GONE);
              editText5.setVisibility(View.GONE);
              editText5.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

              textView1.setVisibility(View.GONE);
              textView2.setVisibility(View.GONE);
              textView3.setVisibility(View.GONE);
              textView4.setVisibility(View.GONE);
              textView5.setVisibility(View.GONE);
              return;
            }
          }
          if (editText1.getText().toString().equals("")) {
            editText1.setText("null");
            Toast.makeText(getApplicationContext(), "نام نمیتواند خالی باشد", Toast.LENGTH_SHORT).show();
            return;
          }
          if (editText2.getText().toString().equals("")) {
            editText2.setText("null");
          } else {
            boolean flag = true;
            for (ProductionLine productionLine : Data.productionLines) {
              if (productionLine.getName().equals(editText2.getText().toString())) {
                flag = false;
              }
            }
            if (flag) {
              Toast.makeText(getApplicationContext(), "چنین خط تولیدی وجود ندارد", Toast.LENGTH_SHORT).show();
              return;
            }
          }
          if (editText3.getText().toString().equals("")) {
            editText3.setText("null");
          }
          if (editText4.getText().toString().equals("")) {
            editText4.setText("null");
          }
          if (editText5.getText().toString().equals("")) {
            editText5.setText("0");
          }
          Station station = new Station(Data.stations.get(selectedIndex).getId(), editText1.getText().toString(), editText2.getText().toString());
          station.setPreviousName(editText3.getText().toString());
          station.setNextName(editText4.getText().toString());
          station.setWeight(Double.parseDouble(editText5.getText().toString()));
          Data.updateStationList(getApplicationContext(), station);
          confirm.setVisibility(View.GONE);
          editText1.setVisibility(View.GONE);
          editText2.setVisibility(View.GONE);
          editText3.setVisibility(View.GONE);
          editText4.setVisibility(View.GONE);
          editText5.setVisibility(View.GONE);

          textView1.setVisibility(View.GONE);
          textView2.setVisibility(View.GONE);
          textView3.setVisibility(View.GONE);
          textView4.setVisibility(View.GONE);
          textView5.setVisibility(View.GONE);
          editText5.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

        }//----------------------------------------------------------------------------------------------------------------------------
        else if ("update op".equals(confirm.getTag().toString())) {
          for (int i = 0; i < Data.ops.size(); i++) {
            if (Data.ops.get(i).getName().equals(editText1.getText().toString()) && i != selectedIndex) {
              Toast.makeText(getApplicationContext(), "این OP قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
              confirm.setVisibility(View.GONE);
              editText1.setVisibility(View.GONE);
              editText2.setVisibility(View.GONE);
              editText3.setVisibility(View.GONE);
              editText4.setVisibility(View.GONE);
              editText5.setVisibility(View.GONE);
              editText6.setVisibility(View.GONE);
              editText7.setVisibility(View.GONE);
              editText8.setVisibility(View.GONE);
              textView1.setVisibility(View.GONE);
              textView2.setVisibility(View.GONE);
              textView3.setVisibility(View.GONE);
              textView4.setVisibility(View.GONE);
              textView5.setVisibility(View.GONE);
              textView6.setVisibility(View.GONE);
              textView7.setVisibility(View.GONE);
              textView8.setVisibility(View.GONE);
              editText8.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
              return;
            }
          }
          if (editText1.getText().toString().equals("")) {
            editText1.setText("null");
            Toast.makeText(getApplicationContext(), "نام نمیتواند خالی باشد", Toast.LENGTH_SHORT).show();
            return;
          }
          if (editText2.getText().toString().equals("")) {
            editText2.setText("null");
          } else {
            boolean flag = true;
            for (ProductType productType : Data.productTypes) {
              if (productType.getName().equals(editText2.getText().toString())) {
                flag = false;
              }
            }
            if (flag) {
              Toast.makeText(getApplicationContext(), "چنین پروژه ای وجود ندارد", Toast.LENGTH_SHORT).show();
              return;
            }
          }
          if (editText3.getText().toString().equals("")) {
            editText3.setText("null");
          } else {
            boolean flag = true;
            for (Station station : Data.stations) {
              if (station.getName().equals(editText3.getText().toString())) {
                flag = false;
              }
            }
            if (flag) {
              Toast.makeText(getApplicationContext(), "چنین ایستگاهی وجود ندارد", Toast.LENGTH_SHORT).show();
              return;
            }
          }
          if (editText4.getText().toString().equals("")) {
            editText4.setText("null");
          } else {
            boolean flag = true;
            for (BOM bom : Data.boms) {
              if (bom.getName().equals(editText4.getText().toString())) {
                flag = false;
              }
            }
            if (flag) {
              Toast.makeText(getApplicationContext(), "چنین BOM وجود ندارد", Toast.LENGTH_SHORT).show();
              return;
            }
          }
          if (editText5.getText().toString().equals("")) {
            editText5.setText("null");
          } else {
            boolean flag = true;
            for (Instruction instruction : Data.instructions) {
              if (instruction.getName().equals(editText5.getText().toString())) {
                flag = false;
              }
            }
            if (flag) {
              Toast.makeText(getApplicationContext(), "چنین دستورالعملی وجود ندارد", Toast.LENGTH_SHORT).show();
              return;
            }
          }
          if (editText6.getText().toString().equals("")) {
            editText6.setText("null");
          }
          if (editText7.getText().toString().equals("")) {
            editText7.setText("null");
          }
          if (editText8.getText().toString().equals("")) {
            editText8.setText("0");
          }
          OP op = new OP(Data.ops.get(selectedIndex).getId(), editText1.getText().toString(),editText2.getText().toString(), editText3.getText().toString());
          op.setbOMName(editText4.getText().toString());
          op.setInstructionName(editText5.getText().toString());
          op.setPreviousName(editText6.getText().toString());
          op.setNextName(editText7.getText().toString());
          op.setWeight(Double.parseDouble(editText8.getText().toString()));
          Data.updateOPList(getApplicationContext(), op);
          confirm.setVisibility(View.GONE);
          editText1.setVisibility(View.GONE);
          editText2.setVisibility(View.GONE);
          editText3.setVisibility(View.GONE);
          editText4.setVisibility(View.GONE);
          editText5.setVisibility(View.GONE);
          editText6.setVisibility(View.GONE);
          editText7.setVisibility(View.GONE);
          editText8.setVisibility(View.GONE);
          textView1.setVisibility(View.GONE);
          textView2.setVisibility(View.GONE);
          textView3.setVisibility(View.GONE);
          textView4.setVisibility(View.GONE);
          textView5.setVisibility(View.GONE);
          textView6.setVisibility(View.GONE);
          textView7.setVisibility(View.GONE);
          textView8.setVisibility(View.GONE);
          editText8.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
      }
    });

    confirm.startAnimation(AnimationUtils.loadAnimation(this , R.drawable.shakebutton));
  }

  //-------------------------------------------------------------------------------------------------------------------------------------------------
  private void productTypeAllertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        selectedIndex = i;
      }
    });
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        confirm.setVisibility(View.VISIBLE);
        editText1.setVisibility(View.VISIBLE);
        editText1.setSelectAllOnFocus(true);
        textView1.setVisibility(View.VISIBLE);
        textView1.setText("نام پروژه");
        editText1.setText(Data.productTypes.get(selectedIndex).getName().replace("null" , ""));
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show();
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //-------------------------------------------------------------------------------------------------------------------------------------------------
  private void productionLineAllertDialoge(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        selectedIndex = i;
      }
    });
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        confirm.setVisibility(View.VISIBLE);
        editText1.setVisibility(View.VISIBLE);
        editText2.setVisibility(View.VISIBLE);
        editText3.setVisibility(View.VISIBLE);
        editText4.setVisibility(View.VISIBLE);
        editText1.setSelectAllOnFocus(true);
        editText2.setSelectAllOnFocus(true);
        editText3.setSelectAllOnFocus(true);
        editText4.setSelectAllOnFocus(true);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        textView1.setText("نام خط تولید");
        textView2.setText(" خط تولید قبلی");
        textView3.setText("خط تولید بعدی");
        textView4.setText("وزن خط تولید");
        editText1.setText(Data.productionLines.get(selectedIndex).getName().replace("null" , ""));
        editText2.setText(Data.productionLines.get(selectedIndex).getPreviousName().replace("null" , ""));
        editText3.setText(Data.productionLines.get(selectedIndex).getNextName().replace("null" , ""));
        editText4.setText("" + Data.productionLines.get(selectedIndex).getWeight());
        editText4.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show();
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  private void stationAllertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        selectedIndex = i;
      }
    });
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        confirm.setVisibility(View.VISIBLE);
        editText1.setVisibility(View.VISIBLE);
        editText2.setVisibility(View.VISIBLE);
        editText3.setVisibility(View.VISIBLE);
        editText4.setVisibility(View.VISIBLE);
        editText5.setVisibility(View.VISIBLE);

        editText1.setSelectAllOnFocus(true);
        editText2.setSelectAllOnFocus(true);
        editText3.setSelectAllOnFocus(true);
        editText4.setSelectAllOnFocus(true);
        editText5.setSelectAllOnFocus(true);

        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);

        textView1.setText("نام ایستگاه جدید");
        textView2.setText("خط تولید");
        textView3.setText("نام ایستگاه قبلی");
        textView4.setText("نام ایستگاه بعدی");
        textView5.setText("وزن");

        editText1.setText(Data.stations.get(selectedIndex).getName().replace("null" , ""));
        editText2.setText(Data.stations.get(selectedIndex).getProductionLineName().replace("null" , ""));
        editText3.setText(Data.stations.get(selectedIndex).getPreviousName().replace("null" , ""));
        editText4.setText(Data.stations.get(selectedIndex).getNextName().replace("null" , ""));
        editText5.setText("" + Data.stations.get(selectedIndex).getWeight());
        editText5.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show();
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  private void opAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        selectedIndex = i;
      }
    });
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        confirm.setVisibility(View.VISIBLE);

        editText1.setVisibility(View.VISIBLE);
        editText2.setVisibility(View.VISIBLE);
        editText3.setVisibility(View.VISIBLE);
        editText4.setVisibility(View.VISIBLE);
        editText5.setVisibility(View.VISIBLE);
        editText6.setVisibility(View.VISIBLE);
        editText7.setVisibility(View.VISIBLE);
        editText8.setVisibility(View.VISIBLE);

        editText1.setSelectAllOnFocus(true);
        editText2.setSelectAllOnFocus(true);
        editText3.setSelectAllOnFocus(true);
        editText4.setSelectAllOnFocus(true);
        editText5.setSelectAllOnFocus(true);
        editText6.setSelectAllOnFocus(true);
        editText7.setSelectAllOnFocus(true);
        editText8.setSelectAllOnFocus(true);

        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);
        textView6.setVisibility(View.VISIBLE);
        textView7.setVisibility(View.VISIBLE);
        textView8.setVisibility(View.VISIBLE);

        textView1.setText("نام OP جدید");
        textView2.setText("نام پروژه");
        textView3.setText("ایستگاه");
        textView4.setText("BOM");
        textView5.setText("دستورالعمل");
        textView6.setText("نام OP قبلی");
        textView7.setText("نام OP بعدی");
        textView8.setText("وزن");


        editText1.setText(Data.ops.get(selectedIndex).getName().replace("null" , ""));
        editText2.setText(Data.ops.get(selectedIndex).getProductTypeName().replace("null" , ""));
        editText3.setText(Data.ops.get(selectedIndex).getStationName().replace("null" , ""));
        editText4.setText(Data.ops.get(selectedIndex).getbOMName().replace("null" , ""));
        editText5.setText(Data.ops.get(selectedIndex).getInstructionName().replace("null" , ""));
        editText6.setText(Data.ops.get(selectedIndex).getPreviousName().replace("null" , ""));
        editText7.setText(Data.ops.get(selectedIndex).getNextName().replace("null" , ""));
        editText8.setText("" + Data.ops.get(selectedIndex).getWeight());
        editText8.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show();
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //-----------------------------------------------------------------------------------------------------------------------------
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

}