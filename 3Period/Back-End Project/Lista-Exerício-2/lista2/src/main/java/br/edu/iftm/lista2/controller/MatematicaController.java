package br.edu.iftm.lista2.controller;

import br.edu.iftm.lista2.dto.MatrizRequest;
import br.edu.iftm.lista2.dto.MediaRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController // diz pro spring que expõe endpoints rest
@RequestMapping("/matematica") // endpoint matematica
public class MatematicaController {

    // --- 1 ---
    @GetMapping("/soma")
    public ResponseEntity<?> somar(
            @RequestParam(required = false) String a,
            @RequestParam(required = false) String b) {

        // Tenta converter para número
        try {
            double numA = Double.parseDouble(a);
            double numB = Double.parseDouble(b);
            double resultado = numA + numB;
            return ResponseEntity.ok(Map.of("resultado", resultado));
        } catch (NumberFormatException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("erro", "Os parâmetros devem ser números válidos"));
        }
    }

    // calculo do fatorial
    private long calcularFatorial(int n) {
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // --- 2 ---
    @GetMapping("/fatorial")
    public ResponseEntity<?> fatorial(
            @RequestParam(required = false) String n) {

        // Tenta converter para número inteiro
        try {
            int numero = Integer.parseInt(n);

            // Valida se n é negativo
            if (numero < 0) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("erro", "O parâmetro 'n' deve ser maior ou igual a 0"));
            }

            long resultado = calcularFatorial(numero);
            return ResponseEntity.ok(Map.of("resultado", resultado));

        } catch (NumberFormatException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("erro", "O parâmetro 'n' deve ser numérico"));
        }
    }

    // --- 3 ---
    @PostMapping("/media")
    public ResponseEntity<?> calcularMedia(@RequestBody MediaRequest request) {

        List<Double> valores = request.getValores();

        // Calcula a soma de todos os elementos
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }

        double media = soma / valores.size();
        return ResponseEntity.ok(Map.of("resultado", media));
    }
    // --- 4 ---
    @PostMapping("/soma-linhas")
    public ResponseEntity<?> somaLinhas(@RequestBody MatrizRequest request) {

        List<List<Double>> matriz = request.getMatriz();

        // Valida se todas as linhas têm o mesmo tamanho
        int tamanhoLinha = matriz.get(0).size();
        for (List<Double> linha : matriz) {
            if (linha.size() != tamanhoLinha) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("erro", "Todas as linhas devem ter o mesmo tamanho"));
            }
        }

        // Soma cada linha
        List<Double> resultado = new ArrayList<>();
        for (List<Double> linha : matriz) {
            double soma = 0;
            for (double valor : linha) {
                soma += valor;
            }
            resultado.add(soma);
        }

        return ResponseEntity.ok(Map.of("resultado", resultado));
    }
}
