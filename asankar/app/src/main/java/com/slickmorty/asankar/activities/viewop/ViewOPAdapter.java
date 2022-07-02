package com.slickmorty.asankar.activities.viewop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.slickmorty.asankar.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewOPAdapter extends RecyclerView.Adapter<ViewOPAdapter.ViewHolder> {

  private ArrayList<String> names;
  private OnClickListener onClickListener;

  public void setNames(ArrayList<String> names) {
    this.names = names;
  }

  public ViewOPAdapter(ArrayList<String> names, OnClickListener onClickListener) {
    this.names = names;
    this.onClickListener = onClickListener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false);
    return new ViewHolder(view, onClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    viewHolder.textTitle.setText(names.get(i));
  }

  @Override
  public int getItemCount() {
    return names.size();
  }

  //ViewHolder Class
  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView textTitle;
    ImageView imageView;
    OnClickListener onClickListener;

    public ViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
      super(itemView);
      textTitle = itemView.findViewById(R.id.textTitle);
      imageView = itemView.findViewById(R.id.img_op_pr);
      this.onClickListener = onClickListener;
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      onClickListener.onClick(getAdapterPosition());
    }
  }

  //OnClick interface
  public interface OnClickListener {
    void onClick(int position);
  }
}
