package com.example.intents2.fragments;

import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.intents2.R;
import com.google.android.material.textfield.TextInputEditText;

public class InputDialog extends DialogFragment {
    private TextInputEditText sweetText;
    private InputDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog, null);
        builder.setView(view)
                .setTitle(R.string.dialogTitle)
                .setNegativeButton(R.string.no, (dialogInterface, i) -> {

                })
                .setPositiveButton(R.string.add, (dialogInterface, i) ->{
                    String sweetName = sweetText.getText().toString();
                    listener.applyTexts(sweetName);
                });
        sweetText = view.findViewById(R.id.sweetInput);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (InputDialogListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(e.getMessage());
        }
    }

    public interface InputDialogListener{
        void applyTexts(String sweetName);
    }
}
