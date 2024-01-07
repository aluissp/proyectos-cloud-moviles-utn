package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtId, txtNombre,txtCapital, txtMoneda, txtPoblacion, txtIdioma;
    Paises paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtId=findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtCapital = findViewById(R.id.txtCapital);
        txtMoneda = findViewById(R.id.txtMoneda);
        txtPoblacion = findViewById(R.id.txtPoblacion);
        txtIdioma = findViewById(R.id.txtIdioma);
        paises = new Paises(this,"geografia.db",1);
    }

    public void cmdCreate_onClick(View v){
        Pais p = paises.Create(Integer.parseInt(txtId.getText().toString()),txtNombre.getText().toString(),txtCapital.getText().toString(),txtMoneda.getText().toString(),Integer.parseInt(txtPoblacion.getText().toString()),txtIdioma.getText().toString());
        if (p!= null){
            Toast.makeText(this,"DATOS INSERTADOS", Toast.LENGTH_SHORT);
        }
    }

    public void cmdReadById_onClick(View v){
        Pais p = paises.Read_ById(Integer.parseInt(txtId.getText().toString()));
        if (p != null){
            txtIdioma.setText(p.Idioma);
            txtPoblacion.setText(p.Poblacion);
            txtMoneda.setText(p.Moneda);
            txtNombre.setText(p.Nombre);
            txtCapital.setText(p.Capital);
            Toast.makeText(this,"ELEMENTO ENCONTRADO", Toast.LENGTH_SHORT);
        } else{
            Toast.makeText(this,"ELEMENTO NO ENCONTRADO", Toast.LENGTH_SHORT);
        }
    }

    public void cmdUpdate_onClick(View v){
        paises.Update(Integer.parseInt(txtId.getText().toString()),txtNombre.getText().toString(),txtCapital.getText().toString(),txtMoneda.getText().toString(),Integer.parseInt(txtPoblacion.getText().toString()),txtIdioma.getText().toString());
        Toast.makeText(this,"DATOS ACTUALIZADOS", Toast.LENGTH_SHORT);
    }

    public void cmdDelete_onClick(View v){
        paises.Delete(Integer.parseInt(txtId.getText().toString()));
        txtIdioma.setText("");
        txtPoblacion.setText("p.Poblacion");
        txtMoneda.setText("");
        txtNombre.setText("");
        txtCapital.setText("");
        txtId.setText("");
        Toast.makeText(this,"DATO ELIMINADO", Toast.LENGTH_SHORT);

    }
}