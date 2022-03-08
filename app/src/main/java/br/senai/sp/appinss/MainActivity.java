package br.senai.sp.appinss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText salarioB, dependentes, alimentacao, refeicao, transporte;
    private Button calcular, limpar;
    private TextView resultado,txtdesconto;
    private RadioButton radio1, radio2, radio3, radio4,radAli,radTrans,radRef,radTrans_n,radAlim_n,radRef_n;
    private RadioGroup radio_group,radio_group_alim,radio_group_ref,radio_group_trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salarioB = findViewById(R.id.salario_b);
        dependentes = findViewById(R.id.dependentes);
        calcular = findViewById(R.id.bt_calcular);
        limpar = findViewById(R.id.bt_limpar);
        radio1 = findViewById(R.id.plano1);
        radio2 = findViewById(R.id.plano2);
        radio3 = findViewById(R.id.plano3);
        radio4 = findViewById(R.id.plano4);
        radAli = findViewById(R.id.radio_alim);
        radRef = findViewById(R.id.radio_ref);
        resultado = findViewById(R.id.text_result);
        txtdesconto = findViewById(R.id.text_desconto);
        radTrans = findViewById(R.id.radio_trans);
        radTrans_n = findViewById(R.id.radio_tansn);
        radAlim_n = findViewById(R.id.radio_alime);
        radRef_n = findViewById(R.id.radio_refn);
        radio_group = findViewById(R.id.radio_group);
        radio_group_alim = findViewById(R.id.radio_group_alim);
        radio_group_ref = findViewById(R.id.radio_group_ref);
        radio_group_trans = findViewById(R.id.radio_group_trans);




        calcular.setOnClickListener(v -> {
            double salarBruto = 0, salarLiquido, valeTrans = 0, valeRef = 0, valeAlimen = 0, impost, inss = 0, desconto;
            int dependente = 0;
            double valorPlano=0;
            if(salarioB.getText().toString().isEmpty()){
                salarioB.setError("Informe um Salário");
                salarioB.requestFocus();
            }else {
                salarBruto=Double.parseDouble(salarioB.getText().toString());
                dependente= Integer.parseInt(dependentes.getText().toString());

                if (radio1.isChecked()) {
                    if (salarBruto <= 3000) {
                        valorPlano = 60;
                    } else {
                        valorPlano = 80;
                    }
                }
                if (radio2.isChecked()) {
                    if (salarBruto <= 3000) {
                        valorPlano = 80;
                    } else {
                        valorPlano = 110;
                    }
                }
                if (radio3.isChecked()) {
                    if (salarBruto <= 3000) {
                        valorPlano = 95;
                    } else {
                        valorPlano = 135;
                    }
                }
                if (radio4.isChecked()) {
                    if (salarBruto <= 3000) {
                        valorPlano = 130;
                    } else {
                        valorPlano = 180;
                    }
                }


                //CALCULO INSS
                if (salarBruto <= 1212.00) {
                    inss = salarBruto * 0.075 - 0;

                } else if (salarBruto <= 2427.35) {
                    inss = salarBruto * 0.09 - 18.18;

                } else if (salarBruto <= 3641.03) {
                    inss = salarBruto * 0.12 - 91.00;

                } else if (salarBruto <= 7087.22) {
                    inss = salarBruto * 0.14 - 163.82;
                } else {
                    inss = 828.39;
                }

                //Vale transporte

                if (radTrans.isChecked()) {
                    valeTrans = salarBruto * 0.06;
                } else {

                }

                if (radAli.isChecked()) {
                    if (salarBruto <= 3000) {
                        valeAlimen = 15;
                    } else if (salarBruto < 5000) {
                        valeAlimen = 25;
                    } else {
                        valeAlimen = 35;
                    }
                }

                if (radRef.isChecked()) {
                    if (salarBruto <= 3000) {
                        valeRef = 2.6 * 22;
                    } else if (salarBruto <= 5000) {
                        valeRef = 3.65 * 22;
                    } else {
                        valeRef = 6.5 * 22;
                    }
                }
                //base de Calculo
                impost = salarBruto - inss - (189.59 * dependente);

                if (impost <= 1903.98) {
                    impost = 0;
                } else if (impost <= 2826.66) {

                    impost = impost * 0.075 - 142.80;

                } else if (impost <= 3751.05) {

                    impost = impost * 0.15 - 354.80;

                } else if (impost <= 4664.68) {

                    impost = impost * 0.225 - 636.13;

                } else {

                    impost = impost * 0.275 - 869.36;

                }

                //calculo salario liquido
                salarLiquido = salarBruto - inss - valeTrans - valeRef - valeAlimen - impost - valorPlano;
                resultado.setText(getString(R.string.result, salarLiquido));
                desconto = salarLiquido * 100 / salarBruto;
                desconto = 100 - desconto;
                txtdesconto.setText(getString(R.string.desconto, desconto));

            }

        });
        limpar.setOnClickListener(view -> {
           salarioB.getText().clear();
           dependentes.getText().clear();
           radio_group.clearCheck();
           radio_group_alim.clearCheck();
           radio_group_ref.clearCheck();
           radio_group_trans.clearCheck();
           resultado.setText("");
           txtdesconto.setText("");
           salarioB.requestFocus();


        });
    }
}