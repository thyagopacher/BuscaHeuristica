package BuscaHeuristica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Busca Gulosa - escolhe o ponto mais proximo sem levar em conta peso de heuristica só o peso do caminho
 */

public class BuscaGulosa extends Busca {

    public BuscaGulosa() {

    }

    /*Comparator for sorting the list by roll no*/
    public static Comparator<No> comparadorBusca = new Comparator<No>() {

        public int compare(No s1, No s2) {

            int rollno1 = s1.getHeuristica();
            int rollno2 = s2.getHeuristica();

            /*For ascending order*/
            return rollno1 - rollno2;

            /*For descending order*/
            //rollno2-rollno1;
        }
    };

    public boolean percorre(int num) {
        boolean acho = false;
        final int i = num + 1;

        List<No> result = this.getVetor().stream().filter(line -> {
            if (this.getPai() != null && !this.getPai().isEmpty() && this.getPai().equals(line.getPai())) {
                return true;
            } else if (i == line.getNivel()) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Collections.sort(result, comparadorBusca);
        if (result != null && !result.isEmpty()) {
            int qtdResult = result.size();
            int heuristicaAnterior = 0;
            int qtdNoIgual = 0;
            for (int j = 0; j < qtdResult; j++) {
                No noLaco = result.get(j);
                if (heuristicaAnterior != 0 && noLaco.getHeuristica() == heuristicaAnterior && i > 0) {
                    qtdNoIgual++;//vai somando se valores iguais no nivel
                } else {
                    this.somarCustoBusca(noLaco.getHeuristica());
                    System.out.print(" -> " + noLaco.getNome() + " - Heuristica: " + noLaco.getHeuristica());
                    this.setPai(noLaco.getNome());
                    if (noLaco.getNome().equals(this.getPontoFinal())) {
                        acho = true;
                    }
                }
                heuristicaAnterior = noLaco.getHeuristica();
            }
            System.out.println(" - Nivel com " + qtdResult + " nós");
            if (qtdNoIgual > 0 && qtdNoIgual == qtdResult - 1) {
                System.out.println("\n== Heuristica não admissível todos os nós do nível com mesmo valor ==");
                acho = true;
            }

        }
        return acho;
    }

    public void buscar() {
        System.out.println("\n === Busca Gulosa ===");
        System.out.println(" - Ponto inicial: " + this.getPontoInicial() + " - Ponto final: " + this.getPontoFinal()
                + "\n - Caminho percorrido:");
        int tamVetor = this.getVetor().size();

        if (tamVetor > 0) {
            boolean acho = this.percorre(-1);
            int i = 0;
            while (acho == false) {
                acho = this.percorre(i);
                if (acho || i > 10) {
                    break;
                }
                i++;
            }
        }
        System.out.println("\n - A busca teve custo de: " + this.getCustoBusca());
    }
}