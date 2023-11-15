package codigos;

 class huffmanFolha extends huffmanArvore {
	public final char valor;
	
	public huffmanFolha (int freq,  char val) {
		super(freq);
		valor = val;
	}
}
