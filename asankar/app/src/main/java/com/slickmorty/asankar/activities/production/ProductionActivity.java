package com.slickmorty.asankar.activities.production;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.slickmorty.asankar.Data;
import com.slickmorty.asankar.R;
import com.slickmorty.asankar.activities.production.dailyactivity.DailyReportActivity;
import com.slickmorty.asankar.data.Product;
import com.slickmorty.asankar.data.UserProfile;
import com.slickmorty.asankar.data.Work;

import java.sql.Date;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ProductionActivity extends AppCompatActivity {

  //Variables
  private TextView selectOP;
  private TextView dailyAcvitity;
  private Button confirm;
  private Button progress;
  private ProgressBar progressBar;
  private double progressN=0;
  private Work selectedWork = null;
  private TextView viewWork;
  private int selectedIndex =-1;
  private CharSequence[] options;
  private int[] options2;
  private Handler handler;


  private String description= "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_production);
    handler = new Handler();
    init();




    int counter = 0;
    for(Work work : Data.works){
      if(!work.isQcCheck() && !work.isWorkerCheck())
        counter++;
    }
    options = new CharSequence[counter];
    options2= new int[counter];


    //-----------------------------------------------------------------------------//

    selectOP.setOnClickListener(view -> {
      int i = 0;
      for(Work work : Data.works){
        if(!work.isQcCheck()&& !work.isWorkerCheck()) {
          options[i] = work.getOpName() +" " + "شماره محصول"+" " +work.getProductID() ;
          options2[i] = work.getId();
          i++;
        }
      }
      showWorksListDialog(options);
    });

    //-----------------------------------------------------------------------------//

    confirm.setOnClickListener(v -> {
      showMessageDialog();
      progress.setVisibility(View.GONE);
      confirm.setVisibility(View.GONE);


    });

    //-----------------------------------------------------------------------------//

    progress.setOnClickListener(v -> {
      progress.setVisibility(View.GONE);
      confirm.setVisibility(View.GONE);
      showProgressDialog();
    });

    //-----------------------------------------------------------------------------//

    dailyAcvitity.setOnClickListener(v -> {
      Intent intent = new Intent(this , DailyReportActivity.class);
      startActivity(intent);
    });

  }


  //***********************************************************************************************************************************//
  private void showWorksListDialog(final CharSequence... options) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setSingleChoiceItems(options, -1, (dialogInterface, i) -> selectedIndex = i);
    builder.setPositiveButton("OK", (dialogInterface, i) -> {
      if(selectedIndex >=0) {
        for(Work work : Data.works){
          if(work.getId()==options2[selectedIndex]){
            selectedWork = work;
            progress.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.VISIBLE);
            viewWork.setVisibility(View.VISIBLE);
            String productType ="" ;
            for(Product product : Data.products){
               if(product.getProductID() == selectedWork.getProductID())
                 productType = product.getProductTypeName();
            }

            String productionName="";
            for(UserProfile userProfile :Data.userProfiles)
              if(userProfile.getIdentification()==selectedWork.getWorkerID()){
                productionName=userProfile.getName()+" "+ userProfile.getFamilyName();
                break;
              }
                viewWork.setText(
                  "جزئیات کار در حال انجام"+ " \n"+
                    "نام متولی: "+productionName + " \n" +
                    "نام OP مورد نظر: "+selectedWork.getOpName() + " \n" +
                    "نوع محصول:" + " " + productType + " \n" +
                    "شماره محصول:" + " " + selectedWork.getProductID()+ " \n" +
                    "تاریخ شروع برنامه ریزی:" + " " + new PersianCalendar(selectedWork.getLongplanStartDate()).getPersianLongDate() + " \n" +
                    "تاریخ پایان برنامه ریزی:" + " " + new PersianCalendar(selectedWork.getLongPlanFinishDate()).getPersianLongDate()+ " \n" +
                    "توضیحات برنامه ریزی:" + " " + selectedWork.getPlanDescription().replace("null" , "")+ " \n" +
                    "توضیحات کنترل کیفی:" + " " + selectedWork.getQcDescription().replace("null" , ""));
            return;
          }
        }

      }
    });
    builder.setNegativeButton("Cancel", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show());
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //***********************************************************************************************************************************//
  private void showMessageDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View view = inflater.inflate(R.layout.layout_production_description , null);
    EditText editText1 = view.findViewById(R.id.layout_prod_des);
    builder.setView(view)
      .setPositiveButton("OK", (dialog, which) -> {
        description = editText1.getText().toString();
        selectedWork.setWorkerFinishDate(new Date(System.currentTimeMillis()));
        selectedWork.setWorkerCheck(true);
        selectedWork.setWorkerDescription(description);
        selectedWork.setProgress(progressN);
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

      });
        AlertDialog dialog = builder.create();
    dialog.show();
  }



  //************************************************************************************************************************************************


  private void showProgressDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View view = inflater.inflate(R.layout.layout_production_progress , null);
    TextView textView = view.findViewById(R.id.layout_prod_progress_txt);
    SeekBar seekBar = view.findViewById(R.id.layout_prod_progress_seekbar);
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                         @Override
                                         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                           textView.setText(""+progress +" %" );
                                         }

                                         @Override
                                         public void onStartTrackingTouch(SeekBar seekBar) {
                                         }

                                         @Override
                                         public void onStopTrackingTouch(SeekBar seekBar) {

                                         }
                                       });
    seekBar.setProgress((int)selectedWork.getProgress());
    Log.d("tag" ,""+ selectedWork.getProgress());


      builder.setView(view)
        .setPositiveButton("تایید", (dialog, which) -> {
          progressN = seekBar.getProgress();
          if(selectedWork.getWorkerStartDate()==null || selectedWork.getLongWorkerStartDate() == 0){
            selectedWork.setWorkerStartDate(new Date(System.currentTimeMillis()));
          }
          selectedWork.setProgress(progressN);
          Data.updateWorkList(getApplicationContext(),selectedWork);
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

        })
        .setNegativeButton("لغو" , (dialog, which) -> {

        })
        .setTitle("درصد پیشرفت");
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //************************************************************************************************************************************************
  private void init(){
    selectOP = findViewById(R.id.pr_textview_selectwork);
    confirm = findViewById(R.id.pr_btn_confirm);
    viewWork = findViewById(R.id.prod_viewwork_textview);
    progress = findViewById(R.id.pr_btn_progress);
    progressBar=findViewById(R.id.prod_progressBar);
    dailyAcvitity= findViewById(R.id.pr_textview_dailyactivity);


  }
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }


}