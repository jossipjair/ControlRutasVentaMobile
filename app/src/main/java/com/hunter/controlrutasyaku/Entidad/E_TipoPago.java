package com.hunter.controlrutasyaku.Entidad;

public class E_TipoPago {

    private String tipoPago;

    public E_TipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public E_TipoPago() {
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String toString(){
        return getTipoPago();
    }
}
