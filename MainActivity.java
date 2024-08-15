package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.Expression;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    double firstNum;
    String operation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button ac = findViewById(R.id.ac);
        Button del = findViewById(R.id.del);
        Button div = findViewById(R.id.div);
        Button times = findViewById(R.id.times);
        Button min = findViewById(R.id.min);
        Button plus = findViewById(R.id.plus);
        Button equal = findViewById(R.id.equal);
        Button point = findViewById(R.id.point);

        TextView screen = findViewById(R.id.screen);

        ac.setOnClickListener(view -> {
            firstNum = 0;
            screen.setText("");
        });
        off.setOnClickListener(view -> screen.setVisibility(TextView.GONE));
        on.setOnClickListener(view -> {
            screen.setVisibility(TextView.VISIBLE);
            screen.setText("");
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);


        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (!screen.getText().toString().isEmpty()) {
                    screen.setText(screen.getText().toString() + b.getText().toString());
                } else {
                    screen.setText(b.getText().toString());
                }
            });

        }
        ArrayList<Character> opers = new ArrayList<>();
        opers.add('-');
        opers.add('*');
        opers.add('/');
        opers.add('+');


        ArrayList<Button> opers2 = new ArrayList<>();
        opers2.add(div);
        opers2.add(times);
        opers2.add(plus);
        opers2.add(min);
        for (Button b : opers2) {
            b.setOnClickListener(view -> {

                String num = screen.getText().toString();
                String operation = b.getText().toString();
                if(operation.equals("X")) operation = "*";
                if(!num.isEmpty()) {
                    char last = num.charAt(num.length() - 1);
                    if (last != '.') {
                        screen.setText(screen.getText().toString() + operation);
                    } else {
                        //ignore- invalid input
                    }
                }
            });

        }

        del.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if(num.length()>1){

                screen.setText(num.substring(0, num.length()-1));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("");

            }
        });

        point.setOnClickListener(view -> {
            String x = screen.getText().toString();
            if (!x.isEmpty()){
                char last = x.charAt(x.length()-1);
                if (last != '.' && !opers.contains(last)){
                    screen.setText(screen.getText().toString() + ".");
                }
            }
        });

        equal.setOnClickListener(view -> {

            String expression = screen.getText().toString();
            Expression e = new Expression(expression);
            double result = e.calculate();

            if(result == Math.floor(result)){
                int finalResult = (int) result;
                screen.setText(String.valueOf(finalResult));
            }
            else{
                screen.setText(String.valueOf(result));
            }

        });
    }
}
