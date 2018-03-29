package BuscaHeuristica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Busca Gulosa A* - levando em conta peso de heuristica e também a distancia do caminho

 */

public class BuscaGulosaA extends Busca {

    public BuscaGulosaA() {

    }

    /*Comparator for sorting the list by roll no*/
    public static Comparator<No> comparadorBusca = new Comparator<No>() {

        public int compare(No s1, No s2) {

            int rollno1 = s1.getPesoFinal();
            int rollno2 = s2.getPesoFinal();

            /*For ascending order*/
            return rollno1 - rollno2;

            /*For descending order*/
            //rollno2-rollno1;
        }
    };

    public boolean percorre(int num) {
        boolean acho = false;
        final int i = num + 1;
        //filtra o vetor para resultados abaixo daquele pai
        List<No> result = this.getVetor().stream().filter(line -> {
            if (this.getPai() != null && !this.getPai().isEmpty() && this.getPai().equals(line.getPai())) {
                return true;
            } else if (i == line.getNivel() && (this.getPai() == null || this.getPai().isEmpty())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Collections.sort(result, comparadorBusca);
        if (result != null && !result.isEmpty()) {
            int qtdResult = result.size();
            int pesoAnterior = 0;
            int qtdNoIgual = 0;
            // esse for compara se os valores perante o nível não são iguais
            for (int j = 0; j < qtdResult; j++) {
                No noLaco = result.get(j);
                if (pesoAnterior != 0 && noLaco.getPesoFinal() == pesoAnterior && i > 0) {
                    qtdNoIgual++;//vai somando se valores iguais no nivel
                }
                pesoAnterior = noLaco.getHeuristica();
            }
            // System.out.println("\n == Nivel com " + qtdResult + " nós ==");
            if (qtdNoIgual > 0 && qtdNoIgual == qtdResult - 1) {
                System.out.println("\n== Heuristica não admissível todos os nós do nível com mesmo valor ==");
                acho = true;
            }else{
                No noEncontrado = result.get(0);
                this.somarCustoBusca(noEncontrado.getHeuristica());
                System.out.print(" -> " + noEncontrado.getNome() + " - Heuristica: " + noEncontrado.getHeuristica());
                this.setPai(noEncontrado.getNome());
                if (noEncontrado.getNome().equals(this.getPontoFinal())) {
                    acho = true;
                }                
            }
        } else {
            System.out.println("\n == Busca Gulosa A * não conseguiu encontrar o final ==");
            acho = true;
        }
        return acho;
    }

    public void buscar() {
        System.out.println("\n === Busca Gulosa A* ===");
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