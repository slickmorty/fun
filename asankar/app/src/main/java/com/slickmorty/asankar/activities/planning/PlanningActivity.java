package com.slickmorty.asankar.activities.planning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.slickmorty.asankar.R;

import androidx.appcompat.app.AppCompatActivity;

public class PlanningActivity extends AppCompatActivity {

  //Variables
  private TextView add;
  private TextView update;
  private TextView delete;
  private TextView reports;
  private TextView storage;
  private CharSequence[] options1;
  private CharSequence[] options2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_planning);

    add = findViewById(R.id.plan_textview_add);
    update = findViewById(R.id.plan_textview_update);
    delete = findViewById(R.id.plan_textview_delete);
    reports = findViewById(R.id.plan_textview_chart);
    storage = findViewById(R.id.plan_storagetext);




    add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(PlanningActivity.this , PlanningAddActivity.class);
        startActivity(intent);
      }
    });

    update.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "در حال توسعه", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(PlanningActivity.this , PlanningUpdateActivity.class);
//        startActivity(intent);
      }
    });
    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "در حال توسعه", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(PlanningActivity.this , PlanningDeleteActivity.class);
//        startActivity(intent);
      }
    });

    reports.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "در حال توسعه", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(PlanningActivity.this , ReportsActivity.class);
//        startActivity(intent);

      }
    });

    storage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "در حال توسعه", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(PlanningActivity.this , ReportsActivity.class);
//        startActivity(intent);

      }
    });

  }
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  //***********************************************************************************************************************************


}