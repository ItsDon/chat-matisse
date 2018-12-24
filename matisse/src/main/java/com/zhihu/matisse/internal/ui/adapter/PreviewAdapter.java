package com.zhihu.matisse.internal.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhihu.matisse.R;
import com.zhihu.matisse.engine.ImageEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.utils.BitmapUtil;

import java.util.List;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.ViewHolder> {


    private List<Item>  datas;
    private OnItemClickListener onItemClickListener;
    private Item selectedPreviewItem;
    private Context mContext;

    public void setItems( List<Item>  uriList){
        this.datas = uriList;
    }

    public List<Item> getItems(){
        return datas;
    }

    public void setSelectedPreviewItem(Item item){
        this.selectedPreviewItem = item;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(selectedPreviewItem != null){
            holder.itemView.setSelected(datas.get(position).id == selectedPreviewItem.id);
        }
        holder.imageView.setImageURI(datas.get(position).uri);
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(onItemClickListener != null){
                 onItemClickListener.onItemClick(datas.get(position));
             }
         }
     });

    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_preview);
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(Item item);
    }
}
