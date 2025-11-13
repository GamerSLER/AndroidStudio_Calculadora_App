package com.gamersler.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculadora extends AppCompatActivity {

    TextView textoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textoTV = findViewById(R.id.TextoTV);
        Double resultado = 0.0;

        Button Boton_borrar = findViewById(R.id.B_borrar);
        Button Boton_borrar_ultimo = findViewById(R.id.B_borrar_ultimo);
        Button Boton_coma = findViewById(R.id.B_coma);

        List<Button> botonesNumericos = new ArrayList<>(Arrays.asList(
                findViewById(R.id.B_num0),
                findViewById(R.id.B_num1),
                findViewById(R.id.B_num2),
                findViewById(R.id.B_num3),
                findViewById(R.id.B_num4),
                findViewById(R.id.B_num5),
                findViewById(R.id.B_num6),
                findViewById(R.id.B_num7),
                findViewById(R.id.B_num8),
                findViewById(R.id.B_num9)
        ));

        List<Button> botonesOperaciones = new ArrayList<>(Arrays.asList(
                findViewById(R.id.B_porcentaje),
                findViewById(R.id.B_dividir),
                findViewById(R.id.B_multiplicar),
                findViewById(R.id.B_restar),
                findViewById(R.id.B_sumar),
                findViewById(R.id.B_igual)
        ));

        for (Button boton_numerico:botonesNumericos) {
            boton_numerico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String valor = boton_numerico.getText().toString();
                    mostrarEnPantalla(valor);
                }
            });
        }

        for (Button boton_operacion:botonesOperaciones) {
            boton_operacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String simbolo = boton_operacion.getText().toString();
                    mostrarEnPantalla(simbolo);
                }
            });
            }
        }
    public void mostrarEnPantalla(String valor) {
        String textoActual = textoTV.getText().toString();
        textoTV.setText(textoActual + valor);
        }
    }