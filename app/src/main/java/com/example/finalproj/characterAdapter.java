package com.example.finalproj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class characterAdapter extends RecyclerView.Adapter<characterAdapter.MyViewHolder> {



        public List<character> characterList;

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewRecyclerName;
            public MyViewHolder(View view) {
                super(view);
                textViewRecyclerName =  view.findViewById(R.id.textViewRecyclerName);

            }
        }

        public characterAdapter(List<character> characterList) {
            this.characterList = characterList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout. activity_name_recyclerview_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            character character = characterList.get(position);
            String characterName = character.getName();

            holder.textViewRecyclerName.setText(characterName);

        }

        @Override
        public int getItemCount() {
            return characterList.size();
        }

        public void setData(List<character> characterList) {
            this.characterList = characterList;
            notifyDataSetChanged();
        }

    }

