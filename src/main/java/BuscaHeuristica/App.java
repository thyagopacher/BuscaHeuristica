package BuscaHeuristica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

  List<No> vetor = new ArrayList<No>();

  /** faz a leitura das heuristicas e distribui para cada um */
  public void lerArrayTxtHeuristica() {
    System.out.printf("\nConteúdo do arquivo texto para heuristica:\n");
    try {
      /** onde se encontra o projeto do arquivo */
      String path = new File(".").getCanonicalPath();
      FileReader arq = new FileReader(path + "/src/main/java/BuscaHeuristica/Heuristica.txt");
      BufferedReader lerArq = new BufferedReader(arq);
      String linha = lerArq.readLine(); // lê a primeira linha
      // a variável "linha" recebe o valor "null" quando o processo
      // de repetição atingir o final do arquivo texto
      while (linha != null) {
        System.out.printf("%s\n", linha);

        if (linha != null && !linha.isEmpty()) {
          String separa[] = linha.split("/");
          int heuristica = Integer.valueOf(separa[1]);

          int qtd = this.vetor.size();
          for(int i = 0; i < qtd; i++){
            No noVerificado = this.vetor.get(i);
            if(noVerificado.getNome().equals(separa[0])){
              noVerificado.setHeuristica(heuristica);
              noVerificado.setPesoFinal(noVerificado.getPeso() + noVerificado.getHeuristica());
              this.vetor.set(i, noVerificado);
              break;
            }
          }
          
        }
        linha = lerArq.readLine(); // lê da segunda até a última linha
      }
      
      arq.close();
    } catch (IOException e) {
      System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
    }
  }

  public void lerArrayTxt(String nome) {
    String raiz;
    System.out.printf("\nConteúdo do arquivo texto:\n");
    try {
      /** onde se encontra o projeto do arquivo */
      String path = new File(".").getCanonicalPath();
      FileReader arq = new FileReader(path + "/src/main/java/BuscaHeuristica/" + nome);
      BufferedReader lerArq = new BufferedReader(arq);
      String linha = lerArq.readLine(); // lê a primeira linha
      // a variável "linha" recebe o valor "null" quando o processo
      // de repetição atingir o final do arquivo texto
      while (linha != null) {
        System.out.printf("%s\n", linha);

        if (linha != null && !linha.isEmpty()) {
          String separa[] = linha.split("/");
          int peso = Integer.valueOf(separa[2]);
          if (this.vetor.size() == 0) {
            this.vetor.add(new No("", separa[0], 0, peso));//aqui consegue adicionar o primeiro nó q não tem pai, ou seja a raiz
          }
          String nomePai = separa[0];
          No noPai = this.vetor.stream().filter(pai -> nomePai.equals(pai.getNome())).findAny().orElse(null);
          int nivelRaizFilho = noPai.getNivel() + 1;
          this.vetor.add(new No(separa[0], separa[1], nivelRaizFilho, peso));//monta a arvore no arraylist
        }
        linha = lerArq.readLine(); // lê da segunda até a última linha
      }
      
      arq.close();
    } catch (IOException e) {
      System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
    }
  }

  public static <T> void main(String[] args) {
    App app = new App();
    app.lerArrayTxt("Grafo.txt");
    app.lerArrayTxtHeuristica();
    
    Busca busca = new BuscaGulosa();
    busca.setPontoInicial("B");
    busca.setPontoFinal("G");
    busca.setVetor(app.vetor);
    busca.buscar();

    System.out.println();
    busca = new BuscaGulosaA();
    busca.setPontoInicial("B");
    busca.setPontoFinal("G");
    busca.setVetor(app.vetor);
    busca.buscar();
  }
}
