package com.example.nicolas.appprogmobile;

import java.io.Serializable;

public class Empresa implements Serializable {
    private String nomeEmpresa;
    private String cnpj;
    private String emailEmpresa;
    private String userEmpresa;
    private String passwordEmpresa;

    public Empresa(){

    }
    public Empresa(String nomeEmpresa, String cnpj, String emailEmpresa, String userEmpresa, String passwordEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.emailEmpresa = emailEmpresa;
        this.userEmpresa = userEmpresa;
        this.passwordEmpresa = passwordEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getUserEmpresa() {
        return userEmpresa;
    }

    public void setUserEmpresa(String userEmpresa) {
        this.userEmpresa = userEmpresa;
    }

    public String getPasswordEmpresa() {
        return passwordEmpresa;
    }

    public void setPasswordEmpresa(String passwordEmpresa) {
        this.passwordEmpresa = passwordEmpresa;
    }
}
