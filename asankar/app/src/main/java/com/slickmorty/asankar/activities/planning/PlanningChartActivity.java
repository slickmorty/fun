package com.slickmorty.asankar.activities.planning;

import android.os.Bundle;

import com.slickmorty.asankar.R;

import androidx.appcompat.app.AppCompatActivity;

public class PlanningChartActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_planning_chart);

//    AnyChartView anyChartView = findViewById(R.id.any_chart_view);
//
//    Cartesian cartesian = AnyChart.column();
//
//    List<DataEntry> data = new ArrayList<>();
//    data.add(new ValueDataEntry("نصب ورق کوبی بدنه", 80));
//    data.add(new ValueDataEntry("اصلاح و ترمیم قطعات فایبرگلاس", 20));
//    data.add(new ValueDataEntry("نصب درب های جعبه", 10));
//    data.add(new ValueDataEntry("نصب درب موتور", 35));
//    data.add(new ValueDataEntry("نصب موتور و گیربکس", 100));
//
//    Column column = cartesian.column(data);
//
//    column.tooltip()
//      .titleFormat("{%X}")
//      .position(Position.CENTER_BOTTOM)
//      .anchor(Anchor.CENTER_BOTTOM)
//      .offsetX(0d)
//      .offsetY(5d)
//      .format("%{%Value}{groupsSeparator: }");
//
//    cartesian.animation(true);
//    cartesian.title(" فرآیندهای تولید ایستگاه سندبلاست");
//
//    cartesian.yScale().minimum(0d);
//    cartesian.yScale().maximum(100);
//
//    cartesian.yAxis(0).labels().format("%{%Value}{groupsSeparator: }");
//
//    cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//    cartesian.interactivity().hoverMode(HoverMode.BY_X);
//
//    cartesian.xAxis(0).title("فرآیندهای تولید");
//    cartesian.yAxis(0).title("درصد پیشرفت");
//
//    anyChartView.setChart(cartesian);
//  }
//
//
//  @Override
//  public void onBackPressed() {
//    super.onBackPressed();
//    finish();
 }
}