package com.example.datetimechecker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                    Toast.makeText(MainActivity.this,"Input data for day is out of range!",Toast.LENGTH_LONG).show();
                }
                else if(checkDate(Cday,Cmonth,Cyear) == true){
                    Toast.makeText(MainActivity.this,"Correct day!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Invalid date!",Toast.LENGTH_SHORT).show();
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
}