package com.slickmorty.asankar.activities.planning.reports;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.data.Product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ReportsActivity extends AppCompatActivity {

  private Button personBased;
  private Button dateBased;
  private Button productBased;
  private int selectedIndex;
  private int poductID;
  private CharSequence[] options;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reports);

    personBased = findViewById(R.id.report_button_person);
    dateBased = findViewById(R.id.report_button_date);
    productBased = findViewById(R.id.report_button_product);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    personBased.setOnClickListener(v -> {

    });
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    productBased.setOnClickListener(v -> {
      options = new CharSequence[Data.products.size()];
      int i=0;
      for(Product product : Data.products){
        options[i]= product.getProductID() +"محصول شماره";
        i++;
      }
      productAlertDialog(options);
    });
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    dateBased.setOnClickListener(v -> {


    });


  }
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  private void productAlertDialog(CharSequence... options) {
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
        Intent intent = new Intent(getApplicationContext() , ProductPDFActivity.class);
        startActivity(intent);
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

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private void showProductMessageDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View view = inflater.inflate(R.layout.layout_production_description , null);
    EditText editText1 = view.findViewById(R.id.layout_prod_des);
    editText1.setHint("شماره محصول مورد نظر را وارد کنید");
    builder.setView(view)
      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
      });
    AlertDialog dialog = builder.create();
    dialog.show();
  }
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}