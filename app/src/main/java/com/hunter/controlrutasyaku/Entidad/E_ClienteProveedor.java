package com.hunter.controlrutasyaku.Entidad;

public class E_ClienteProveedor {

    private String cliPro_Id;
    private String cliPro_Ruc;
    private String cliPro_NombreCom;
    private String cliPro_PersonaContacto;
    private String cliPro_Direccion;
    private String cliPro_Telefono;
    private String cliPro_LatitudDir;
    private String cliPro_LongitudDir;
    private String emp_Id;

    public E_ClienteProveedor() {
    }


    public E_ClienteProveedor(String cliPro_Id, String cliPro_Ruc, String cliPro_NombreCom, String cliPro_PersonaContacto, String cliPro_Direccion, String cliPro_Telefono, String cliPro_LatitudDir, String cliPro_LongitudDir, String emp_Id) {
        this.cliPro_Id = cliPro_Id;
        this.cliPro_Ruc = cliPro_Ruc;
        this.cliPro_NombreCom = cliPro_NombreCom;
        this.cliPro_PersonaContacto = cliPro_PersonaContacto;
        this.cliPro_Direccion = cliPro_Direccion;
        this.cliPro_Telefono = cliPro_Telefono;
        this.cliPro_LatitudDir = cliPro_LatitudDir;
        this.cliPro_LongitudDir = cliPro_LongitudDir;
        this.emp_Id = emp_Id;
    }

    public String getCliPro_Id() {
        return cliPro_Id;
    }

    public void setCliPro_Id(String cliPro_Id) {
        this.cliPro_Id = cliPro_Id;
    }

    public String getCliPro_Ruc() {
        return cliPro_Ruc;
    }

    public void setCliPro_Ruc(String cliPro_Ruc) {
        this.cliPro_Ruc = cliPro_Ruc;
    }

    public String getCliPro_NombreCom() {
        return cliPro_NombreCom;
    }

    public void setCliPro_NombreCom(String cliPro_NombreCom) {
        this.cliPro_NombreCom = cliPro_NombreCom;
    }

    public String getCliPro_PersonaContacto() {
        return cliPro_PersonaContacto;
    }

    public void setCliPro_PersonaContacto(String cliPro_PersonaContacto) {
        this.cliPro_PersonaContacto = cliPro_PersonaContacto;
    }

    public String getCliPro_Direccion() {
        return cliPro_Direccion;
    }

    public void setCliPro_Direccion(String cliPro_Direccion) {
        this.cliPro_Direccion = cliPro_Direccion;
    }

    public String getCliPro_Telefono() {
        return cliPro_Telefono;
    }

    public void setCliPro_Telefono(String cliPro_Telefono) {
        this.cliPro_Telefono = cliPro_Telefono;
    }

    public String getCliPro_LatitudDir() {
        return cliPro_LatitudDir;
    }

    public void setCliPro_LatitudDir(String cliPro_LatitudDir) {
        this.cliPro_LatitudDir = cliPro_LatitudDir;
    }

    public String getCliPro_LongitudDir() {
        return cliPro_LongitudDir;
    }

    public void setCliPro_LongitudDir(String cliPro_LongitudDir) {
        this.cliPro_LongitudDir = cliPro_LongitudDir;
    }

    public String getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }
}
