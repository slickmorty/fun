package com.slickmorty.asankar.activities.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.activities.engineering.EngineeringActivity;
import com.slickmorty.asankar.activities.planning.PlanningActivity;
import com.slickmorty.asankar.activities.production.ProductionActivity;
import com.slickmorty.asankar.activities.profile.ProfileActivity;
import com.slickmorty.asankar.activities.qc.QCActivity;
import com.slickmorty.asankar.activities.viewop.prlist.PrListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainPageActivity extends AppCompatActivity {

  Toolbar toolbar;

  ImageButton viewOP;
  ImageButton engineering;
  ImageButton profile;
  ImageButton office;
  ImageButton qC;
  ImageButton production;
  ImageButton planning;
  ImageButton educational;
  ImageButton suggestions;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_page);

    Data.getUserProfileInfo(getApplicationContext());
    Data.getPrLineList(getApplicationContext());
    Data.getProductTypesList(getApplicationContext());
    Data.getOPsList(getApplicationContext());
    Data.getStationsList(getApplicationContext());
    Data.getProductsList(getApplicationContext());
    Data.getUsersList(getApplicationContext());
    Data.getWorkList(getApplicationContext());
    init();

    toolbar = findViewById(R.id.toolbar_mainpage);
    setSupportActionBar(toolbar);

    toolbar.setOnClickListener(view -> {
      Log.d("tag", Data.userProfile.getFamilyName() + Data.userProfile.getName() + Data.userProfile.getUserType() + Data.userProfile.getIdentification()
        + Data.userProfile.getPassWord() + Data.userProfile.getUserName());
      Intent intent = new Intent(MainPageActivity.this, ProjectControlMain.class);
      startActivity(intent);
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.mainpage_toolbar_menu, menu);
    return true;
  }


  private void init() {

    profile = findViewById(R.id.img_me);
    engineering = findViewById(R.id.img_engineering);
    viewOP = findViewById(R.id.img_op);
    production = findViewById(R.id.img_production);
    qC = findViewById(R.id.img_qc);
    planning = findViewById(R.id.img_planning);
    educational = findViewById(R.id.img_educational);
    suggestions = findViewById(R.id.img_suggestions);
    office = findViewById(R.id.img_office);

    //******************************************************************************************/

    profile.setOnClickListener(view -> {
      Intent intent = new Intent(MainPageActivity.this, ProfileActivity.class);
      startActivity(intent);
    });

    //*****************************************************************************************/

    engineering.setOnClickListener(view -> {
      if (Data.userProfile.getUserType().equals("admin") || Data.userProfile.getUserType().equals("engineering")) {
        Intent intent = new Intent(MainPageActivity.this, EngineeringActivity.class);
        startActivity(intent);
      } else
        Toast.makeText(getApplicationContext(), "شما به این پنل دسترسی ندارید", Toast.LENGTH_SHORT).show();
    });

    //******************************************************************************************/

    viewOP.setOnClickListener(view -> {
      Intent intent = new Intent(MainPageActivity.this, PrListActivity.class);
      startActivity(intent);
    });

    //******************************************************************************************/

    production.setOnClickListener(view -> {
      Intent intent = new Intent(MainPageActivity.this, ProductionActivity.class);
      startActivity(intent);
    });


    //******************************************************************************************/

    qC.setOnClickListener(view -> {
      Intent intent = new Intent(MainPageActivity.this, QCActivity.class);
      startActivity(intent);
    });

    //******************************************************************************************/

    planning.setOnClickListener(view -> {

      if (Data.userProfile.getUserType().equals("admin") || Data.userProfile.getUserType().equals("planning")) {
        Intent intent = new Intent(MainPageActivity.this, PlanningActivity.class);
        startActivity(intent);
      } else
        Toast.makeText(getApplicationContext(), "شما به این پنل دسترسی ندارید", Toast.LENGTH_SHORT).show();
    });

    //******************************************************************************************/

    educational.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "بخش آموزشی در حال آماده سازی است", Toast.LENGTH_SHORT).show());

    //******************************************************************************************/

    suggestions.setOnClickListener(view -> {
//        Intent intent = new Intent(MainPageActivity.this, SuggestionActivity.class);
//        startActivity(intent);
      Toast.makeText(getApplicationContext(), "بخش پیشنهادات در حال آماده سازی است", Toast.LENGTH_SHORT).show();
    });


    //****************************************************************************************/

    office.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "بخش اداری در حال آماده سازی است", Toast.LENGTH_SHORT).show());

    //******************************************************************************************/

  }


}
