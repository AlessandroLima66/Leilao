package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void propoem(Lance lance) {
        lances.add(lance);
        Collections.sort(lances);
        double valorLance = lance.getValor();

        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public void setMenorLance(double menorLance) {
        this.menorLance = menorLance;
    }

    public List<Lance> tresMaioresLances() {
        int quantidadeDeLances = lances.size();

        if (quantidadeDeLances > 3){
            return lances.subList(0, 3);
        }
        return lances.subList(0, quantidadeDeLances);
    }
}
