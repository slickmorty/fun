package com.slickmorty.asankar.activities.engineering;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EngineeringDeleteActivity extends AppCompatActivity {

  //Vars
  private int selectedIndex = -1;
  private Button deletePrLine;
  private Button deleteOP;
  private Button deleteStation;
  private Button deleteProductType;
  private String dataType = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_engineering_delete);
    init();


  }

  private void showDialog(final CharSequence... options) {
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
        int id;
        switch (dataType) {
          case "station":
            id = Data.stations.get(selectedIndex).getId();
            Data.deleteStationById(getApplicationContext(), id);
            break;
          case "production line":
            id = Data.productionLines.get(selectedIndex).getId();
            Data.deletePrLineById(getApplicationContext(), id);
            break;
          case "product type":
            id = Data.productTypes.get(selectedIndex).getId();
            Data.deletProducTypeById(getApplicationContext(), id);
            break;
          case "op":
            id = Data.ops.get(selectedIndex).getId();
            Data.deleteOPById(getApplicationContext(), id);
            break;
        }
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

  private void init() {
    deletePrLine = findViewById(R.id.engdelet_btn_prline);
    deleteOP = findViewById(R.id.engdelet_btn_op);
    deleteStation = findViewById(R.id.engdelet_btn_station);
    deleteProductType = findViewById(R.id.engdelet_btn_prtype);

    deletePrLine.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        CharSequence[] options = new CharSequence[Data.productionLines.size()];
        for (int i = 0; i < Data.productionLines.size(); i++) {
          options[i] = Data.productionLines.get(i).getName();
        }
        dataType = "production line";
        showDialog(options);
      }
    });
    deleteProductType.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        CharSequence[] options = new CharSequence[Data.productTypes.size()];
        for (int i = 0; i < Data.productTypes.size(); i++) {
          options[i] = Data.productTypes.get(i).getName();
        }
        dataType = "product type";
        showDialog(options);
      }
    });
    deleteStation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        CharSequence[] options = new CharSequence[Data.stations.size()];
        for (int i = 0; i < Data.stations.size(); i++) {
          options[i] = Data.stations.get(i).getName();
        }
        dataType = "station";
        showDialog(options);
      }
    });
    deleteOP.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        CharSequence[] options = new CharSequence[Data.ops.size()];
        for (int i = 0; i < Data.ops.size(); i++) {
          options[i] = Data.ops.get(i).getName();
        }
        dataType = "op";
        showDialog(options);
      }
    });
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}