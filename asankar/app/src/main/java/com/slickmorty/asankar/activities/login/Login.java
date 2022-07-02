package com.slickmorty.asankar.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.activities.mainpage.MainPageActivity;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

  //Variables
  private String userName;
  private String passWord;
  private EditText edittxt_username;
  private EditText edittxt_password;
  private Button btn_enterapp;
  private ProgressBar progressBar;
  private Handler handler;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    btn_enterapp = findViewById(R.id.btn_enterapp);
    edittxt_username = findViewById(R.id.edittxt_username);
    edittxt_password = findViewById(R.id.edittxt_password);
    progressBar = findViewById(R.id.login_progressBar);
    handler = new Handler();

    btn_enterapp.setOnClickListener(view -> {

      passWord = "" + edittxt_password.getText();
      userName = "" + edittxt_username.getText();
      Data.getUserAuth(Login.this , userName , passWord);
      progressBar.setVisibility(View.VISIBLE);
      btn_enterapp.setClickable(false);

      new Thread(() -> {
        while (true){
          if(Data.isAuthorized==1){
            Intent intent = new Intent(Login.this, MainPageActivity.class);
            startActivity(intent);
            finish();
            break;
          }
          else if(Data.isAuthorized==2){
            btn_enterapp.setClickable(true);
            handler.post(() -> progressBar.setVisibility(View.GONE));
            break;
          }
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    });

    edittxt_password.setOnLongClickListener(v -> {
      edittxt_password.setText("");
      return false;
    });

  }
}
