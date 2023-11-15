package codigos;

abstract class huffmanArvore implements Comparable<huffmanArvore> {
	public final int frequencia;
	public huffmanArvore (int freq) {
		frequencia = freq;
	}
	public int compareTo(huffmanArvore arvore ) {
		return frequencia - arvore.frequencia;
	}
	
	
}
