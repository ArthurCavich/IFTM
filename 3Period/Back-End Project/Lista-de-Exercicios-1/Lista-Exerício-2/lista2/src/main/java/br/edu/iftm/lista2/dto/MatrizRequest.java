package br.edu.iftm.lista2.dto;

import java.util.List;

public class MatrizRequest {

    private List<List<Double>> matriz;

    public List<List<Double>> getMatriz() {
        return matriz;
    }

    public void setMatriz(List<List<Double>> matriz) {
        this.matriz = matriz;
    }
}
