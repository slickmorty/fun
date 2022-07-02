package com.slickmorty.asankar.activities.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

  //UI
  TextView userNAme;
  TextView userFamilyNAme;
  TextView userID;
  TextView userType;
  Button userLeaveButton;
  Button userFoodButton;
  Button userAttendenceButton;
  Button userPropertyButton;
  Button userNoteButton;
  Button userReceiptButton;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    init();

    userNAme.setText(Data.userProfile.getName());
    userFamilyNAme.setText(Data.userProfile.getFamilyName());
    userID.setText("" + Data.userProfile.getIdentification());
    userType.setText(Data.userProfile.getUserType());

  }


  private void init() {

    userNAme = findViewById(R.id.txt_username);
    userFamilyNAme = findViewById(R.id.txt_userfamilyname);
    userID = findViewById(R.id.txt_userid);
    userType = findViewById(R.id.txt_usertype);

    userLeaveButton = findViewById(R.id.btn_profile_leave);
    userFoodButton = findViewById(R.id.btn_profile_food);
    userAttendenceButton = findViewById(R.id.btn_profle_attendance);
    userPropertyButton = findViewById(R.id.btn_profile_property);
    userNoteButton = findViewById(R.id.btn_profile_note);
    userReceiptButton = findViewById(R.id.btn_profile_receipt);

    userLeaveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(ProfileActivity.this, "باشه", Toast.LENGTH_SHORT).show();
      }
    });

    userFoodButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(ProfileActivity.this, "باشه", Toast.LENGTH_SHORT).show();
      }
    });

    userAttendenceButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(ProfileActivity.this, "باشه", Toast.LENGTH_SHORT).show();
      }
    });

    userPropertyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(ProfileActivity.this, "باشه", Toast.LENGTH_SHORT).show();
      }
    });

    userNoteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(ProfileActivity.this, "باشه", Toast.LENGTH_SHORT).show();
      }
    });

    userReceiptButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(ProfileActivity.this, "باشه", Toast.LENGTH_SHORT).show();
      }
    });


    ///////////////////////////////////////////////////////////
//    txt_name.setClickable(false);
//    txt_name.setFocusable(false);
//    txt_name.setCursorVisible(false);
//    txt_name.setFocusableInTouchMode(false);
//    txt_familyName.setClickable(false);
//    txt_familyName.setFocusable(false);
//    txt_familyName.setCursorVisible(false);
//    txt_familyName.setFocusableInTouchMode(false);
    /////////////////////////////////////////////////////////////


  }

}