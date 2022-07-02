package com.slickmorty.asankar.activities.planning;

import android.os.Bundle;

import com.slickmorty.asankar.R;

import androidx.appcompat.app.AppCompatActivity;

public class PlanningDeleteActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_planning_delete);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}