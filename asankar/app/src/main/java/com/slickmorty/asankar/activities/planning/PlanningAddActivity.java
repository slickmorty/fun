package com.slickmorty.asankar.activities.planning;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.data.OP;
import com.slickmorty.asankar.data.Product;
import com.slickmorty.asankar.data.ProductType;
import com.slickmorty.asankar.data.UserProfile;
import com.slickmorty.asankar.data.Work;

import java.sql.Date;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class PlanningAddActivity extends AppCompatActivity {

  //Vars
  private EditText editText1;
  private EditText editText2;
  private EditText editText3;
  private EditText editText4;


  private Button buttonProduct;
  private Button buttonWork;

  private int selectedIndex = -1;
  private CharSequence[] stringOption;
  private int[] intOptions;

  private ImageView confirm;
  private Date planStartDate;
  private Date planFinishDate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_planning_add);

    editText1 = findViewById(R.id.planadd_edtext_1);
    editText2 = findViewById(R.id.planadd_edtext_2);
    editText3 = findViewById(R.id.planadd_edtext_3);
    editText4 = findViewById(R.id.planadd_edtext_4);


    buttonProduct = findViewById(R.id.planadd_btn_addpr);
    buttonWork = findViewById(R.id.planadd_btn_addwork);
    confirm = findViewById(R.id.planadd_imgbtn_confirm);
    editText2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    editText3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


    //---------------------------------------------------------------------------
    buttonProduct.setOnClickListener(view -> {
      confirm.setTag("add product");
      Log.d("tag", "    " + confirm.getTag().toString());
      editText1.setVisibility(View.GONE);
      editText2.setVisibility(View.GONE);
      editText3.setVisibility(View.GONE);
      editText4.setVisibility(View.GONE);

      confirm.setVisibility(View.VISIBLE);
      editText1.setText(null);
      editText1.setVisibility(View.VISIBLE);
      editText2.setText(null);
      editText2.setVisibility(View.VISIBLE);
      editText1.setHint("نام پروژه");
      editText2.setHint("شماره محصول");
    });

    //---------------------------------------------------------------------------
    buttonWork.setOnClickListener(view -> {
      confirm.setTag("add work");
      Log.d("tag", "    " + confirm.getTag().toString());
      confirm.setVisibility(View.VISIBLE);
      editText1.setVisibility(View.GONE);
      editText2.setVisibility(View.GONE);
      editText3.setVisibility(View.GONE);
      editText4.setVisibility(View.GONE);


      editText1.setText(null);
      editText1.setVisibility(View.VISIBLE);
      editText2.setText(null);
      editText2.setVisibility(View.VISIBLE);
      editText3.setText(null);
      editText3.setVisibility(View.VISIBLE);
      editText4.setText(null);
      editText4.setVisibility(View.VISIBLE);

      editText1.setHint("نام OP");
      editText2.setHint("شماره محصول");
      editText3.setHint("شماره متولی");
      editText4.setHint("توضیحات");
    });

    //------------------------------------------------------------------------------------------------------------------------------------------
    confirm.setOnClickListener(view -> {
      closeKeyboard();
      if ("add product".equals(confirm.getTag().toString())) {
        for (Product product : Data.products) {
          if (product.getProductID() == Integer.parseInt(editText2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "محصولی با این شماره قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
            confirm.setVisibility(View.GONE);
            editText1.setVisibility(View.GONE);
            editText2.setVisibility(View.GONE);
            return;
          }
        }
        boolean flag = true;
        for (ProductType productType : Data.productTypes) {
          if (productType.getName().equals(editText1.getText().toString())) {
            flag = false;
          }
        }

        if (flag) {
          Toast.makeText(getApplicationContext(), "این پروژه وجود ندارد", Toast.LENGTH_SHORT).show();
          confirm.setVisibility(View.GONE);
          editText1.setVisibility(View.GONE);
          editText2.setVisibility(View.GONE);
          return;
        }

        if (editText2.getText().toString().equals("")) {
          editText1.setText(null);
          Toast.makeText(getApplicationContext(), "شماره محصول نمیتواند خالی باشد", Toast.LENGTH_SHORT).show();
          return;
        }
        Data.addToProductsList(getApplicationContext(), Integer.parseInt(editText2.getText().toString()), editText1.getText().toString());
        confirm.setVisibility(View.GONE);
        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);

      }
      //--------------------------------------------------------------------
      //TODO make edittext 2 , 3 like edittext 1 which takes ops
      else if ("add work".equals(confirm.getTag().toString())) {
        boolean flagWorker = false;
        boolean flagProduct = false;
        boolean flagOP = false;
        if (editText2.getText().toString().equals(""))
          editText2.setText("0");
        if (editText3.getText().toString().equals(""))
          editText3.setText("0");
        for (Product product : Data.products) {
          if (product.getProductID() == Integer.parseInt(editText2.getText().toString())) {
            flagProduct = true;
          }
        }
        for (OP op : Data.ops) {
          if (op.getName().equals(editText1.getText().toString())) {
            flagOP = true;
          }
        }
        for (UserProfile userProfile : Data.userProfiles) {
          if (userProfile.getIdentification() == Integer.parseInt(editText3.getText().toString())) {
            flagWorker = true;
          }
        }
        if (!flagOP) {
          Toast.makeText(getApplicationContext(), "چنین OP وجود ندارد", Toast.LENGTH_SHORT).show();
          editText1.setVisibility(View.GONE);
          editText2.setVisibility(View.GONE);
          editText3.setVisibility(View.GONE);
          editText4.setVisibility(View.GONE);
          return;
        }
        if (!flagProduct) {
          Toast.makeText(getApplicationContext(), "چنین محصولی وجود ندارد", Toast.LENGTH_SHORT).show();
          editText1.setVisibility(View.GONE);
          editText2.setVisibility(View.GONE);
          editText3.setVisibility(View.GONE);
          editText4.setVisibility(View.GONE);

          return;
        }
        if (!flagWorker) {
          Toast.makeText(getApplicationContext(), "چنین شخصی وجود ندارد", Toast.LENGTH_SHORT).show();
          editText1.setVisibility(View.GONE);
          editText2.setVisibility(View.GONE);
          editText3.setVisibility(View.GONE);
          editText4.setVisibility(View.GONE);
          return;
        }
        if (editText4.getText().toString().equals("")) {
          editText4.setText("null");
        }
        showDatePickerDialog1();
        Toast.makeText(getApplicationContext(), "تاریخ شروع کار را انتخاب کنید", Toast.LENGTH_SHORT).show();
        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);
      }
    });
    //---------------------------------------------------------------------------
    editText1.setOnLongClickListener(v -> {
      int i = 0;
      if (confirm.getTag().toString().equals("add work")) {
        stringOption = new CharSequence[Data.ops.size()];
        for (OP op : Data.ops) {
          stringOption[i] = op.getName();
          i++;
        }
        opAlertDialog(stringOption);
      } else if (confirm.getTag().toString().equals("add product")) {
        stringOption = new CharSequence[Data.productTypes.size()];
        for (ProductType productType : Data.productTypes) {
          stringOption[i] = productType.getName();
          i++;
        }
        productTypeAllertDialog(stringOption);
      }
      return true;
    });

    //---------------------------------------------------------------------------
    editText2.setOnLongClickListener(v -> {
      if (confirm.getTag().toString().equals("add work")) {
        int i = 0;
        stringOption = new CharSequence[Data.products.size()];
        intOptions = new int[Data.products.size()];

        for (Product product : Data.products) {
          stringOption[i] = product.getProductTypeName() + " " + "به شماره" + " " + product.getProductID();
          intOptions[i] = product.getProductID();
          i++;
        }
        productAlertDialog(stringOption);
      }
      return true;
    });
    //---------------------------------------------------------------------------
    editText3.setOnLongClickListener(v -> {
      if (confirm.getTag().toString().equals("add work")) {
        int i = 0;
        stringOption = new CharSequence[Data.userProfiles.size()];
        intOptions = new int[Data.userProfiles.size()];

        for (UserProfile userProfile : Data.userProfiles) {
          stringOption[i] = userProfile.getName() + " " + userProfile.getFamilyName() + " " + "به شماره" + " " + userProfile.getIdentification();
          intOptions[i] = userProfile.getIdentification();
          i++;
        }
        workerAlertDialog(stringOption);
      }
      return true;
    });

  }


  /****************************************************************************************************************************************************   */

  private void closeKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }


  /****************************************************************************************************************************************************   */
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  /****************************************************************************************************************************************************   */
  private void showDatePickerDialog1() {
    PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
      .setPositiveButtonString("تایید")
      .setNegativeButton("لغو")
      .setTodayButton("امروز")
      .setTodayButtonVisible(true)
      .setMinYear(1395)
      .setMaxYear(1450)
      .setActionTextColor(Color.GRAY)
      .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
      .setShowInBottomSheet(true)
      .setListener(new Listener() {
        @Override
        public void onDateSelected(PersianCalendar persianCalendar) {
          planStartDate = new Date(persianCalendar.getTime().getTime());
          Toast.makeText(getApplicationContext(), "تاریخ پایان کار را انتخاب کنید", Toast.LENGTH_SHORT).show();
          showDatePickerDialog2();
        }

        @Override
        public void onDismissed() {
        }
      });
    picker.show();
  }

  /****************************************************************************************************************************************************   */
  private void showDatePickerDialog2() {
    PersianDatePickerDialog secondpicker = new PersianDatePickerDialog(this)
      .setPositiveButtonString("ثبت کار")
      .setNegativeButton("لغو")
      .setTodayButton("امروز")
      .setTodayButtonVisible(true)
      .setMinYear(1395)
      .setMaxYear(1450)
      .setActionTextColor(Color.GRAY)
      .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
      .setShowInBottomSheet(true)
      .setListener(new Listener() {
        @Override
        public void onDateSelected(PersianCalendar persianCalendar) {
          planFinishDate = new Date(persianCalendar.getTime().getTime());
          Work work = new Work(editText1.getText().toString(), Integer.parseInt(editText2.getText().toString()),
            Integer.parseInt(editText3.getText().toString()), planStartDate, planFinishDate);
          work.setPlanDescription(editText4.getText().toString());
          Data.addToWorksList(getApplicationContext(), work);
          confirm.setVisibility(View.GONE);
          //TODO ezafe kardane inke yek kard ra 2 bar sabt nakonim
        }

        @Override
        public void onDismissed() {
        }
      });
    secondpicker.show();
  }

  /****************************************************************************************************************************************************   */
  private void productTypeAllertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if (selectedIndex < 0)
        return;
      editText1.setText(Data.productTypes.get(selectedIndex).getName().replace("null", ""));
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  /*      ***************************************************************************************************************************************************   */
  private void opAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if (selectedIndex < 0)
        return;
      editText1.setText(Data.ops.get(selectedIndex).getName().replace("null", ""));
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  /*      ***************************************************************************************************************************************************   */
  private void productAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if (selectedIndex < 0)
        return;
      editText2.setText("" + intOptions[selectedIndex]);
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  /*      ***************************************************************************************************************************************************   */
  private void workerAlertDialog(CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("تایید", (dialogInterface, i) -> {
      if (selectedIndex < 0)
        return;
      editText3.setText("" + intOptions[selectedIndex]);
    });
    builder.setNegativeButton("لغو", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }


}

/*
PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
      .setPositiveButtonString("باشه")
      .setNegativeButton("بیخیال")
      .setTodayButton("امروز")
      .setTodayButtonVisible(true)
      .setMinYear(1395)
      .setMaxYear(1450)
      .setActionTextColor(Color.GRAY)
      .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
      .setShowInBottomSheet(true)
      .setListener(new Listener() {
        @Override
        public void onDateSelected(PersianCalendar persianCalendar) {
          Log.d("TAG", "onDateSelected: " + persianCalendar.getGregorianChange());//Fri Oct 15 03:25:44 GMT+04:30 1582
          Log.d("TAG", "onDateSelected: " + persianCalendar.getTimeInMillis());//1583253636577
          Log.d("TAG", "onDateSelected: " + persianCalendar.getTime());//Tue Mar 03 20:10:36 GMT+03:30 2020
          Log.d("TAG", "onDateSelected: " + persianCalendar.getDelimiter());//  /
          Log.d("TAG", "onDateSelected: " + persianCalendar.getPersianLongDate());// سه‌شنبه  13  اسفند  1398
          Log.d("TAG", "onDateSelected: " + persianCalendar.getPersianLongDateAndTime()); //سه‌شنبه  13  اسفند  1398 ساعت 20:10:36
          Log.d("TAG", "onDateSelected: " + persianCalendar.getPersianMonthName()); //اسفند
          Log.d("TAG", "onDateSelected: " + persianCalendar.isPersianLeapYear());//false
          Toast.makeText(getApplicationContext(), persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDismissed() {

        }
      });

    picker.show();
 */