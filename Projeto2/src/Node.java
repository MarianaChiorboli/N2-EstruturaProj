public class Node {

    Palavra elemento;  
    Node left, right, parent; 

    public Node(Palavra elemento) {
        this.elemento = elemento;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public void mostraNo() {
        System.out.print(elemento.getPalavra() + " (" 
                + elemento.getOcorrencias() + ", "
                + elemento.getNumeroCaracteres() + ") ");
    }
   }