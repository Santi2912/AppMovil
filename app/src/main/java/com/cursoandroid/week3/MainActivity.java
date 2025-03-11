package com.cursoandroid.week3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edt_num_1, edt_num_2;
    TextView txt_resultado;
    Spinner spinner_operador;
    Button btn_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_num_1 = findViewById(R.id.edit_num1);
        edt_num_2 = findViewById(R.id.edit_num2);
        txt_resultado = findViewById(R.id.txt_resultado);
        spinner_operador = findViewById(R.id.spinner_operador);
        btn_calcular = findViewById(R.id.btn_calcular);

        // Configurar Spinner
        String[] operadores = {"+", "-", "*", "/"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, operadores);
        spinner_operador.setAdapter(adapter);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesar();
            }
        });
    }

    private void procesar() {
        String num1 = edt_num_1.getText().toString();
        String num2 = edt_num_2.getText().toString();

        if (num1.isEmpty() || num2.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, llenar todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        double n1 = Double.parseDouble(num1);
        double n2 = Double.parseDouble(num2);
        String operador = spinner_operador.getSelectedItem().toString();
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = n1 + n2;
                break;
            case "-":
                resultado = n1 - n2;
                break;
            case "*":
                resultado = n1 * n2;
                break;
            case "/":
                if (n2 != 0) {
                    resultado = n1 / n2;
                } else {
                    Toast.makeText(this, "No se puede dividir por 0", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
        }

        txt_resultado.setText(String.valueOf(resultado));
    }
}
