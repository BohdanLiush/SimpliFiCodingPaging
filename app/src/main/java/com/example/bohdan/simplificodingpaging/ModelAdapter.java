package com.example.bohdan.simplificodingpaging;

import android.arch.paging.AsyncPagedListDiffer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ModelAdapter extends PagedListAdapter<Item, ModelAdapter.ModelViewHolder> {

    /*private final AsyncPagedListDiffer<StackApiResponse> mDiffer
            = new AsyncPagedListDiffer(this, DIFF_CALLBACK);*/

    /*@Override
    public void submitList(PagedList<StackApiResponse> pagedList) {
        mDiffer.submitList(pagedList);
    }*/

   /* @Override
    public int getItemCount() {
        if (mDiffer == null)
            return 0;
        return mDiffer.getItemCount();
    }*/

    private Context mCtx;

    public ModelAdapter(Context  mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

   /* @Override
    public int getItemCount() {
        *//*if (model == null)
            return 0;*//*
        return model.size();
    }*/

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.custom,parent,false);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {

        //StackApiResponse  model_ = model.get(position);
       /* if(mDiffer!=null){
            holder.textView.setText(mDiffer.getItem(position).getDescription());
//            holder.textViewTwo.setText(mDiffer.getItem(position).getId());
            Picasso.get().load(mDiffer.getItem(position).getIcon()).into(holder.imageView);
        }*/
         Item model = getItem(position);

        if (model!=null){
            holder.textView.setText(model.getOwner().getDisplayName());
            Picasso.get().load(model.getOwner().getProfileImage()).into(holder.imageView);

            //holder.textView.setText(model.get(position).getOwner().getDisplayName());
           // holder.textViewTwo.setText(model.get(position).getDescription());
            //Picasso.get().load(model.get(position).getOwner().getProfileImage()).into(holder.imageView);
        }
        else {
            holder.imageView.invalidate();
            holder.textView.invalidate();
        }
    }


    public class ModelViewHolder extends RecyclerView.ViewHolder{

        TextView textView, textViewTwo;
        ImageView imageView;


        public ModelViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_id);
            textViewTwo = itemView.findViewById(R.id.textView2_description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


    public static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.getQuestionId()==newItem.getQuestionId();
                    //return true;
                }

                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return oldItem.equals(newItem);
                    // return true;
                }
            };
}
