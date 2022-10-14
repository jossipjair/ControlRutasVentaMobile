package com.hunter.controlrutasyaku.Entidad;

public class E_Empleado {

    private String emp_Id;
    private String emp_Dni;
    private String emp_ApellidosNomb;
    private String emp_Telefono;

    public E_Empleado() {
    }

    public E_Empleado(String emp_Id, String emp_Dni, String emp_ApellidosNomb) {
        this.emp_Id = emp_Id;
        this.emp_Dni = emp_Dni;
        this.emp_ApellidosNomb = emp_ApellidosNomb;
    }

    public E_Empleado(String emp_ApellidosNomb, String emp_Telefono) {
        this.emp_ApellidosNomb = emp_ApellidosNomb;
        this.emp_Telefono = emp_Telefono;
    }

    public String getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }

    public String getEmp_Dni() {
        return emp_Dni;
    }

    public void setEmp_Dni(String emp_Dni) {
        this.emp_Dni = emp_Dni;
    }

    public String getEmp_ApellidosNomb() {
        return emp_ApellidosNomb;
    }

    public void setEmp_ApellidosNomb(String emp_ApellidosNomb) {
        this.emp_ApellidosNomb = emp_ApellidosNomb;
    }

    public String getEmp_Telefono() {
        return emp_Telefono;
    }

    public void setEmp_Telefono(String emp_Telefono) {
        this.emp_Telefono = emp_Telefono;
    }

    @Override
    public String toString(){
        return getEmp_ApellidosNomb();
    }
}
