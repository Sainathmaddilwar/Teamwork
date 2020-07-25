package com.example.teamwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamwork.R;
import com.example.teamwork.models.retro_Object;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewImage_Adapter extends RecyclerView.Adapter<ViewImage_Adapter.ViewHolder> {
Context mcontext;
ArrayList<retro_Object> arrayList;

    public ViewImage_Adapter(Context mcontext, ArrayList<retro_Object> arrayList) {
        this.mcontext = mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int i=arrayList.get(position).getId();
        String image=arrayList.get(position).getUrl();
        Toast.makeText(mcontext,image,Toast.LENGTH_SHORT).show();
        Picasso.get().load(image).into(holder.Rimage);

        String s=Integer.toString(i);
        holder.Id.setText(s);
        holder.Title.setText(arrayList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Id,Title;
        ImageView Rimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mcontext=itemView.getContext();
            Id=(TextView)itemView.findViewById(R.id.Rid);
            Title=(TextView)itemView.findViewById(R.id.Rtitle);
            Rimage=(ImageView)itemView.findViewById(R.id.Rimage);
        }
    }


}
