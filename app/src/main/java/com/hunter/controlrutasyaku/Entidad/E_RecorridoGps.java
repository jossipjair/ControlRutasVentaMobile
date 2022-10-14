package com.hunter.controlrutasyaku.Entidad;

public class E_RecorridoGps {

    private int recGps_Id;
    private String recGps_FechaHora;
    private String recGps_Fecha;
    private String recGps_FechaHoraLog;
    private String recGps_Latitud;
    private String recGps_Longitud;

    public E_RecorridoGps() {
    }

    public E_RecorridoGps(int recGps_Id, String recGps_FechaHora, String recGps_Fecha, String recGps_FechaHoraLog, String recGps_Latitud, String recGps_Longitud) {
        this.recGps_Id = recGps_Id;
        this.recGps_FechaHora = recGps_FechaHora;
        this.recGps_Fecha = recGps_Fecha;
        this.recGps_FechaHoraLog = recGps_FechaHoraLog;
        this.recGps_Latitud = recGps_Latitud;
        this.recGps_Longitud = recGps_Longitud;
    }

    public int getRecGps_Id() {
        return recGps_Id;
    }

    public void setRecGps_Id(int recGps_Id) {
        this.recGps_Id = recGps_Id;
    }

    public String getRecGps_FechaHora() {
        return recGps_FechaHora;
    }

    public void setRecGps_FechaHora(String recGps_FechaHora) {
        this.recGps_FechaHora = recGps_FechaHora;
    }

    public String getRecGps_Fecha() {
        return recGps_Fecha;
    }

    public void setRecGps_Fecha(String recGps_Fecha) {
        this.recGps_Fecha = recGps_Fecha;
    }

    public String getRecGps_FechaHoraLog() {
        return recGps_FechaHoraLog;
    }

    public void setRecGps_FechaHoraLog(String recGps_FechaHoraLog) {
        this.recGps_FechaHoraLog = recGps_FechaHoraLog;
    }

    public String getRecGps_Latitud() {
        return recGps_Latitud;
    }

    public void setRecGps_Latitud(String recGps_Latitud) {
        this.recGps_Latitud = recGps_Latitud;
    }

    public String getRecGps_Longitud() {
        return recGps_Longitud;
    }

    public void setRecGps_Longitud(String recGps_Longitud) {
        this.recGps_Longitud = recGps_Longitud;
    }
}
