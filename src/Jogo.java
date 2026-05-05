import java.util.Scanner;

public class Jogo {
    public static void iniciaMatriz(String[][] matriz, int l, int c) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                matriz[i][j] = "";
            }
        }
    }
    public static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.println(mensagem);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println("Digite um número inteiro: ");
                sc.next();
            }
        }
    }
    public static boolean verificaVitoria(String[][] matriz, String simbolo) {
        for (String[] value : matriz) {
            boolean linhaCompleta = true;
            for (int j = 0; j < matriz.length; j++) {
                if (!value[j].equals(simbolo)) {
                    linhaCompleta = false;
                    break;
                }
            }
            if (linhaCompleta) return true;
        }
        for (int j = 0; j < matriz.length; j++) {
            boolean colunaCompleta = true;
            for (String[] strings : matriz) {
                if (!strings[j].equals(simbolo)) {
                    colunaCompleta = false;
                    break;
                }
            }
            if (colunaCompleta) return true;
        }
        boolean diagPrincipal = true;
        boolean diagSecundaria = true;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][i] == null || !matriz[i][i].equals(simbolo)) {
                diagPrincipal = false;
            }
            if (matriz[i][matriz.length - 1 - i] == null || !matriz[i][matriz.length - i - 1].equals(simbolo)) {
                diagSecundaria = false;
            }
        }
        return diagPrincipal || diagSecundaria;
    }
    public static void igualaTabuleiro(String[][] matrizPeq, String[][] matrizGran) {
        for (int i = 0; i < matrizPeq.length; i++) {
            for (int j = 0; j < matrizPeq.length; j++) {
                int linhaGran = 2 + (i * 4);
                int colunaGran = 2 + (j * 4);

                matrizGran[linhaGran][colunaGran] = matrizPeq[i][j];
            }
        }
    }
    public static void iniciatabuleiro(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i % 4 == 0 && j % 4 == 0) {
                    matriz[i][j] = "+";
                } else if (j % 4 == 0) {
                    matriz[i][j] = "|";
                } else if (i % 4 == 0) {
                    matriz[i][j] = "-";
                } else {
                    matriz[i][j] = "";
                }
            }
        }
    }
    public static void exibirTabuleiro(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public static void jogada(String[][] matrizPeq, int jogador) {
        Scanner sc = new Scanner(System.in);
        int l, c;
        String simbolo = (jogador == 1) ? "X" : "O";

        while (true) {
            System.out.println("\nVez do Jogador: " + jogador + "(Símbolo: " + simbolo + ")");

            l = lerInteiro(sc, "Digite a linha entre (1-3): ") - 1;

            c = lerInteiro(sc,"Digite a coluna entre (1-3): ") - 1;

            if(l >= 0 && l < 3 && c >= 0 && c < 3) {
                if (matrizPeq[l][c].isEmpty()) {
                    matrizPeq[l][c] = simbolo;
                    break;
                }
                else{
                    System.out.println("Essa posição já está ocupada! escolha outra");
                }
            }
            else{
                System.out.println("Erro: Digite uma coordenada válida.");
            }
        }
    }

    public static void main(String[] args) {
        String[][] matrizPeq = new String[3][3];
        String[][] matrizGran = new String[13][13];
        Scanner sc = new Scanner(System.in);
        int jogador1 = 1, jogador2 = 2, escolha, rodadas = 0;


        iniciaMatriz(matrizPeq,3,3);
        iniciatabuleiro(matrizGran);
        exibirTabuleiro(matrizGran);

        do{
            System.out.println("==================================");
            System.out.println("           Jogo da velha");
            System.out.println("Jogador 1: X            Jogador 2: O");
            System.out.println("==================================\n");

            while(rodadas < 9 ) {

                jogada(matrizPeq, jogador1);
                igualaTabuleiro(matrizPeq, matrizGran);
                exibirTabuleiro(matrizGran);
                rodadas++;

                if(verificaVitoria(matrizPeq,"X")){
                    System.out.println("Parabéns, o jogador 1 venceu! (X)");
                    break;
                }

                if(rodadas == 9) {
                    System.out.println("Deu velha! ");
                    break;
                }

                jogada(matrizPeq, jogador2);
                igualaTabuleiro(matrizPeq, matrizGran);
                exibirTabuleiro(matrizGran);
                rodadas++;

                if(verificaVitoria(matrizPeq,"O")){
                    System.out.println("Parabéns, o jogador 2 venceu! (O)");
                    break;
                }
            }
            escolha = lerInteiro(sc, "Digite 0 para encerrar ou qualquer valor: ");

        }while(escolha != 0);
    }
}