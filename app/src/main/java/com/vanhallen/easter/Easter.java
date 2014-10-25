package com.vanhallen.easter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Easter extends Activity {

    private TextView textVvod;
    private TextView textVivod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter);

        textVvod = (TextView)findViewById(R.id.vvod);
        textVivod = (TextView)findViewById(R.id.Vivod);

    }

    public void onVivod(View w) {
        int res = 0;
        int N = 0;


        String S = textVvod.getText().toString();
        if (S == null) Toast.makeText(this, "Введите год", Toast.LENGTH_SHORT).show();
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textVvod.getWindowToken(), 0);
        try {
            int Year = Integer.parseInt(S);
            if (Year <= 0) Toast.makeText(this, "Введите год", Toast.LENGTH_SHORT).show();
            else {
                int a = Year % 19;
                int b = Year % 4;
                int c = Year % 7;
                int d = (19 * a + 15) % 30;
                int e = (2 * b + 4 * c + 6 * d + 6) % 7;
                int Sum = d + e;

                if (Year - 1900 >= 0) N = 13;
                else N = 12;
                String T = " марта";
                String G = " апреля";
                String H = " мая";

                String m = null;
                if (Sum <= 9) {
                    res = 22 + Sum;
                    m = T;
                }
                if (Sum > 9) {
                    res = Sum - 9;
                    m = G;
                }

                res += N;
                if (res > 31 && m.equals(T)) {
                    m = G;
                    res -= 31;
                } else if (res > 30 && m.equals(G)) {
                    m = H;
                    res -= 30;
                }
                StringBuilder sb = new StringBuilder();
                Date New_date = new Date();
                New_date.setYear(Year);
                Calendar calendar = new GregorianCalendar();
                int t = calendar.getTime().getYear() + 1900;
                String B = "";
                if (New_date.getYear() > t) B = "будет праздноваться ";
                if (New_date.getYear() < t) B = "праздновалась ";
                if (New_date.getYear() == t) B = "";

                sb.append("В ");
                sb.append(Year);
                sb.append(" году Пасха ");
                sb.append(B);
                sb.append(res);
                sb.append(m);
                sb.append(".");
                textVivod.setText(sb.toString());


            }
        }catch(NumberFormatException e){
            Toast.makeText(this, "Год введен не верно!", Toast.LENGTH_SHORT).show();
        }

    }

}
