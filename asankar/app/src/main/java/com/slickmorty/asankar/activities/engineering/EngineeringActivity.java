package com.slickmorty.asankar.activities.engineering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.slickmorty.asankar.R;
import androidx.appcompat.app.AppCompatActivity;

public class EngineeringActivity extends AppCompatActivity {

  //Vars
  private TextView add;
  private TextView update;
  private TextView delete;
  private TextView bOM;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_engineering);

    add = findViewById(R.id.eng_textview_add);
    update = findViewById(R.id.eng_textview_update);
    delete = findViewById(R.id.eng_textview_delete);
    bOM = findViewById(R.id.eng_textview_bom);

    add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(EngineeringActivity.this , EngineeringAddActivity.class);
        startActivity(intent);
      }
    });
    update.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(EngineeringActivity.this , EngineeringUpdateActivity.class);
        startActivity(intent);
      }
    });
    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(EngineeringActivity.this , EngineeringDeleteActivity.class);
        startActivity(intent);
      }
    });

    bOM.setOnClickListener(v -> {
      Toast.makeText(this,"در حال آماده سازی" ,Toast.LENGTH_SHORT).show();
    });

  }

}