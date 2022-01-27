package www.actividad.conversordivisas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class eurosMoneda extends AppCompatActivity {
    private EditText cantidad;
    private Spinner monedaCamb;
    private String[] moneda = {"USD - DÓLARES AMERICANOS","GBP - LIBRAS ESTERLINAS","SEK - CORONAS SUECAS","CAD - DÓLARES CANADIENSES","HKD - DÓLARES DE HONG KONG",
            "ISK - CORONAS ISLANDESAS","PHP - PESOS FILIPINO","DKK - CORONAS DANESAS","HUF - FORINTS HÚNGAROS","CZK - CORONAS CHEKAS","AUD - DÓLARES AUSTRALIANOS",
            "RON - LEUS RUMANOS","IDR - RUPIAS INDONESAS","INR - RUPIAS INDIAS","BRL - REALES BRASILEÑOS","RUB - RUBLOS RUSOS","HRK - KUNAS CROATAS",
            "JPY - YENES JAPONESES","THB - BAHTS TAILANDESES","CHF - FRANCOS SUIZOS","SGD - DÓLARES SINGAPUR","PLN - ZLOTYS POLACOS","BGN - LEVS BÚLGAROS",
            "TRY - LIRAS TURCAS","CNY - RENMINBINS CHINOS","NOK - CORONAS NORUEGAS","NZD - DÓLARES NEOZELANDES","ZAR - RANDS SUDAFRICANOS","MXN - PESOS MEXICANOS",
            "ILS - SÉQUELS ISRAELIS","KRW - WONS SURCOREANOS","MYR - RINGGITS MALASIOS"};
    private Map<String, Double> tiposDeCambio = new HashMap<String, Double>();
    private TextView t_resultado;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euros_moneda);
         new MiTarea().execute("https://api.exchangeratesapi.io/latest");

        cantidad = findViewById(R.id.t_cantidad);
        monedaCamb = findViewById((R.id.sp_monOr));
        t_resultado = findViewById(R.id.t_resultado);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, moneda);
        monedaCamb.setAdapter(adaptador);
    }

    private class MiTarea extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... servidor) {
            // proceso
            JSONObject resultado = new JSONObject();
            HttpURLConnection conexion = null;
            try {
                // conexión
                URL u = new URL(servidor[0]);
                conexion = (HttpURLConnection) u.openConnection();
                conexion.setRequestMethod("GET");
                conexion.connect();
                Log.e("HECHO", "CONECTADO AL SERVIDOR");
                // convertimos el stream de bytes que llegan desde el servidor
                InputStream stream = conexion.getInputStream(); // descarga fichero JSON
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                resultado = new JSONObject(buffer.toString());

            } catch (Exception e) {
                Log.e("ERROR", "No me he conectado");
                Log.e("ERROR", e.getMessage());
            }
            // algo devolverá: el JSON descargado
            return resultado;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            // asignar del objeto JSON a tiposDeCambio
            try {

                String usd = json.getJSONObject("rates").getString("USD");
                String gbp = json.getJSONObject("rates").getString("GBP");
                String sek = json.getJSONObject("rates").getString("SEK");
                String cad = json.getJSONObject("rates").getString("CAD");
                String hkd = json.getJSONObject("rates").getString("HKD");
                String isk = json.getJSONObject("rates").getString("ISK");
                String php = json.getJSONObject("rates").getString("PHP");
                String dkk = json.getJSONObject("rates").getString("DKK");
                String huf = json.getJSONObject("rates").getString("HUF");
                String czk = json.getJSONObject("rates").getString("CZK");
                String aud = json.getJSONObject("rates").getString("AUD");
                String ron = json.getJSONObject("rates").getString("RON");
                String idr = json.getJSONObject("rates").getString("IDR");
                String inr = json.getJSONObject("rates").getString("INR");
                String brl = json.getJSONObject("rates").getString("BRL");
                String rub = json.getJSONObject("rates").getString("RUB");
                String hrk = json.getJSONObject("rates").getString("HRK");
                String jpy = json.getJSONObject("rates").getString("JPY");
                String thb = json.getJSONObject("rates").getString("THB");
                String chf = json.getJSONObject("rates").getString("CHF");
                String sgd = json.getJSONObject("rates").getString("SGD");
                String pln = json.getJSONObject("rates").getString("PLN");
                String bgn = json.getJSONObject("rates").getString("BGN");
                String try1 = json.getJSONObject("rates").getString("TRY");
                String cny = json.getJSONObject("rates").getString("CNY");
                String nok = json.getJSONObject("rates").getString("NOK");
                String nzd = json.getJSONObject("rates").getString("NZD");
                String zar = json.getJSONObject("rates").getString("ZAR");
                String mxn = json.getJSONObject("rates").getString("MXN");
                String ils = json.getJSONObject("rates").getString("ILS");
                String krw = json.getJSONObject("rates").getString("KRW");
                String myr = json.getJSONObject("rates").getString("MYR");

                tiposDeCambio.put("USD - DÓLARES AMERICANOS", Double.parseDouble(usd));
                tiposDeCambio.put("GBP - LIBRAS ESTERLINAS", Double.parseDouble(gbp));
                tiposDeCambio.put("SEK - CORONAS SUECAS", Double.parseDouble(sek));
                tiposDeCambio.put("CAD - DÓLARES CANADIENSES", Double.parseDouble(cad));
                tiposDeCambio.put("HKD - DÓLARES DE HONG KONG", Double.parseDouble(hkd));
                tiposDeCambio.put("ISK - CORONAS ISLANDESAS", Double.parseDouble(isk));
                tiposDeCambio.put("PHP - PESOS FILIPINO", Double.parseDouble(php));
                tiposDeCambio.put("DKK - CORONAS DANESAS", Double.parseDouble(dkk));
                tiposDeCambio.put("HUF - FORINTS HÚNGAROS", Double.parseDouble(huf));
                tiposDeCambio.put("CZK - CORONAS CHEKAS", Double.parseDouble(czk));
                tiposDeCambio.put("AUD - DÓLARES AUSTRALIANOS", Double.parseDouble(aud));
                tiposDeCambio.put("RON - LEUS RUMANOS", Double.parseDouble(ron));
                tiposDeCambio.put("IDR - RUPIAS INDONESAS", Double.parseDouble(idr));
                tiposDeCambio.put("INR - RUPIAS INDIAS", Double.parseDouble(inr));
                tiposDeCambio.put("BRL - REALES BRASILEÑOS", Double.parseDouble(brl));
                tiposDeCambio.put("RUB - RUBLOS RUSOS", Double.parseDouble(rub));
                tiposDeCambio.put("HRK - KUNAS CROATAS", Double.parseDouble(hrk));
                tiposDeCambio.put("JPY - YENES JAPONESES", Double.parseDouble(jpy));
                tiposDeCambio.put("THB - BAHTS TAILANDESES", Double.parseDouble(thb));
                tiposDeCambio.put("CHF - FRANCOS SUIZOS", Double.parseDouble(chf));
                tiposDeCambio.put("SGD - DÓLARES SINGAPUR", Double.parseDouble(sgd));
                tiposDeCambio.put("PLN - ZLOTYS POLACOS", Double.parseDouble(pln));
                tiposDeCambio.put("BGN - LEVS BÚLGAROS", Double.parseDouble(bgn));
                tiposDeCambio.put("TRY - LIRAS TURCAS", Double.parseDouble(try1));
                tiposDeCambio.put("CNY - RENMINBINS CHINOS", Double.parseDouble(cny));
                tiposDeCambio.put("NOK - CORONAS NORUEGAS", Double.parseDouble(nok));
                tiposDeCambio.put("NZD - DÓLARES NEOZELANDES", Double.parseDouble(nzd));
                tiposDeCambio.put("ZAR - RANDS SUDAFRICANOS", Double.parseDouble(zar));
                tiposDeCambio.put("MXN - PESOS MEXICANOS", Double.parseDouble(mxn));
                tiposDeCambio.put("ILS - SÉQUELS ISRAELIS", Double.parseDouble(ils));
                tiposDeCambio.put("KRW - WONS SURCOREANOS", Double.parseDouble(krw));
                tiposDeCambio.put("MYR - RINGGITS MALASIOS", Double.parseDouble(myr));

            } catch (Exception e) {
                Log.e("OTRO ERROR", e.getMessage());
            }
        }
    }


    public void convertirDivisas(View v){

        try {

            String monedaSeleccionada = monedaCamb.getSelectedItem().toString();
            Double tipoDeCambio = tiposDeCambio.get(monedaSeleccionada);

            Double cantidadIntroducida = Double.parseDouble(cantidad.getText().toString());
            Double valor = cantidadIntroducida * tipoDeCambio;

            String resultado = String.format("%.2f", cantidadIntroducida) + "€ son " + String.format("%.2f", valor) + " " + monedaSeleccionada;

            Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
            t_resultado.setText(resultado);
        }catch (Exception e){
            String mensajeError = "NO HA SELECCIONADO NINGUNA CANTIDAD CORRECTA";
            Toast.makeText(this, mensajeError, Toast.LENGTH_LONG).show();
        }

    }

}
