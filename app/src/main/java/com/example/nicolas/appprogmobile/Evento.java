package com.example.nicolas.appprogmobile;

public class Evento {

    private String tipoDeEvento; // tipo de evento ex: casamento,aniversario, palestra...
    private String local;
    private int qtdPessoas; // quantidade de pessoas no evento
    private String data;
    private String hora;

    public Evento() {

    }

    public Evento(String tipoDeEvento, String local, int qtdPessoas, String data, String hora) {
        this.tipoDeEvento = tipoDeEvento;
        this.local = local;
        this.qtdPessoas = qtdPessoas;
        this.data = data;
        this.hora = hora;
    }

    public String getTipoDeEvento() {
        return tipoDeEvento;
    }

    public void setTipoDeEvento(String tipoDeEvento) {
        this.tipoDeEvento = tipoDeEvento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
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
}
