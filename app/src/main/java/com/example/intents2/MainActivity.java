package com.example.intents2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.intents2.classes.ArrayStack;
import com.example.intents2.classes.SweetsAdapter;
import com.example.intents2.fragments.InputDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity implements InputDialog.InputDialogListener {

    ArrayStack<String> stack = new ArrayStack<>(5);
    SweetsAdapter adapter;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton push = findViewById(R.id.push);
        MaterialButton pop = findViewById(R.id.pop);
        MaterialButton top = findViewById(R.id.top);
        MaterialButton isEmpty = findViewById(R.id.isEmpty);
        MaterialButton size = findViewById(R.id.size);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stack.push("Ice pop");
        stack.push("Lotta");
        stack.push("Mr. Berry");
        top.setOnClickListener(view -> {
            String topElement = stack.top();
            if(topElement!=null){
                Snackbar snackbar = Snackbar.make(findViewById(R.id.main_view), "Top sweet: "+topElement, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.top_dialog, null);
                builder.setView(view1)
                        .setPositiveButton(R.string.ok, (dialogInterface, i) -> dialogInterface.dismiss())
                        .setTitle(R.string.top_errTitle);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        isEmpty.setOnClickListener(view -> {
            String text;
            if(stack.isEmpty()){
                text = "'True'";
            }
            else{
                text = "'False'";
            }
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main_view), text, Snackbar.LENGTH_LONG);
            snackbar.show();
        });
        size.setOnClickListener(view -> {
            int length = stack.size();
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main_view), "Size: "+ length, Snackbar.LENGTH_LONG);
            snackbar.show();
        });
        pop.setOnClickListener(view -> {
            Snackbar snackbar;
            if(!stack.isEmpty()){
                String popped = stack.top();
                stack.pop();
                adapter.notifyDataSetChanged();
                snackbar = Snackbar.make(findViewById(R.id.main_view), "Popped: "+popped, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.pop_dialog, null);
                builder.setTitle(R.string.pop_errTitle)
                        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        })
                        .setView(view1);
                AlertDialog dialog = builder.create();
                dialog.show();
            }


        });
        push.setOnClickListener(view -> onDialogOpen());
        RecyclerView recyclerView = findViewById(R.id.sweet_recyclerview);
        adapter = new SweetsAdapter(stack);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void onDialogOpen() {
        InputDialog dialog = new InputDialog();
        dialog.show(getSupportFragmentManager(), "sweetInput");
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void applyTexts(String sweetName) {
        if(!sweetName.equals("")){
            try {
                stack.push(sweetName);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Added: "+ sweetName, Toast.LENGTH_LONG).show();
            }
            catch(ArrayIndexOutOfBoundsException e){
                Toast.makeText(this, "Maximum capacity reached!", Toast.LENGTH_LONG).show();
            }
        }

        else{
            Toast.makeText(this, "Cannot add an empty entry", Toast.LENGTH_LONG).show();
        }
    }
}