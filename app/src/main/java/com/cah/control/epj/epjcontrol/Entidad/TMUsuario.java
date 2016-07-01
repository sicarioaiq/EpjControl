package com.cah.control.epj.epjcontrol.Entidad;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by carlos.sarmiento on 10/06/2016.
 */
public class TMUsuario {
    private int intId;
    private String strUsuario;
    private String strPassword;
    private String strMail;
    private int intIdPerfil;
    private int  intActivo;
    private String strRuta;
    private String ErrorMensaje;
    private String ErrorCode;

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public int getIntIdPerfil() {
        return intIdPerfil;
    }

    public void setIntIdPerfil(int intIdPerfil) {
        this.intIdPerfil = intIdPerfil;
    }

    public int getIntActivo() {
        return intActivo;
    }

    public void setIntActivo(int intActivo) {
        this.intActivo = intActivo;
    }

    public String getStrMail() {
        return strMail;
    }

    public void setStrMail(String strMail) {
        this.strMail = strMail;
    }

    public void setStrRuta(String strRuta) {
        this.strRuta = strRuta;
    }

    public String getErrorMensaje(){
        return  ErrorMensaje;
    }

    public void setErrorMensaje(String strErrorMensaje){
        this.ErrorMensaje = strErrorMensaje;
    }

    public String getErrorCode(){
        return  ErrorCode;
    }

    public String getStrRuta() {
        return strRuta;
    }


    public void setErrorCode(String strErrorCode){
        this.ErrorCode = strErrorCode;
    }

    public static TMUsuario fromJson(JSONObject jsonObject) {
        TMUsuario b = new TMUsuario();
        // Deserialize json into object fields
        try {
            b.intId = jsonObject.getInt("intId");
            b.strUsuario = jsonObject.getString("strUsuario");
            b.strPassword = jsonObject.getString("strPassword");
//            b.strMail = jsonObject.getString("strMail");
            b.intIdPerfil = jsonObject.getInt("intIdPerfil");
            b.intActivo = jsonObject.getInt("intActivo");
            b.strRuta = jsonObject.getString("strRuta");
            b.ErrorMensaje = jsonObject.getString("ErrorMensaje");
            b.ErrorCode = jsonObject.getString("ErrorCode");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return b;
    }
}
