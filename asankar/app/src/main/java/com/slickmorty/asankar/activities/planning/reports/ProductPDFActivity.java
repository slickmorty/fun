package com.slickmorty.asankar.activities.planning.reports;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.Toast;

import com.tejpratapsingh.pdfcreator.activity.PDFCreatorActivity;
import com.tejpratapsingh.pdfcreator.utils.PDFUtil;
import com.tejpratapsingh.pdfcreator.views.PDFBody;
import com.tejpratapsingh.pdfcreator.views.PDFHeaderView;
import com.tejpratapsingh.pdfcreator.views.PDFTableView;
import com.tejpratapsingh.pdfcreator.views.basic.PDFLineSeparatorView;
import com.tejpratapsingh.pdfcreator.views.basic.PDFTextView;

import java.io.File;

public class ProductPDFActivity extends PDFCreatorActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }

    createPDF("test", new PDFUtil.PDFUtilListener() {
      @Override
      public void pdfGenerationSuccess(File savedPDFFile) {
        Toast.makeText(ProductPDFActivity.this, "PDF Created", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void pdfGenerationFailure(Exception exception) {
        Toast.makeText(ProductPDFActivity.this, "PDF NOT Created", Toast.LENGTH_SHORT).show();
      }
    });

  }

  @Override
  protected PDFHeaderView getHeaderView(int page) {
    return null;
  }

  @Override
  protected PDFBody getBodyViews() {
    PDFBody pdfBody = new PDFBody();

    PDFTextView pdfCompanyNameView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
    pdfCompanyNameView.setText("پیشرو دیزل");
    pdfBody.addView(pdfCompanyNameView);
    PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.WHITE);
    pdfBody.addView(lineSeparatorView1);
    PDFTextView pdfAddressView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
    pdfBody.addView(pdfAddressView);

    String[] textInTable = {"نام مسئول", "نام OP", "تاریخ شروع", "تاریخ پایان"};

    PDFLineSeparatorView lineSeparatorView2 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.WHITE);
    pdfBody.addView(lineSeparatorView2);
    PDFTextView pdfTableTitleView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
    pdfTableTitleView.setText("جدول نمونه");
    pdfBody.addView(pdfTableTitleView);

    PDFTableView.PDFTableRowView tableHeader = new PDFTableView.PDFTableRowView(getApplicationContext());
    for (String s : textInTable) {
      PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
      pdfTextView.setText(s);
      tableHeader.addToRow(pdfTextView);
    }
    PDFTableView.PDFTableRowView tableRowView1 = new PDFTableView.PDFTableRowView(getApplicationContext());
    for (String s : textInTable) {
      PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
      pdfTextView.setText("ردیف 1 : " + s);
      tableRowView1.addToRow(pdfTextView);
    }

    PDFTableView tableView = new PDFTableView(getApplicationContext(), tableHeader, tableRowView1);
    for (int i = 0; i < 40; i++) {
      // Create 10 rows
      PDFTableView.PDFTableRowView tableRowView = new PDFTableView.PDFTableRowView(getApplicationContext());
      for (String s : textInTable) {
        PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfTextView.setText("ردیف " + (i + 2) + ": " + s);
        tableRowView.addToRow(pdfTextView);
      }
      tableView.addRow(tableRowView);
      tableView.addSeparatorRow(new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK));
    }
    pdfBody.addView(tableView);

    PDFLineSeparatorView lineSeparatorView3 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
    pdfBody.addView(lineSeparatorView3);

    PDFTextView pdfIconLicenseView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
    Spanned icon8Link = Html.fromHtml("");
    pdfIconLicenseView.getView().setText(icon8Link);
    pdfBody.addView(pdfIconLicenseView);

    return pdfBody;
  }


  @Override
  protected void onNextClicked(File savedPDFFile) {

    Uri pdfUri = Uri.fromFile(savedPDFFile);
    Intent intentPdfViewer = new Intent(ProductPDFActivity.this, PdfViewerActivity.class);
    intentPdfViewer.putExtra(PdfViewerActivity.PDF_FILE_URI, pdfUri);
    startActivity(intentPdfViewer);

  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
