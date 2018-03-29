package BuscaHeuristica;

/**
 * No
 */
public class No {

	private String pai;
	private String nome;
	private int nivel;
	private int peso;
	private int heuristica;
	private int pesoFinal;

	public No(String pai, String nome, int nivel) {
		this.pai = pai;
		this.nome = nome;
		this.nivel = nivel;
	}

	public No(String pai, String nome, int nivel, int peso) {
		this.pai = pai;
		this.nome = nome;
		this.nivel = nivel;
		this.peso = peso;
	}

	public No(String pai, String nome, int nivel, int peso, int heuristica) {
		this.pai = pai;
		this.nome = nome;
		this.nivel = nivel;
		this.peso = peso;
		this.heuristica = heuristica;
	}

	/**
	 * @return the pai
	 */
	public String getPai() {
		return pai;
	}

	/**
	 * @param pai the pai to set
	 */
	public void setPai(String pai) {
		this.pai = pai;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * @return the heuristica
	 */
	public int getHeuristica() {
		return heuristica;
	}

	/**
	 * @param heuristica the heuristica to set
	 */
	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}

	/**
	 * @return the pesoFinal
	 */
	public int getPesoFinal() {
		return pesoFinal;
	}

	/**
	 * @param pesoFinal the pesoFinal to set
	 */
	public void setPesoFinal(int pesoFinal) {
		this.pesoFinal = pesoFinal;
	}

}