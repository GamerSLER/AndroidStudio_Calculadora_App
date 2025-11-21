package com.gamersler.calculadora;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculadora extends AppCompatActivity {

    TextView textoTV;
    String textoActual = "";
    ImageView patricioIV;

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
        patricioIV = findViewById(R.id.NaN_error);
        patricioIV.setVisibility(patricioIV.GONE);

        textoTV = findViewById(R.id.TextoTV);

        Button Boton_borrar = findViewById(R.id.B_borrar);
        Button Boton_borrar_ultimo = findViewById(R.id.B_borrar_ultimo);
        Boton_borrar.setOnClickListener(v -> borrarTodo());
        Boton_borrar_ultimo.setOnClickListener(v -> borrarUltimo());

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
                findViewById(R.id.B_igual),
                findViewById(R.id.B_coma)
        ));

        for (Button boton_numerico: botonesNumericos) {
            boton_numerico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String valor = boton_numerico.getText().toString();
                    mostrarEnPantalla(valor);
                }
            });
        }

        for (Button boton_operacion: botonesOperaciones) {
            boton_operacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String simbolo = boton_operacion.getText().toString();

                    mostrarEnPantalla(simbolo);
                }
            });
        }
    }

    public void borrarUltimo() {
        if (textoActual.length() > 0) {
            textoActual = textoActual.substring(0, textoActual.length() - 1);
            textoTV.setText(textoActual);
        }
    }
    public void borrarTodo() {
        textoActual = "";
        textoTV.setText("");
    }
    public void mostrarEnPantalla(String valor) {
        boolean valorEsSimbolo = valor.equals("+") || valor.equals("-") || valor.equals("÷") || valor.equals("×") || valor.equals("%") || valor.equals(".");
        boolean valorEsIgual = valor.equals("=");
        boolean terminaConSimbolo = textoActual.endsWith("+") || textoActual.endsWith("-") || textoActual.endsWith("÷") || textoActual.endsWith("×") || textoActual.endsWith("%") || textoActual.equals(".");
        if (valorEsIgual){
            String expr = textoActual.replace("%", "/100*").replace("×", "*").replace("÷", "/");
            Expression expression = new Expression(expr);
            double resultado = expression.calculate();
            textoActual = String.valueOf(resultado);
            textoTV.setText(textoActual);
            if (textoTV.getText().toString() == "NaN"){
                patricioIV.setVisibility(patricioIV.VISIBLE);
                Glide.with(this).asGif().load(R.drawable.patricio).into(patricioIV);
            } else {
                patricioIV.setVisibility(patricioIV.GONE);
                Glide.with(this).clear(patricioIV);
            }
            return;
        }
        if (valorEsSimbolo && terminaConSimbolo) {
            Toast.makeText(this, "Pendejo men", Toast.LENGTH_SHORT).show();
            return;
        }
        textoActual += valor;
        textoTV.setText(textoActual);
    }
}
