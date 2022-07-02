package com.slickmorty.asankar.activities.viewop.stlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.activities.viewop.ViewOPAdapter;
import com.slickmorty.asankar.activities.viewop.oplist.OPListActivity;
import com.slickmorty.asankar.data.Station;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StListActivity extends AppCompatActivity implements ViewOPAdapter.OnClickListener {

  //  UI
  private RecyclerView recyclerView;

  //  Adaptor
  ViewOPAdapter adapter;

  //  Var
  private String productTypeName = null;
  private String prodcutionLineName = null;
  private ArrayList<String> names;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stlist);

    recyclerView = findViewById(R.id.recycleview4);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    Intent intent = getIntent();
    productTypeName = intent.getStringExtra("product type name");
    prodcutionLineName = intent.getStringExtra("production line name");

    names = new ArrayList<>();
    for (Station station : Data.stations) {
      if (station.getProductionLineName().equals(prodcutionLineName)) {
        names.add(station.getName());
      }
    }
    adapter = new ViewOPAdapter(names, this);
    recyclerView.setAdapter(adapter);

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("tag", " StListActivityClosed");
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  @Override
  public void onClick(int position) {
    Intent intent = new Intent(this, OPListActivity.class);
    intent.putExtra("product type name" , productTypeName);
    intent.putExtra("station name" , names.get(position));
    startActivity(intent);
  }
}