package com.hunter.controlrutasyaku.Entidad;

public class E_DetallePedido {

    private int pedDet_Id;
    private String ped_Id;
    private int pro_Id;
    private int pedDet_Cantidad;
    private double pedDet_PrecioUni;
    private double pedDet_Importe;

    public E_DetallePedido() {
    }

    public E_DetallePedido(int pedDet_Id, String ped_Id, int pro_Id, int pedDet_Cantidad, double pedDet_PrecioUni, double pedDet_Importe) {
        this.pedDet_Id = pedDet_Id;
        this.ped_Id = ped_Id;
        this.pro_Id = pro_Id;
        this.pedDet_Cantidad = pedDet_Cantidad;
        this.pedDet_PrecioUni = pedDet_PrecioUni;
        this.pedDet_Importe = pedDet_Importe;
    }

    public int getPedDet_Id() {
        return pedDet_Id;
    }

    public void setPedDet_Id(int pedDet_Id) {
        this.pedDet_Id = pedDet_Id;
    }

    public String getPed_Id() {
        return ped_Id;
    }

    public void setPed_Id(String ped_Id) {
        this.ped_Id = ped_Id;
    }

    public int getPro_Id() {
        return pro_Id;
    }

    public void setPro_Id(int pro_Id) {
        this.pro_Id = pro_Id;
    }

    public int getPedDet_Cantidad() {
        return pedDet_Cantidad;
    }

    public void setPedDet_Cantidad(int pedDet_Cantidad) {
        this.pedDet_Cantidad = pedDet_Cantidad;
    }

    public double getPedDet_PrecioUni() {
        return pedDet_PrecioUni;
    }

    public void setPedDet_PrecioUni(double pedDet_PrecioUni) {
        this.pedDet_PrecioUni = pedDet_PrecioUni;
    }

    public double getPedDet_Importe() {
        return pedDet_Importe;
    }

    public void setPedDet_Importe(double pedDet_Importe) {
        this.pedDet_Importe = pedDet_Importe;
    }
}
