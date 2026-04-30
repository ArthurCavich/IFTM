/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.iftm.sistema.servicies;

public interface GerenciadorImpressora {

    boolean estaConectado();

    int imprimir(String nomeCompleto, String sigla);
}
