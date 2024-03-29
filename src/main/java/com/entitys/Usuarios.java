package com.entitys;
// Generated 21-oct-2016 13:31:23 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuarios generated by hbm2java
 */
@Entity
@Table(name="Usuarios"
    ,catalog="intercity"
)
public class Usuarios  implements java.io.Serializable {


     private String telefono;
     private int codigoArea;
     private String password;
     private String nombre;
     private String pais;
     private String status;

    public Usuarios() {
    }

	
    public Usuarios(String telefono, int codigoArea, String password) {
        this.telefono = telefono;
        this.codigoArea = codigoArea;
        this.password = password;
    }
    public Usuarios(String telefono, int codigoArea, String password, String nombre, String pais, String status) {
       this.telefono = telefono;
       this.codigoArea = codigoArea;
       this.password = password;
       this.nombre = nombre;
       this.pais = pais;
       this.status = status;
    }
   
     @Id 

    
    @Column(name="telefono", unique=true, nullable=false, length=15)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Column(name="codigo_area", nullable=false)
    public int getCodigoArea() {
        return this.codigoArea;
    }
    
    public void setCodigoArea(int codigoArea) {
        this.codigoArea = codigoArea;
    }

    
    @Column(name="password", nullable=false, length=30)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="Nombre", length=60)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="Pais", length=20)
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }

    
    @Column(name="status", length=25)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }




}


