package com.example.mobile_08_ingressos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_venda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv01;
        tv01 = findViewById(R.id.tv01);
        TextView tv02;
        tv02 = findViewById(R.id.tv02);
        Button BTVoltar;
        BTVoltar = findViewById(R.id.btVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String id  = bundle.getString("id");
        float valFinal;
        if(id.equals("error")){
            id = "Não foi possível finalizar a compra do seu ingresso";
            tv01.setText(id);
            tv01.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        }else{
            valFinal = bundle.getFloat("valor");
            tv01.setText(id);
            tv01.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            BigDecimal valor = BigDecimal.valueOf(valFinal).setScale(2, RoundingMode.HALF_UP);
            tv02.setText("Valor Final = R$"+valor);
            tv02.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

            BTVoltar.setOnClickListener(op -> voltar());

        }
    }

    private void voltar() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
}