package Mapa;

import java.util.Scanner;

public class JogoDaVelha {
	private static char [][] board = {
			{' ', ' ', ' '},
			{' ', ' ', ' '},
			{' ', ' ', ' '}
	};
	
	private static char jogadorAtual = 'X';
	
	private static String jogador1;
	private static String jogador2;
	
	private static Scanner sc = new Scanner(System.in); 
	
	public static void main(String[] args) {
		boolean playAgain;
		
		System.out.println("Digite o nome do jogador 1: ");
		jogador1 = sc.nextLine();
		
		System.out.println("Digite o nome do jogador 2: ");
		jogador2 = sc.nextLine();
		
		do {
			cleanBoard();
			boolean jogando = true;
			
			while(jogando) {
				showBoard();
				play(sc);
				
				if(victory()) {
					showBoard();
					if(jogadorAtual == 'X') {
						System.out.println("\nParabéns " + jogador1 + " você venceu!\n");
					} else {
						System.out.println("\nParabéns " + jogador2 + " você venceu!\n");
					}
					jogando = false;
				} else if(draw()) {
					showBoard();
					System.out.println("\nO jogo terminou em empate\n");
					jogando = false;
				} else {
					changePlayer();
				}
			}
			
			System.out.print("Deseja jogar novamente? (s/n): \n");
			playAgain = sc.next().equalsIgnoreCase("s");
            
		} while (playAgain);
		
		System.out.println("Obrigado por jogar!");
        sc.close();
	}
	
	private static void cleanBoard() {
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	private static void showBoard() {
		System.out.println("  1 2 3");
		for(int i = 0; i < 3; i++) {
			System.out.print((i + 1) + " ");
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
				if(j < 2) {
					System.out.print("|");
				}
			}
			
			System.out.println();
			if(i < 2) {
				System.out.println("  -----");
			}
		}
	}
	
	private static void play(Scanner sc) {
		int linha, coluna;
		
		while(true) {
			
			if(jogadorAtual == 'X') {
				System.out.println("\n" + jogador1 + ", sua vez de jogar.\n");
			} else {
				System.out.println("\n" + jogador2 + ", sua vez de jogar.\n");
			}
			try {
				System.out.println("Linha: ");
				linha = sc.nextInt();
				linha--;
				
				System.out.println("coluna: ");
				coluna = sc.nextInt();
				coluna--;
				
				if(linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
					System.out.println("\nPosições inválidas.");
				} else if (board[linha][coluna] != ' ') {
					System.out.println("\nPosição já ocupada.");
				} else {
					board[linha][coluna] = jogadorAtual;
					break;
				}
			} catch(Exception e){
				System.out.println("Dado digitado invalido. Tente novamente: ");
				sc.next();
			}
		}
	}
	
	private static boolean victory() {
		for(int i = 0; i < 3; i++) {
			if(board[i][0] == jogadorAtual && board[i][1] == jogadorAtual && board[i][2] == jogadorAtual) {
				return true;
			}
			
			if(board[0][i] == jogadorAtual && board[1][i] == jogadorAtual && board[2][i] == jogadorAtual) {
                return true;
            }
		}
		
		if(board[0][0] == jogadorAtual && board[1][1] == jogadorAtual && board[2][2] == jogadorAtual) {
			return true;
		}
		
		if(board[0][2] == jogadorAtual && board[1][1] == jogadorAtual && board[2][0] == jogadorAtual) {
			return true;
		}
		
		return false;
	}
	
	private static boolean draw() {
		for(int i = 0; i < 3 ; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static void changePlayer() {
		jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
	}
}