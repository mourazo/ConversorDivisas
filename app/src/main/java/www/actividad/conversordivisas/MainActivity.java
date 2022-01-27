package www.actividad.conversordivisas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button euros; //declaramos el boton euros
    Button monedas; //declaramos el boton monedas
    private HashMap<String, Double> tiposDeCambio = new HashMap<String, Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        euros = (Button) findViewById(R.id.botonEuros); //introducimos el botonEuros del layout en la variable euros
        monedas = (Button) findViewById((R.id.botonMonedas)); //introducimos el botonMonedas en la variable monedas


        //tomamos el objeto euros(boton) como párametro para crear nueva vista al clickarlo
        euros.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { //declaramos la función onClick

                //creamos un intent el cual nos nos redirige de la MainActivity a eurosMoneda
                Intent euros = new Intent(MainActivity.this, monedaEuros.class);

                startActivity(euros); //le indicamos que inicie el intent y nos redirija a eurosMoneda

            }
        });

        //tomamos el objeto monedas(boton) como párametro para crear nueva vista al clickarlo
        monedas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {  //declaramos la función onClick

                //creamos un intent el cual nos nos redirige de la MainActivity a monedaEuros
                Intent monedas = new Intent(MainActivity.this, eurosMoneda.class);
                startActivity(monedas);//le indicamos que inicie el intent y nos redirija a eurosMoneda
            }
        });

    }



}