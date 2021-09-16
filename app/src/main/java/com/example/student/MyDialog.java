package com.example.student;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class MyDialog extends DialogFragment {
    public static final String CLASS_ADD_DIALOG = "addClass";
    public static final String STUDENT_ADD_DIALOG = "addStudent";

    private onClickListener listener;

    public interface onClickListener {
        void onClick(String text1, String text2);
    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if (getTag().equals(CLASS_ADD_DIALOG)) dialog = getAddClassDialog();
        if (getTag().equals(STUDENT_ADD_DIALOG)) dialog = getAddStudentDialog();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    private Dialog getAddStudentDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Student");


        EditText roll_edt = view.findViewById(R.id.edt01);
        EditText name_edt = view.findViewById(R.id.edt02);


        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);


        cancel.setOnClickListener(view1 -> dismiss());
        add.setOnClickListener(view1 -> {
            String roll = roll_edt.getText().toString();
            String name = name_edt.getText().toString();
            roll_edt.setText(String.valueOf(Integer.parseInt(roll)+1));
            name_edt.setText("");
            listener.onClick(roll, name);

        });

        return builder.create();

    }

    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Class");


        EditText class_edt = view.findViewById(R.id.edt01);
        EditText subject_edt = view.findViewById(R.id.edt02);


        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);


        cancel.setOnClickListener(view1 -> dismiss());
        add.setOnClickListener(view1 -> {
            String className = class_edt.getText().toString();
            String SubName = subject_edt.getText().toString();
            listener.onClick(className, SubName);
            dismiss();

        });

        return builder.create();
    }
}
