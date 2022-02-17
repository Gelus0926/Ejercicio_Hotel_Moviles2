package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton rbcartagena,rbamazonas,rbcancun;
    TextView tvtransporte,tvguia,tvtotal,tvciudad;
    CheckBox cbguia,cbtransporte;
    EditText etcantidadpersonas;
    Button btncalcular, btnlimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        rbcartagena  = findViewById(R.id.idrbcartagena);
        rbamazonas = findViewById(R.id.idramazona);
        rbcancun = findViewById(R.id.idrcancun);
        tvguia = findViewById(R.id.tvguia);
        tvtransporte = findViewById(R.id.tvtransporte);
        tvtotal = findViewById(R.id.tvtotal);//
        tvciudad = findViewById(R.id.tvciudad);
        cbguia = findViewById(R.id.cbguia);
        cbtransporte = findViewById(R.id.cbtransporte);
        etcantidadpersonas = findViewById(R.id.etcantpersonas);
        btncalcular = findViewById(R.id.btncalcular);
        btnlimpiar = findViewById(R.id.btnlimpiar);

        }

        public void calcular_total(View view){
            String cantidad;
            cantidad = etcantidadpersonas.getText().toString();
            if(cantidad.isEmpty()){
                etcantidadpersonas.requestFocus();
                Toast.makeText( this, "La cantidad de personas es requerida",Toast.LENGTH_SHORT).show();
            }
            else{
                int cant,ciudad,guia,transporte,subtotal;
                float iva,total_viaje;
                cant=Integer.parseInt(cantidad);
                if (cant < 1){
                    Toast.makeText( this, "Cantidad de personas mayor de 0",Toast.LENGTH_SHORT).show();
                    etcantidadpersonas.requestFocus();
                }
                else{
                   if(rbcartagena.isChecked()){
                        tvciudad.setText("600000");
                        ciudad=600000;
                    }
                    else{
                        if(rbamazonas.isChecked()){
                            tvciudad.setText("2000000");
                            ciudad=2000000;
                        }
                        else{
                            tvciudad.setText("3200000");
                            ciudad=3200000;
                        }
                    }
                    if(cbguia.isChecked()){
                        tvguia.setText("120000");
                        guia=120000;
                    }
                    else{
                        tvguia.setText("0");
                        guia=0;
                    }
                    if(cbtransporte.isChecked()){
                        tvtransporte.setText("50000");
                        transporte=50000;
                    }
                    else{
                        tvtransporte.setText("0");
                        transporte=0;
                    }

                   subtotal=ciudad * cant;
                   iva=((float)(subtotal + guia + cant * transporte) * 19)/100;
                    total_viaje=subtotal * iva + guia + cant * transporte;
                    String valor=String.format("%.1f",total_viaje);
                   tvtotal.setText(valor);
                }

            }
    }

    public void Cancelar(View view){
        tvtotal.setText("0");
        tvguia.setText("0");
        tvtransporte.setText("0");
        tvciudad.setText("600000");
        rbcartagena.setChecked(true);
        cbtransporte.setChecked(false);
        cbguia.setChecked(false);
        etcantidadpersonas.setText("");
        etcantidadpersonas.requestFocus();
    }
}