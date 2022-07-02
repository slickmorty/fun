package com.slickmorty.asankar.activities.viewop.prlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.activities.viewop.ViewOPAdapter;
import com.slickmorty.asankar.activities.viewop.prlinelist.PrLineListActivity;
import com.slickmorty.asankar.data.ProductType;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PrListActivity extends AppCompatActivity implements ViewOPAdapter.OnClickListener {

  // UI
  private RecyclerView recyclerView;

  // Adapter
  private ViewOPAdapter adapter;

  //Vars
  ArrayList<String> productTypes;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_prlist);

    productTypes = new ArrayList<>();
    for (ProductType productType : Data.productTypes) {
      productTypes.add(productType.getName());
    }
    recyclerView = findViewById(R.id.recyclerview);
    recyclerView.setLayoutManager(new LinearLayoutManager(PrListActivity.this));
    adapter = new ViewOPAdapter(productTypes, this);
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
    Log.d("tag", " prlist activity closed");
  }

  @Override
  public void onClick(int position) {
    Intent intent = new Intent(this, PrLineListActivity.class);
    intent.putExtra("product type name" , productTypes.get(position));
    startActivity(intent);
  }
}