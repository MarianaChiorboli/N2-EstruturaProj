//Beatriz Soares RA -10443916
//Mariana Agostinho RA -10435657
//Murilo Franciscon RA -10438787
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