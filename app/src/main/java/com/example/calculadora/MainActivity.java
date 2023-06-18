package com.exemple.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.calculadora.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;
    private String input, output;
    private Button button0, button1, button2, button3, button4, button5, button6, button7,
            button8, button9, buttonSom, buttonMult, buttonSub, buttonDiv, buttonRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);

        setupButtons();
    }

    private void setupButtons() {
        int[] buttonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9, R.id.som_btn, R.id.mul_btn, R.id.div_btn, R.id.sub_btn,
                R.id.res_btn};

        for (int buttonId : buttonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(this::onButtonClicked);
        }
    }

    public void onButtonClicked(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();

        switch (data) {
            case "*":
                solve();
                input += "*";
                break;
            case "=":
                solve();
                break;
            default:
                if (input == null) {
                    input = "";
                }
                solve();
                input += data;
        }
        inputText.setText(input);
    }

    private void solve() {
        String[] operators = {"\\+", "\\*", "\\/", "\\^", "\\-"};
        String[] operatorSymbols = {"+", "*", "/", "-"};

        for (int i = 0; i < operators.length; i++) {
            if (input.split(operators[i]).length == 2) {
                String numbers[] = input.split(operators[i]);
                try {
                    double d = 0;

                    switch (operatorSymbols[i]) {
                        case "+":
                            d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                            break;
                        case "*":
                            d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                            break;
                        case "/":
                            d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                            break;
                        case "-":
                            d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                            break;
                    }

                    output = Double.toString(d);
                    outputText.setText(output);
                    input = Double.toString(d);
                } catch (Exception e) {
                    outputText.setText(e.getMessage().toString());
                }
                break;
            }
        }
    }
}
