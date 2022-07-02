package com.slickmorty.asankar.activities.viewop.prlinelist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.activities.viewop.ViewOPAdapter;
import com.slickmorty.asankar.activities.viewop.stlist.StListActivity;
import com.slickmorty.asankar.data.ProductionLine;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PrLineListActivity extends AppCompatActivity implements ViewOPAdapter.OnClickListener {

  //  UI
  private RecyclerView recyclerView;

  //  Adaptor
  ViewOPAdapter adapter;

  //  Var
  private String productTypeName;
  private ArrayList<String> names;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_prlinelist);


    recyclerView = findViewById(R.id.recycleview3);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    Intent intent = getIntent();
    productTypeName = intent.getStringExtra("product type name");
    names = new ArrayList<>();
    for (ProductionLine productionLine : Data.productionLines) {
      names.add(productionLine.getName());
    }
    adapter = new ViewOPAdapter(names, this);
    recyclerView.setAdapter(adapter);
  }


  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("tag", " productionlines list activity closed");
  }


  @Override
  public void onClick(int position) {
    Intent intent = new Intent(this, StListActivity.class);
    intent.putExtra("product type name", productTypeName);
    intent.putExtra("production line name", names.get(position));
    startActivity(intent);
  }
}