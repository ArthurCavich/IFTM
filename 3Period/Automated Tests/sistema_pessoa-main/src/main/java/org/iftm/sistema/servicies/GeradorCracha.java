/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.iftm.sistema.servicies;

import org.iftm.sistema.entities.Pessoa;

class GeradorCracha {

    private GerenciadorImpressora gerenciadorImpressora;

    public GeradorCracha(GerenciadorImpressora gerenciadorImpressora) {
        this.gerenciadorImpressora = gerenciadorImpressora;
    }

    public void gerarPara(Pessoa pessoa) {
        gerenciadorImpressora.imprimir(pessoa.retornarNomeCompleto(), pessoa.retornarIniciais());
    }
}
