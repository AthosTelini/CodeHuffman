package codigos;

class huffmanNo extends huffmanArvore {
    
    public final huffmanArvore esquerda, direita;

    public huffmanNo(huffmanArvore l, huffmanArvore r) {
        super(l.frequencia + r.frequencia);
        esquerda = l;
        direita = r;
    }
}

