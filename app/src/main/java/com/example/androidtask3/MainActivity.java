package com.example.androidtask3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditText 초기화
        edit1 = findViewById(R.id.Edit1);
        edit2 = findViewById(R.id.Edit2);

        // 연산 버튼 초기화 및 터치 이벤트 설정
        btnAdd = findViewById(R.id.BtnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSub = findViewById(R.id.BtnSub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMul = findViewById(R.id.BtnMul);
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDiv = findViewById(R.id.BtnDiv);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });

        // 숫자 버튼 초기화 및 터치 이벤트 설정
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            findViewById(getResources().getIdentifier("BtnNum" + i, "id", getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText focusedEditText = getCurrentFocus() == edit1 ? edit1 : edit2;
                    focusedEditText.append(String.valueOf(finalI));
                }
            });
        }

        // 결과를 표시할 TextView 초기화
        textResult = findViewById(R.id.TextResult);
    }

    // 연산을 수행하는 메서드
    private void calculate(char operator) {
        String num1 = edit1.getText().toString();
        String num2 = edit2.getText().toString();
        int operand1 = num1.isEmpty() ? 0 : Integer.parseInt(num1);
        int operand2 = num2.isEmpty() ? 0 : Integer.parseInt(num2);

        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                // 0으로 나누는 경우 처리
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    textResult.setText("0으로 나눌 수 없습니다.");
                    return;
                }
                break;
        }

        textResult.setText("계산결과: " + result);
    }
}
