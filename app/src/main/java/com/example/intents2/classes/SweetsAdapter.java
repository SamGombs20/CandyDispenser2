package com.example.intents2.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intents2.R;

import java.util.ArrayList;
import java.util.Iterator;

public class SweetsAdapter extends RecyclerView.Adapter<SweetsAdapter.MyViewHolder> {
    private ArrayStack<String> arrayStack;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public MyViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
    public SweetsAdapter(ArrayStack<String> arrayStack){
        this.arrayStack = arrayStack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_main, viewGroup, false);
        return new MyViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CardView cardView = myViewHolder.cardView;
        TextView sweetText = (TextView) cardView.findViewById(R.id.sweet_text);
//        String[] sweets=arrayStack.toArray();
        ArrayList<String> sweets = new ArrayList<>();
        Iterator<String> sweetsIterator = arrayStack.iterator();
        while (sweetsIterator.hasNext()){
            sweets.add(sweetsIterator.next());
        }
        sweetText.setText(sweets.get(i));
    }

    @Override
    public int getItemCount() {
        return arrayStack.size();
    }
}
