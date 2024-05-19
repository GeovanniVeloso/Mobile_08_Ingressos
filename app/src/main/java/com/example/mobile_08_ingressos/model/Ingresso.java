package com.example.mobile_08_ingressos.model;

public class Ingresso {

    private String id;
    private float valor;

    public Ingresso() {
        super();
    }

    public float calcValor(int taxa) throws Exception {
        if(valor > 0){
            return (valor + (valor * (taxa/100)));
        }else{
            String e = "Valor Vazio";
            throw new ArithmeticException(e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
