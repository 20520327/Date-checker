package com.example.datetimechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnClear, btnCheck;
    EditText etDayCheck, etMonthCheck, etYearCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClear = findViewById(R.id.btnClearInfo);
        btnCheck = findViewById(R.id.btnCheckDate);
        etDayCheck = findViewById(R.id.etDay);
        etMonthCheck = findViewById(R.id.etMonth);
        etYearCheck = findViewById(R.id.etYear);



        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDayCheck.setText("");
                etMonthCheck.setText("");
                etYearCheck.setText("");
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Cday = Integer.parseInt(etDayCheck.getText().toString());
                int Cmonth = Integer.parseInt(etMonthCheck.getText().toString());
                int Cyear = Integer.parseInt(etYearCheck.getText().toString());

                if(Cday<1 || Cday >31){
                    showWrongDialog("Input day is out of range!");
                }
                else if(checkDate(Cday,Cmonth,Cyear) == true){
                    showCorrectDialog();
                }
                else{
                    showWrongDialog("Invalid date!");
                }
            }
        });
    }

    public int CheckDaysInMonth(int year, int month){
        if(month>0 && month<=12)
        switch (month){
            case 2:
                if(year%400==0)
                    return 29;
                else if(year%100==0)
                    return 28;
                else if(year%4==0)
                    return 29;
                else
                    return 28;

            case 4:
                return 30;

            case 6:
                return 30;

            case 9:
                return 30;

            case 11:
                return 30;

            default:
                return 31;
        }
        else
            return 0;
    }

    public Boolean checkDate(int day, int month, int year){
        if(month>=1 && month<=12)
        {
            if(day>=1)
            {
                if(day<=CheckDaysInMonth(year, month)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    void showCorrectDialog(){
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.setCancelable(true);
        dialog.setContentView(R.layout.correct_dialog);

        Button btnDone = dialog.findViewById(R.id.btnCDialogDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    }

    void showWrongDialog(String message){
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.setCancelable(true);
        dialog.setContentView(R.layout.wrong_dialog);

        TextView tvInfo = dialog.findViewById(R.id.tvWDialogInfo);
        Button btnDone = dialog.findViewById(R.id.btnWDialogDone);

        tvInfo.setText(message);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    }
}