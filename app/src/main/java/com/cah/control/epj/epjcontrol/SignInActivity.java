package com.cah.control.epj.epjcontrol;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cah.control.epj.epjcontrol.DAO.TMPersonaDA;
import com.cah.control.epj.epjcontrol.DAO.TMUsuarioDA;
import com.cah.control.epj.epjcontrol.Entidad.TMUsuario;
import com.cah.control.epj.epjcontrol.SQL.MyDbHelper;
import com.cah.control.epj.epjcontrol.request.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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
import java.net.URLConnection;
import java.text.DecimalFormat;


public class SignInActivity extends Activity{
    EditText etUserName,etPass;
    Button btnSingIn;
    String usr, pwd;
    Boolean loginOk = false;
    TMUsuarioDA sqlcon;
    ProgressBar progressBar;
    LinearLayout ll1;
    TextView tv1;
    Integer finalI= 1;
    Integer TotallenghtOfFile;
    private MyDbHelper dbhelper;
    private ProgressDialog pDialog;
    private Context ourcontext;
    private SQLiteDatabase database;
    public static final int progress_bar_type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPass = (EditText)findViewById(R.id.etPass);
        btnSingIn = (Button)findViewById(R.id.btnSingIn);
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
        tv1 = (TextView)findViewById(R.id.tv1);
        ll1 = (LinearLayout)findViewById(R.id.ll1);
        Drawable draw = getDrawable(R.drawable.custom_progress_bar);
        progressBar.setProgressDrawable(draw);

        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr = etUserName.getText().toString();
                pwd = etPass.getText().toString();
                if (usr.trim().length()!=0 && pwd.trim().length()!=0)
                {
                    int rows = 0;
                    try {
                        sqlcon = new TMUsuarioDA(getApplicationContext());
                        sqlcon.open();
                        final Cursor c = sqlcon.readEntryUsuario(usr.toString().trim(), pwd.toString().trim());
                        sqlcon.close();
                        rows = c.getCount();
                    }catch (Exception ex)
                    {
//                        ex.printStackTrace();
                        rows = 0;
                    }
//                    if (rows>0)
//                    {
//                        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
//                        startActivity(i);
//                    }
//                    else
//                    {
//                        new HttpRequest().execute(usr, pwd);
//                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"El Usuario y/o Contraseña son inconrrectos.", Toast.LENGTH_LONG).show();
                }
//                new HttpRequest().execute(usr, pwd);
//                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivity(i);
                new HttpRequest().execute(usr, pwd);
            }
        });
    }

    public class HttpRequest extends AsyncTask<String,Void,Void> {
        String strUser = "";
        String strPwd ="";
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
                    if (objTMUsuario!=null) {

                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntId()));
                        Log.i("objTMUsuario", objTMUsuario.getStrUsuario());
                        Log.i("objTMUsuario", objTMUsuario.getStrPassword());
                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntActivo()));
                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getIntIdPerfil()));
                        Log.i("objTMUsuario", String.valueOf(objTMUsuario.getStrRuta()));
                        if(objTMUsuario.getErrorCode().trim().isEmpty() && objTMUsuario.getErrorMensaje().trim().isEmpty())
                        {
                            if (objTMUsuario.getStrUsuario()!=null && objTMUsuario.getStrRuta() !=null)
                            {
                                strUrl = objTMUsuario.getStrRuta().trim().toString();
                            }
                        }
                        else
                        {

                        }
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
            if(!strUrl.isEmpty())
            {
                new DownloadFileFromURL().execute(strUrl);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"El Usuario y/o Contraseña son inconrrectos.", Toast.LENGTH_LONG).show();
            }
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

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
//                pDialog = new ProgressDialog(this);
//                pDialog.setMessage("Descargando Base de Datos. Por favor espere.");
//                pDialog.setIndeterminate(false);
//                pDialog.setMax(100);
//                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                pDialog.setCancelable(true);
//                pDialog.show();
//                return pDialog;
            default:
                return null;
        }
    }

    private class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            showDialog(progress_bar_type);
            progressBar.setVisibility(View.VISIBLE);
            tv1.setVisibility(View.VISIBLE);
            ll1.setVisibility(View.VISIBLE);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection;
                conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();
                TotallenghtOfFile = lenghtOfFile;
                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
//                Output stream to write file
//                String ruta = Environment.getExternalStorageDirectory().getPath() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator;
                File file = new File(getApplicationContext().getDatabasePath("Escuela.sqlite").getParent() );
                File filee = new File(getApplicationContext().getDatabasePath("Escuela.sqlite").getParent(),"Escuela.sqlite");
                boolean booOk =false;
                if(!file.exists()) {
                    booOk = file.mkdir();
                    if (!booOk) {
                        return null;
                    }
                }
                OutputStream output = new FileOutputStream(filee);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
//                    publishProgress(""+(int)((total*100)/lenghtOfFile));
                    publishProgress(""+(int)total);
                    // writing data to file
                    output.write(data, 0, count);
                }
                // flushing output
                output.flush();
                // closing streams
                output.close();
                input.close();
                loginOk = true;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
//            finalI = Integer.parseInt(progress[0]);
//            pDialog.setProgress(Integer.parseInt(progress[0]));

            progressBar.setProgress(Integer.parseInt(progress[0]));
            tv1.setText("Descargando " + getCorrectSize(Integer.parseInt(progress[0])) + " de " + getCorrectSize(TotallenghtOfFile));
            tv1.setTextColor(Color.WHITE);
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
//            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            String ruta = Environment.getExternalStorageDirectory().getPath() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "hive.jpg";
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";

            if (loginOk) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        }

        private String getCorrectSize(int value) {
            String actualSize;
             if (value > 1048576) {
                int MB = 1024 * 1024;
                DecimalFormat form = new DecimalFormat("0.00");
                actualSize = form.format(value * 1.0 / MB);
                actualSize += " MB";
             } else {
                 int KB = 1024;
                 actualSize = value > KB ? String.valueOf(value * 1.0f / KB)
                 : String.valueOf(value);
                 actualSize += " KB.";
             }
            return actualSize;
        }
    }
}

