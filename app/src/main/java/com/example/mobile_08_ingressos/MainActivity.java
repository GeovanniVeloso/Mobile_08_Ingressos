package com.example.mobile_08_ingressos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobile_08_ingressos.model.Ingresso;

public class MainActivity extends AppCompatActivity {

    private RadioGroup RG01;
    private RadioButton RB01;
    private RadioButton RB02;
    private RadioButton RB03;
    private RadioButton RBVIP;
    private Button BTCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BTCalc = findViewById(R.id.btCalc);
        RB01 = findViewById(R.id.rb01);
        RB01.setChecked(true);
        RB02 = findViewById(R.id.rb02);
        RB03 = findViewById(R.id.rb03);
        RBVIP = findViewById(R.id.rbVIP);

        BTCalc.setOnClickListener(op -> calc());

    }

    private void calc() {

        String error = "";
        Ingresso ingresso = new Ingresso();
        float valFinal = 0f;

        if(RB01.isChecked()){
            ingresso.setId("1");
            ingresso.setValor(245f);
            try {
                valFinal = ingresso.calcValor(10);
            } catch (Exception e) {
                error = e.getMessage();
            }
        }else if (RB02.isChecked()){
            ingresso.setId("2");
            ingresso.setValor(200f);
            try {
                valFinal = ingresso.calcValor(15);
            } catch (Exception e) {
                error = e.getMessage();
            }
        }else{
            ingresso.setId("3");
            ingresso.setValor(450f);
            try {
                valFinal = ingresso.calcValor(12);
            } catch (Exception e) {
                error = e.getMessage();
            }
        }

        if(RBVIP.isChecked()){
            valFinal = (float) (valFinal * 1.18);
        }

        if(error.isEmpty()){
            Bundle bundle = new Bundle();
            bundle.putString("id",ingresso.getId());
            bundle.putFloat("valor",valFinal);

            troca(bundle);
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("id",error);

            troca(bundle);
        }

    }

    private void troca(Bundle bundle) {

        Intent i = new Intent(this, VendaActivity.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();

    }
}