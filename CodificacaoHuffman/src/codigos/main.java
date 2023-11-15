package codigos;
import java.util.*;

public class main {

    public static void main(String[] args) {
        String teste = "EstruturaDeDados";

        int[] charFreqs = new int[256];
        for (char c : teste.toCharArray())
            charFreqs[c]++;

        huffmanArvore arvore = construirArvore(charFreqs);

        printCodes(arvore, new StringBuffer());

        String codificar = codificar(arvore, teste);

        System.out.println("\nCodificação");
        System.out.println(codificar);

        System.out.println("\nTEXTO DECODIFICADO");
        System.out.println(decodificar(arvore, codificar));
    }

    public static huffmanArvore construirArvore(int[] charFreqs) {
        PriorityQueue<huffmanArvore> arvores = new PriorityQueue<huffmanArvore>();

        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0)
                arvores.offer(new huffmanFolha(charFreqs[i], (char) i));
        }

        while (arvores.size() > 1) {
            huffmanArvore a = arvores.poll();
            huffmanArvore b = arvores.poll();

            arvores.offer(new huffmanNo(a, b));
        }

        return arvores.poll();
    }

    public static String codificar(huffmanArvore arvore, String codificar) {
        assert arvore != null;

        String codificarTexto = "";
        for (char c : codificar.toCharArray()) {
            codificarTexto += (getCodes(arvore, new StringBuffer(), c));
        }
        return codificarTexto;
    }

    public static String decodificar(huffmanArvore arvore, String codificar) {
        assert arvore != null;

        String decodificarTexto = "";
        huffmanNo no = (huffmanNo) arvore;
        for (char code : codificar.toCharArray()) {
            if (code == '0') {
                if (no.esquerda instanceof huffmanFolha) {
                    decodificarTexto += ((huffmanFolha) no.esquerda).valor;
                    no = (huffmanNo) arvore;
                } else {
                    no = (huffmanNo) no.esquerda;
                }
            } else if (code == '1') {
                if (no.direita instanceof huffmanFolha) {
                    decodificarTexto += ((huffmanFolha) no.direita).valor;
                    no = (huffmanNo) arvore;
                } else {
                    no = (huffmanNo) no.direita;
                }
            }
        }
        return decodificarTexto;
    }

    public static void printCodes(huffmanArvore arvore, StringBuffer prefix) {
        assert arvore != null;

        if (arvore instanceof huffmanFolha) {
            huffmanFolha folha = (huffmanFolha) arvore;
            System.out.println(folha.valor + "\t  " + folha.frequencia + "\t\t" + prefix);
        } else if (arvore instanceof huffmanNo) {
            huffmanNo no = (huffmanNo) arvore;

            prefix.append('0');
            printCodes(no.esquerda, prefix);
            prefix.deleteCharAt(prefix.length() - 1);

            prefix.append('1');
            printCodes(no.direita, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public static String getCodes(huffmanArvore arvore, StringBuffer prefix, char w) {
        assert arvore != null;

        if (arvore instanceof huffmanFolha) {
            huffmanFolha folha = (huffmanFolha) arvore;

            if (folha.valor == w) {
                return prefix.toString();
            }

        } else if (arvore instanceof huffmanNo) {
            huffmanNo no = (huffmanNo) arvore;

            prefix.append('0');
            String esquerda = getCodes(no.esquerda, prefix, w);
            prefix.deleteCharAt(prefix.length() - 1);

            prefix.append('1');
            String direita = getCodes(no.direita, prefix, w);
            prefix.deleteCharAt(prefix.length() - 1);

            if (esquerda == null) return direita;
            else return esquerda;
        }
        return null;
    }
}
