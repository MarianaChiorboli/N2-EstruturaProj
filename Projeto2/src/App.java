public class App {
    public static void main(String[] args) throws Exception {
        java.util.Scanner sc = new java.util.Scanner(System.in);
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
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    //Opção 1 selecionada: Carregar o texto");
                    break;
                case 2:
                    //Opção 2 selecionada: Estatísticas");
                    break;
                case 3:
                    //Opção 3 selecionada: Busca por palavra");
                    break;
                case 4:
                    //Opção 4 selecionada: Busca por parte da palavra");
                    break;
                case 5:
                    //Opção 5 selecionada: Exibição do texto
                    break;
                case 6:
                    //Opção 6 selecionada
                    break;
                case 7:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
    }
}
