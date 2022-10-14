package com.hunter.controlrutasyaku.Entidad;

public class E_Pedido {

    private String ped_Id;
    private String cliPro_Id;
    private String ped_DespachadoA;
    private String ped_FechaPed;
    private String ped_FechaHoraPed;
    private String ped_FechaHoraLog;
    private String ped_FechaEntregaPed;
    private String emp_Id;
    private String ped_FormaPag;
    private String ped_Observacion;

    public E_Pedido() {
    }

    public E_Pedido(String ped_Id, String cliPro_Id, String ped_DespachadoA, String ped_FechaPed, String ped_FechaHoraPed, String ped_FechaHoraLog, String ped_FechaEntregaPed, String emp_Id, String ped_FormaPag, String ped_Observacion) {
        this.ped_Id = ped_Id;
        this.cliPro_Id = cliPro_Id;
        this.ped_DespachadoA = ped_DespachadoA;
        this.ped_FechaPed = ped_FechaPed;
        this.ped_FechaHoraPed = ped_FechaHoraPed;
        this.ped_FechaHoraLog = ped_FechaHoraLog;
        this.ped_FechaEntregaPed = ped_FechaEntregaPed;
        this.emp_Id = emp_Id;
        this.ped_FormaPag = ped_FormaPag;
        this.ped_Observacion = ped_Observacion;
    }

    public String getPed_Id() {
        return ped_Id;
    }

    public void setPed_Id(String ped_Id) {
        this.ped_Id = ped_Id;
    }

    public String getCliPro_Id() {
        return cliPro_Id;
    }

    public void setCliPro_Id(String cliPro_Id) {
        this.cliPro_Id = cliPro_Id;
    }

    public String getPed_DespachadoA() {
        return ped_DespachadoA;
    }

    public void setPed_DespachadoA(String ped_DespachadoA) {
        this.ped_DespachadoA = ped_DespachadoA;
    }

    public String getPed_FechaPed() {
        return ped_FechaPed;
    }

    public void setPed_FechaPed(String ped_FechaPed) {
        this.ped_FechaPed = ped_FechaPed;
    }

    public String getPed_FechaHoraPed() {
        return ped_FechaHoraPed;
    }

    public void setPed_FechaHoraPed(String ped_FechaHoraPed) {
        this.ped_FechaHoraPed = ped_FechaHoraPed;
    }

    public String getPed_FechaHoraLog() {
        return ped_FechaHoraLog;
    }

    public void setPed_FechaHoraLog(String ped_FechaHoraLog) {
        this.ped_FechaHoraLog = ped_FechaHoraLog;
    }

    public String getPed_FechaEntregaPed() {
        return ped_FechaEntregaPed;
    }

    public void setPed_FechaEntregaPed(String ped_FechaEntregaPed) {
        this.ped_FechaEntregaPed = ped_FechaEntregaPed;
    }

    public String getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }

    public String getPed_FormaPag() {
        return ped_FormaPag;
    }

    public void setPed_FormaPag(String ped_FormaPag) {
        this.ped_FormaPag = ped_FormaPag;
    }

    public String getPed_Observacion() {
        return ped_Observacion;
    }

    public void setPed_Observacion(String ped_Observacion) {
        this.ped_Observacion = ped_Observacion;
    }
}
