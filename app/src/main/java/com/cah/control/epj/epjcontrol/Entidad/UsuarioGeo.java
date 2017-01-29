package com.cah.control.epj.epjcontrol.Entidad;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by usuario on 29/01/2017.
 */
public class UsuarioGeo {
    private String GuidUsuario;
    private String Usuario;
    private String Nombres;
    private String Apellidos;
    private String Correo;
    private String Clave;
    private Integer Activo;

    public Boolean getErrorRequest() {
        return ErrorRequest;
    }

    public void setErrorRequest(Boolean errorRequest) {
        ErrorRequest = errorRequest;
    }

    public Integer getCodigoMensaje() {
        return CodigoMensaje;
    }

    public void setCodigoMensaje(Integer codigoMensaje) {
        CodigoMensaje = codigoMensaje;
    }

    public String getMensajeRequest() {
        return MensajeRequest;
    }

    public void setMensajeRequest(String mensajeRequest) {
        MensajeRequest = mensajeRequest;
    }

    private Boolean ErrorRequest;
    private Integer CodigoMensaje;
    private String MensajeRequest;



    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getGuidUsuario() {
        return GuidUsuario;
    }

    public void setGuidUsuario(String guidUsuario) {
        GuidUsuario = guidUsuario;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public Integer getActivo() {
        return Activo;
    }

    public void setActivo(Integer activo) {
        Activo = activo;
    }

    public static UsuarioGeo fromJson(JSONObject jsonObject) {
        UsuarioGeo b = new UsuarioGeo();
        // Deserialize json into object fields
        try {
            b.GuidUsuario = jsonObject.getString("GuidUsuario");
            b.Usuario = jsonObject.getString("Usuario");
            b.Nombres = jsonObject.getString("Nombres");
            b.Apellidos = jsonObject.getString("Apellidos");
            b.Correo = jsonObject.getString("Correo");
            b.Clave = jsonObject.getString("Clave");
            b.Activo = jsonObject.getInt("Activo");
            b.MensajeRequest =  jsonObject.getString("MensajeRequest");
            b.ErrorRequest =  jsonObject.getBoolean("ErrorRequest");
            b.CodigoMensaje =  jsonObject.getInt("CodigoMensaje");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return b;
    }
}
