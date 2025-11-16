//Beatriz Soares RA -10443916
//Mariana Agostinho RA -10435657
//Murilo Franciscon RA -10438787
public class ABB {

    private Node root; 

    ABB() {
  
        root = null;
    }

    public boolean isEmpty() {
    
        return root == null;
    }

    public Node root() {
   
        return root;
    }

    public boolean isLeaf(Node n) {

        return n.left == null && n.right == null;
    }

    public void executaPreOrdem(Node no) {
        if (no == null) {
            return;
        }
        no.mostraNo();
        executaPreOrdem(no.left);
        executaPreOrdem(no.right);
    }

    public void executaInOrdem(Node no) {
        if (no == null) {
            return;
        }
        executaInOrdem(no.left);
        no.mostraNo();
        executaInOrdem(no.right);
    }

    public void executaPosOrdem(Node no) {
        if (no == null) {
            return;
        }
        executaPosOrdem(no.left);
        executaPosOrdem(no.right);
        no.mostraNo();
    }

    public void insere(Node z) {
        Node y = null;
        Node x = root();
        while (x != null) {
            y = x;
            int cmp = z.elemento.getPalavra().compareTo(x.elemento.getPalavra());
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            root = z;
        } else {
            int cmp = z.elemento.getPalavra().compareTo(y.elemento.getPalavra());
            if (cmp < 0) {
                y.left = z;
            } else {
                y.right = z;
            }
        }
    }

    public void delete(Node Tree, int Tar) {
        // Este método não está sendo usado no App, mas ajustando para String
        // Se quiser deletar por palavra, mude o tipo do parâmetro para String
        // e ajuste as comparações para usar compareTo
    }

    public Node busca(Node k) {
        Node y = root();
        String palavraBusca = k.elemento.getPalavra();
        while (y != null) {
            int cmp = palavraBusca.compareTo(y.elemento.getPalavra());
            if (cmp == 0) {
                return y;
            } else if (cmp < 0) {
                y = y.left;
            } else {
                y = y.right;
            }
        }
        return null;
    }

    public Node maximo(Node x) {
        //Node<E> x = root();
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Node minimo(Node n) {
        Node x = n;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

}
