package com.avci.berkeravciproject1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    ArrayList<Product> recyclerList;

    public MyRecyclerViewAdapter(Context context, ArrayList recyclerList){
        this.context = context;
        this.recyclerList = recyclerList;
    }

    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.item_layout, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final int position = i;
        final Product productItem = (Product) recyclerList.get(i);

        myRecyclerViewItemHolder.proName.setText(productItem.getName());




        myRecyclerViewItemHolder.proName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                Commons.selectedProIndex = position;
                Commons.curentSelectedPro = productItem;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerList.size();
    }

    class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView proName;

        public MyRecyclerViewItemHolder(View itemView) {
            super(itemView);
            proName = itemView.findViewById(R.id.tvProTitle);
            ;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Commons.selectedProIndex = getLayoutPosition();
                    Commons.curentSelectedPro = (Product) recyclerList.get(getLayoutPosition());
                    Toast.makeText(context, Commons.curentSelectedPro.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }

}
