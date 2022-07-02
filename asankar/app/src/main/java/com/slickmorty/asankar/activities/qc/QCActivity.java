package com.slickmorty.asankar.activities.qc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.data.Product;
import com.slickmorty.asankar.data.UserProfile;
import com.slickmorty.asankar.data.Work;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class QCActivity extends AppCompatActivity {

  //Variable
  private Button view;
  private Button confirm;
  private Button decline;
  private TextView viewWork;
  private Work selectedWork = null;
  private String qcDescription = null;
  private int selectedIndex =-1;
  private CharSequence[] options;
  private int[] options1;
  private Handler handler;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_qc);
    handler = new Handler();

    viewWork = findViewById(R.id.qc_textview_textbox);
    view= findViewById(R.id.qc_button_view);
    confirm= findViewById(R.id.qc_button_confirm);
    decline= findViewById(R.id.qc_button_decline);
    progressBar= findViewById(R.id.qc_progressBar);


    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        selectedIndex= -1;
        int counter = 0;
        for(Work work : Data.works){
          if(!work.isQcCheck() && work.isWorkerCheck()) {
            counter++;
          }
        }
        options = new CharSequence[counter];
        options1= new int[counter];

        int i = 0;
        for(Work work : Data.works){
          if(!work.isQcCheck()&& work.isWorkerCheck()) {
            options[i] = work.getOpName() + " " + " محصول شماره "
              +work.getProductID();
            options1[i]= work.getId();
            i++;
          }
        }
        showDialog(options);
      }
    });

    confirm.setOnClickListener(v -> {
      showMessageDialog();

    });

    decline.setOnClickListener(v -> {
      showMessageDialog2();

    });

  }

  private void showDialog(final CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        selectedIndex = i;
      }
    });
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        if(selectedIndex <0){
          return;
        }
        for(Work work : Data.works){
          if(work.getId()==options1[selectedIndex]){
            selectedWork = work;
            break;
          }
        }
        viewWork.setVisibility(View.VISIBLE);

        //-----------------------------------
        String productType ="" ;
        for(Product product : Data.products)
          if(product.getProductID() == selectedWork.getProductID()) {
            productType = product.getProductTypeName();
            break;
          }

        //----------------
        String productionName="";
        for(UserProfile userProfile :Data.userProfiles)
          if(userProfile.getIdentification()==selectedWork.getWorkerID()){
            productionName=userProfile.getName()+" "+ userProfile.getFamilyName();
            break;
          }

        //-----------------------------------
        viewWork.setText(
          "جزئیات کار انجام شده"+ " \n"+
            "نام OP مورد نظر: "+selectedWork.getOpName() + " \n" +
            "نوع محصول:" + " " + productType + " \n" +
            "شماره محصول:" + " " + selectedWork.getProductID()+ " \n" +
            "نیروی تولید:" + " " + productionName+ " \n" +
            "تاریخ شروع برنامه ریزی:" + " " + new PersianCalendar(selectedWork.getLongplanStartDate()).getPersianLongDate() + " \n" +
            "تاریخ پایان برنامه ریزی:" + " " + new PersianCalendar(selectedWork.getLongPlanFinishDate()).getPersianLongDate()+ " \n" +
            "توضیحات برنامه ریزی:" + " " + selectedWork.getPlanDescription().replace("null" , "")+ " \n" +
            "تاریخ شروع نیروی تولید:" + " " + new PersianCalendar(selectedWork.getLongWorkerStartDate()).getPersianLongDate()+ " \n" +
            "تاریخ پایان نیروی تولید:" + " " + new PersianCalendar(selectedWork.getLongWorkerFinishDate()).getPersianLongDate()+ " \n" +
            "توضیحات نیروی تولید:" + " " + selectedWork.getWorkerDescription().replace("null" , ""));
        confirm.setVisibility(View.VISIBLE);
        decline.setVisibility(View.VISIBLE);

      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show();
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void showMessageDialog2(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View view = inflater.inflate(R.layout.layout_production_description , null);
    EditText editText1 = view.findViewById(R.id.layout_prod_des);
    builder.setView(view)
      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          qcDescription = editText1.getText().toString();
          selectedWork.setWorkerCheck(false);
          selectedWork.setQcDescription(qcDescription);
          Data.updateWorkList(getApplicationContext() , selectedWork);
          progressBar.setVisibility(View.VISIBLE);
          new Thread(() -> {
            while(true){
              if(Data.isWorksUpdated !=0){
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
          confirm.setVisibility(View.INVISIBLE);
          decline.setVisibility(View.INVISIBLE);
        }
      });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void showMessageDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View view = inflater.inflate(R.layout.layout_production_description , null);
    EditText editText1 = view.findViewById(R.id.layout_prod_des);
    builder.setView(view)
      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          qcDescription = editText1.getText().toString();
          selectedWork.setQcCheck(true);
          selectedWork.setQcDescription(qcDescription);
          Data.updateWorkList(getApplicationContext() , selectedWork);
          progressBar.setVisibility(View.VISIBLE);
          new Thread(() -> {
            while(true){
              if(Data.isWorksUpdated !=0){
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
          confirm.setVisibility(View.INVISIBLE);
          decline.setVisibility(View.INVISIBLE);
        }
      });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}