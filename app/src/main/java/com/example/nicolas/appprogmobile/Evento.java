package com.example.nicolas.appprogmobile;

public class Evento {
    private String tipo;
    private String data;
    private String hora;
    private String local;
    private String qntPessoas ;
    private String cliente;

    public Evento() {
        this.tipo = tipo;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.qntPessoas = qntPessoas;
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getQntPessoas() {
        return qntPessoas;
    }

    public void setQntPessoas(String qntPessoas) {
        this.qntPessoas = qntPessoas;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        return "Evento: " +
                "\nTipo: " + tipo  +
                "\nData: " + data +
                "\nHorário: " + hora +
                "\nData: " + data +
                "\nLocal: " + local +
                "\nQuantidade de pessoas esperadas: " + qntPessoas +
                "\nCliente solicitante: " + cliente;
    }
}
