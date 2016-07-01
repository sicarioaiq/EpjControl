package com.cah.control.epj.epjcontrol;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cah.control.epj.epjcontrol.Entidad.TMUsuario;
import com.cah.control.epj.epjcontrol.request.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUpActivity extends Activity {
    Button btnSingIn;
    String usr, pwd, correo,strMensaje;
    EditText etEmail, etUserName,etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSingIn = (Button)findViewById(R.id.btnSingIn);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPass = (EditText)findViewById(R.id.etPass);
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr = etUserName.getText().toString();
                pwd = etPass.getText().toString();
                correo = etEmail.getText().toString();
                new HttpRequest().execute(usr, pwd,correo);
            }
        });
    }

    public class HttpRequest extends AsyncTask<String,Void,Void> {
        String strUser = "";
        String strPwd ="";
        String strCorreo = "";

        HttpURLConnection urlConnection = null;
        String strUrl = "";
        JSONObject jsonObject = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("SYNC", "INIT");
            TMUsuario objTMUsuario;
            try {
                if (params!=null)
                {
                    if(params.length > 0)
                    {
                        strUser = params[0];
                        strPwd = params[1];
                        strCorreo = params[2];
                    }
                }
                URL url = new URL("http://webserviceepj.somee.com/Service1.svc/RegistroUsuario");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(20000);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                jsonObject = new JSONObject();
                String usuario = strUser, password = strPwd,correo = strCorreo;
                jsonObject.put("strUsuario", usuario);
                jsonObject.put("strPassword", password);
                jsonObject.put("strMail",correo);
                out.write(jsonObject.toString());
                out.close();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String response = convertStreamToString(in).trim();
                if (response.length()>0)
                {
                    Log.i("response", response);
                    JSONObject x = new JSONObject(response);
                    objTMUsuario = TMUsuario.fromJson(x);
                    if (objTMUsuario!=null) {

                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntId()));
                        Log.i("objTMUsuario", objTMUsuario.getStrUsuario());
                        Log.i("objTMUsuario", objTMUsuario.getStrPassword());
                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntActivo()));
                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntIdPerfil()));
                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getStrRuta()));
                        strMensaje = String.valueOf(objTMUsuario.getErrorMensaje());
                    }
                }
                else
                {
                    Log.i("response", "sin dato");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), strMensaje, Toast.LENGTH_LONG).show();
            super.onPostExecute(aVoid);
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    line = line + "\n";
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }
}
