package com.cah.control.epj.epjcontrol.request;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.cah.control.epj.epjcontrol.Entidad.TMUsuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by carlos.sarmiento on 10/06/2016.
 */
public class HttpRequest extends AsyncTask<String,Void,Void> {
    String strUser = "";
    String strPwd ="";
    HttpURLConnection urlConnection = null;

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
                }
            }
            URL url = new URL("http://webserviceepj.somee.com/Service1.svc/Login");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(20000);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            jsonObject = new JSONObject();
            String usuario = strUser;
            String password = strPwd;
            jsonObject.put("strUsuario", usuario);
            jsonObject.put("strPassword", password);
            out.write(jsonObject.toString());
            out.close();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String response = convertStreamToString(in).trim();
            if (response.length()>0)
            {
                Log.i("response", response);
                JSONObject x = new JSONObject(response);
                objTMUsuario = TMUsuario.fromJson(x);
                if (objTMUsuario!=null)
                {

                    Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntId()) );
                    Log.i("objTMUsuario", objTMUsuario.getStrUsuario());
                    Log.i("objTMUsuario", objTMUsuario.getStrPassword());
                    Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntActivo()));
                    Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntIdPerfil()));
                    Log.i("objTMUsuario", String.valueOf(objTMUsuario.getStrRuta()));
//                        File file = new File("ftp://caiquipa1206@96.43.137.44/www.WebServiceEPJ.somee.com/Slide1.JPG");
//
//                        downloadAndSaveFile("96.43.137.44", 21, "caiquipa1206", "Cesar120690g..", "Slide1.JPG", file);
//                        FtpConnection objFtp= new FtpConnection(getApplicationContext());
//                        objFtp.ftpConnect("96.43.137.44", "caiquipa1206", "Cesar120690g..", 21);
//
//                        boolean res=objFtp.ftpDownload("ftp://webserviceepj.somee.com/www.WebServiceEPJ.somee.com/Slide1.JPG", "/data/data/" + getApplicationContext().getPackageName() + "/"+"Slide1.JPG");
//                    FileDownloader.execute(LoginActivity.this, "http://webserviceepj.somee.com/Slide1.JPG", "Slide1.JPG", evDatabaseDownloadListener);
//                        objFtp.ftpDownload("ftp://caiquipa1206@96.43.137.44/www.WebServiceEPJ.somee.com/Slide1.JPG","Web.config");


                    Intent intent = null;

                    intent = new Intent("com.example.carlossarmiento.epj");


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
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
