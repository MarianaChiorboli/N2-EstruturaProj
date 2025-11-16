//Beatriz Soares RA -10443916
//Mariana Agostinho RA -10435657
//Murilo Franciscon RA -10438787
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ABB arvore = new ABB();
    private static ArrayList<String> textoPalavras = new ArrayList<>();
    private static boolean textoCarregado = false;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        
        while (opcao != 7) {
            System.out.println("===== MENU =====");
            System.out.println("1 - Carregar o texto");
            System.out.println("2 - Estatísticas");
            System.out.println("3 - Busca por palavra");
            System.out.println("4 - Busca por parte da palavra");
            System.out.println("5 - Exibição do texto");
            System.out.println("6 - Função inventada pelo grupo");
            System.out.println("7 - Encerrar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    carregarTexto();
                    break;
                case 2:
                    exibirEstatisticas();
                    break;
                case 3:
                    buscaPorPalavra(sc);
                    break;
                case 4:
                    buscaPorPartePalavra(sc);
                    break;
                case 5:
                    exibirTexto();
                    break;
                case 6:
                    funcaoInventada();
                    break;
                case 7:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
        sc.close();
    }

    private static void carregarTexto() {
        Scanner leitor = null;
        try {
            File arquivo = new File("Projeto2/src/cotidiano.txt");
            if (!arquivo.exists()) {
                throw new Exception("Arquivo cotidiano.txt não encontrado em Projeto2/src/");
            }
            leitor = new Scanner(arquivo);
            arvore = new ABB();
            textoPalavras.clear();
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().toLowerCase().trim();
                String[] palavras = linha.split("\\s+");
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        textoPalavras.add(palavra);
                        adicionarPalavraArvore(palavra);
                    }
                }
            }
            textoCarregado = true;
            System.out.println("Texto carregado com sucesso!");
            System.out.println("Total de palavras: " + textoPalavras.size());
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            textoCarregado = false;
        } finally {
            if (leitor != null) leitor.close();
        }
    }

    private static void adicionarPalavraArvore(String palavra) {
        Node novoNode = new Node(new Palavra(palavra));
        
        if (arvore.isEmpty()) {
            arvore.insere(novoNode);
        } else {
            Node encontrado = buscarNodeArvorePorPalavra(arvore.root(), palavra);
            if (encontrado != null) {
                encontrado.elemento.setOcorrencias(encontrado.elemento.getOcorrencias() + 1);
            } else {
                arvore.insere(novoNode);
            }
        }
    }

    private static Node buscarNodeArvorePorPalavra(Node no, String palavra) {
        if (no == null) {
            return null;
        }
        
        int comparacao = palavra.compareTo(no.elemento.getPalavra());
        
        if (comparacao == 0) {
            return no;
        } else if (comparacao < 0) {
            return buscarNodeArvorePorPalavra(no.left, palavra);
        } else {
            return buscarNodeArvorePorPalavra(no.right, palavra);
        }
    }

    private static void exibirEstatisticas() {
        if (!textoCarregado) {
            System.out.println("Carregue o texto primeiro!");
            return;
        }
        
        System.out.println("\n===== ESTATÍSTICAS =====");
        System.out.println("Total de palavras: " + textoPalavras.size());
        
        ArrayList<String> palavrasUnicas = new ArrayList<>();
        for (String palavra : textoPalavras) {
            if (!palavrasUnicas.contains(palavra)) {
                palavrasUnicas.add(palavra);
            }
        }
        System.out.println("Palavras únicas: " + palavrasUnicas.size());
        
        int totalCaracteres = 0;
        for (String palavra : textoPalavras) {
            totalCaracteres += palavra.length();
        }
        System.out.println("Total de caracteres: " + totalCaracteres);
        
        System.out.println("\nPalavras mais frequentes:");
        ArrayList<Node> palavrasOrdenadas = new ArrayList<>();
        coletarPalavras(arvore.root(), palavrasOrdenadas);
        
        palavrasOrdenadas.sort((a, b) -> Integer.compare(b.elemento.getOcorrencias(), a.elemento.getOcorrencias()));
        
        int limite = Math.min(10, palavrasOrdenadas.size());
        for (int i = 0; i < limite; i++) {
            Node n = palavrasOrdenadas.get(i);
            System.out.println((i + 1) + ". " + n.elemento.getPalavra() + ": " + n.elemento.getOcorrencias() + " ocorrências");
        }
    }

    private static void coletarPalavras(Node no, ArrayList<Node> lista) {
        if (no == null) {
            return;
        }
        coletarPalavras(no.left, lista);
        lista.add(no);
        coletarPalavras(no.right, lista);
    }

    private static void buscaPorPalavra(Scanner sc) {
        if (!textoCarregado) {
            System.out.println("Carregue o texto primeiro!");
            return;
        }
        
        System.out.print("Digite a palavra a procurar: ");
        String palavra = sc.nextLine().toLowerCase();
        
        Node encontrado = buscarNodeArvorePorPalavra(arvore.root(), palavra);
        
        if (encontrado != null) {
            System.out.println("Palavra encontrada!");
            System.out.println("Palavra: " + encontrado.elemento.getPalavra());
            System.out.println("Ocorrências: " + encontrado.elemento.getOcorrencias());
            System.out.println("Número de caracteres: " + encontrado.elemento.getNumeroCaracteres());
        } else {
            System.out.println("Palavra não encontrada no texto.");
        }
    }

    private static void buscaPorPartePalavra(Scanner sc) {
        if (!textoCarregado) {
            System.out.println("Carregue o texto primeiro!");
            return;
        }
        
        System.out.print("Digite a parte da palavra a procurar: ");
        String parte = sc.nextLine().toLowerCase();
        
        ArrayList<Node> palavrasEncontradas = new ArrayList<>();
        buscarPalavrasPorParte(arvore.root(), parte, palavrasEncontradas);
        
        if (palavrasEncontradas.isEmpty()) {
            System.out.println("Nenhuma palavra contém essa parte.");
        } else {
            System.out.println("Palavras encontradas:");
            for (Node n : palavrasEncontradas) {
                System.out.println("- " + n.elemento.getPalavra() + " (" + n.elemento.getOcorrencias() + " ocorrências)");
            }
        }
    }

    private static void buscarPalavrasPorParte(Node no, String parte, ArrayList<Node> resultado) {
        if (no == null) {
            return;
        }
        
        buscarPalavrasPorParte(no.left, parte, resultado);
        
        if (no.elemento.getPalavra().contains(parte)) {
            resultado.add(no);
        }
        
        buscarPalavrasPorParte(no.right, parte, resultado);
    }

    private static void exibirTexto() {
        if (!textoCarregado) {
            System.out.println("Carregue o texto primeiro!");
            return;
        }
        
        System.out.println("\n===== TEXTO CARREGADO =====");
        System.out.println("\nPalavras em ordem alfabética (In-ordem):");
        arvore.executaInOrdem(arvore.root());
        System.out.println("\n");
    }

    private static void funcaoInventada() {
        if (!textoCarregado) {
            System.out.println("Carregue o texto primeiro!");
            return;
        }
        
        System.out.println("\n===== FUNÇÃO INVENTADA: PALAVRAS POR TAMANHO =====");
        
        ArrayList<Node> todasPalavras = new ArrayList<>();
        coletarPalavras(arvore.root(), todasPalavras);
        
        int tamanhoMin = Integer.MAX_VALUE;
        int tamanhoMax = 0;
        
        for (Node n : todasPalavras) {
            int tamanho = n.elemento.getNumeroCaracteres();
            if (tamanho < tamanhoMin) tamanhoMin = tamanho;
            if (tamanho > tamanhoMax) tamanhoMax = tamanho;
        }
        
        System.out.println("Palavras agrupadas por número de caracteres:\n");
        
        for (int i = tamanhoMin; i <= tamanhoMax; i++) {
            final int tamanho = i;
            ArrayList<Node> palavrasComTamanho = new ArrayList<>();
            
            for (Node n : todasPalavras) {
                if (n.elemento.getNumeroCaracteres() == tamanho) {
                    palavrasComTamanho.add(n);
                }
            }
            
            if (!palavrasComTamanho.isEmpty()) {
                System.out.println("Palavras com " + i + " caracteres:");
                for (Node n : palavrasComTamanho) {
                    System.out.println("  - " + n.elemento.getPalavra() + " (" + n.elemento.getOcorrencias() + "x)");
                }
                System.out.println();
            }
        }
    }
}
