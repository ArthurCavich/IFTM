# Relatorio de Erros - Atividade A2: Calculadora com Memoria

**Aluno:** Arthur Santana Cavichioli
**Data:** 8 de marco de 2026
**Disciplina:** Testes Automatizados com JUnit

---

## Resumo da Execucao

- Comando executado: `./mvnw.cmd test`
- Total de testes: 17
- Falhas: 8
- Erros: 1
- Cenarios aprovados: 8

---

## Estrutura Esperada (Metodo, Cenario, Erro)

### 1) Construtor vazio
- Metodo testado: `Calculadora()`
- Cenario testado: inicializar a calculadora sem parametro
- Erro encontrado: a memoria inicializa com `1`, mas o esperado no enunciado e iniciar com `0`.
- Evidencia do teste: `memoria()` falhou com `expected: <0> but was: <1>`.

### 2) Subtrair valor positivo
- Metodo testado: `subtrair(int valor)`
- Cenario testado: `3 - 2` deveria resultar em `1`
- Erro encontrado: o metodo nao altera a memoria (`this.memoria = this.memoria;`), portanto a subtracao nao acontece.
- Evidencia do teste: `subtracao()` falhou com `expected: <1> but was: <3>`.

### 3) Subtrair valor negativo
- Metodo testado: `subtrair(int valor)`
- Cenario testado: `3 - (-2)` deveria resultar em `5`
- Erro encontrado: o metodo continua sem alterar a memoria, ignorando o parametro recebido.
- Evidencia do teste: `subtracaoNegativo()` falhou com `expected: <5> but was: <3>`.

### 4) Multiplicar valor positivo
- Metodo testado: `multiplicar(int valor)`
- Cenario testado: `3 * 2` deveria resultar em `6`
- Erro encontrado: o metodo divide em vez de multiplicar (`this.memoria = this.memoria / valor`).
- Evidencia do teste: `multi()` falhou com `expected: <6> but was: <1>`.

### 5) Multiplicar valor negativo
- Metodo testado: `multiplicar(int valor)`
- Cenario testado: `3 * (-2)` deveria resultar em `-6`
- Erro encontrado: o metodo faz divisao inteira; `3 / -2` vira `-1`, resultado incorreto para multiplicacao.
- Evidencia do teste: `multiNegativo()` falhou com `expected: <-6> but was: <-1>`.

### 6) Dividir valor positivo
- Metodo testado: `dividir(int valor)`
- Cenario testado: `3 / 3` deveria resultar em `1`
- Erro encontrado: o teste estava esperando `0`, mas o resultado matematico correto e `1`.
- Evidencia do teste: `div()` falhou com `expected: <0> but was: <1>`.
- Observacao importante: aqui o erro e de especificacao do cenario de teste, nao do metodo da classe.

### 7) Dividir valor negativo
- Metodo testado: `dividir(int valor)`
- Cenario testado: `3 / (-2)` deveria resultar em `-1` (divisao inteira em Java)
- Erro encontrado: a validacao do metodo esta incorreta, pois bloqueia qualquer valor `<= 1` e lanca excecao tambem para negativos.
- Evidencia do teste: `divNegativo()` gerou erro `java.lang.Exception: Divisao por zero!!!`.

### 8) Exponenciar elevado a 1
- Metodo testado: `exponenciar(int valor)`
- Cenario testado: `3^1` deveria resultar em `3`
- Erro encontrado: o metodo ignora o parametro `valor` no laco e faz quadrado repetido (`memoria *= memoria`), produzindo numero incorreto.
- Evidencia do teste: `exponenciar()` falhou com `expected: <3> but was: <1995565057>`.

### 9) Exponenciar elevado a 10
- Metodo testado: `exponenciar(int valor)`
- Cenario testado: `2^10` deveria resultar em `1024`
- Erro encontrado: mesma logica incorreta de quadrado repetido, causando overflow de inteiro e resultado final errado.
- Evidencia do teste: `exponenciar10()` falhou com `expected: <1024> but was: <0>`.

---

## Cenarios Que Passaram

- `memoria2()`
- `memoria3()`
- `soma()`
- `somaNegativo()`
- `divZero()`
- `exponenciar20()`
- `zerarMemoria()`
- `Atividade2ApplicationTests.contextLoads()`

---
