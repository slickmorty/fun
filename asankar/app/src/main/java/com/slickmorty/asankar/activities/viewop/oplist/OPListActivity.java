package com.slickmorty.asankar.activities.viewop.oplist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.activities.viewop.ViewOPAdapter;
import com.slickmorty.asankar.data.OP;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OPListActivity extends AppCompatActivity implements ViewOPAdapter.OnClickListener {


  //  UI
  private RecyclerView recyclerView;

  //  Adaptor
  ViewOPAdapter adapter;

  //  Var
  private String productTypeName;
  private String stationName;
  ArrayList<String> names;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_oplist);


    recyclerView = findViewById(R.id.recycleview2);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    Intent intent = getIntent();

    productTypeName = intent.getStringExtra("product type name");
    stationName = intent.getStringExtra("station name");

    names = new ArrayList<>();
    for (OP op : Data.ops) {
      if (op.getStationName().equals(stationName) && op.getProductTypeName().equals(productTypeName)) {
        names.add(op.getName());
      }
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
    Log.d("tag", " op list activity closed");
  }


  @Override
  public void onClick(int position) {
//    Intent intent = new Intent(this, OPActivity.class);
//    intent.putExtra("op name" , names.get(position));
//    startActivity(intent);
  }
}