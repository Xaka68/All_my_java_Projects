package pgdp.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pgdp.tictactoe.ai.HumanPlayer;

public class Game {
	private PenguAI first;
	private PenguAI second;
	public Field[][] board;
	private boolean firstPlayer;
	boolean[] firstPlayedPieces = new boolean[9];
	boolean[] secondPlayedPieces = new boolean[9];
	public static Boolean Alert = null;
	public static boolean nikx = false;
	public static PenguAI winner;
	public static Boolean alert;

	public Game(PenguAI first, PenguAI second) {
		winner = null;
		this.first = first;
		this.second = second;
		board = new Field[3][3];

		for (int i = 0; i < 9; i++) {
			firstPlayedPieces[i] = false;
			secondPlayedPieces[i] = false;
		}
	}

	public PenguAI getWinner() {

		if (firstCanNotPlayAnymore(firstPlayedPieces, board) == true) {
			if (secondCanNotPlayAnymore(secondPlayedPieces, board) == false) {
				return second;
			} else if (secondCanNotPlayAnymore(secondPlayedPieces, board) == true && nikx == true) {

				return first;
			} else if (secondCanNotPlayAnymore(secondPlayedPieces, board) == true && nikx == false) {
				return second;
			}
		}
		if (secondCanNotPlayAnymore(secondPlayedPieces, board) == true) {
			if (firstCanNotPlayAnymore(firstPlayedPieces, board) == false) {
				return first;
			} else if (firstCanNotPlayAnymore(firstPlayedPieces, board) == true && nikx == true) {
				return first;
			} else if (firstCanNotPlayAnymore(firstPlayedPieces, board) == true && nikx == false) {
				return second;
			}

		}

		if (firstCanNotPlayAnymore(firstPlayedPieces, board) == false
				&& secondCanNotPlayAnymore(secondPlayedPieces, board) == false) {
			if (Alert != null && Alert == true) {

				return first;
			} else if (Alert != null && Alert == false) {

				return second;
			}
		}

		// 1 er cas
		Boolean[] coche = new Boolean[3];

		for (int k = 0; k < 8; k++) {

			switch (k) {
			case 0:
				if (board[0][0] != null && board[0][1] != null && board[0][2] != null) {
					for (int i = 0; i < 3; i++) {

						coche[i] = board[0][i].firstPlayer();
					}
				}
				break;

			case 1:
				if (board[1][0] != null && board[1][1] != null && board[1][2] != null) {

					for (int i = 0; i < 3; i++) {
						coche[i] = board[1][i].firstPlayer();
					}
				}
				break;

			case 2:
				if (board[2][0] != null && board[2][1] != null && board[2][2] != null) {

					for (int i = 0; i < 3; i++) {
						coche[i] = board[2][i].firstPlayer();
					}
				}
				break;

			case 3:
				if (board[0][0] != null && board[1][0] != null && board[2][0] != null) {

					for (int i = 0; i < 3; i++) {
						coche[i] = board[i][0].firstPlayer();
					}
				}
				break;

			case 4:
				if (board[0][1] != null && board[1][1] != null && board[2][1] != null) {

					for (int i = 0; i < 3; i++) {
						coche[i] = board[i][1].firstPlayer();
					}
				}
				break;

			case 5:
				if (board[0][2] != null && board[1][2] != null && board[2][2] != null) {

					for (int i = 0; i < 3; i++) {
						coche[i] = board[i][2].firstPlayer();
					}
				}
				break;

			case 6:
				if (board[0][0] != null && board[1][1] != null && board[2][2] != null) {

					for (int i = 0; i < 3; i++) {
						coche[i] = board[i][i].firstPlayer();
					}
				}
				break;
			case 7:
				if (board[2][0] != null && board[1][1] != null && board[0][2] != null) {

					for (int i = 0; i < 3; i++) {
						coche[i] = board[i][2 - i].firstPlayer();
					}
				}
				break;

			}
			if (!contain1(coche, null)) {
				if (!contain1(coche, false)) {

					return first;
				} else if (!contain1(coche, true)) {

					return second;
				}
			}

		}

		return winner;
	}

	public static int minValuePlayedByFirst(Field[][] board) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] != null && board[i][j].firstPlayer() == true) {
					list.add(board[i][j].value());
				}
			}
		}

		return list.stream().min(Integer::compare).get();
	}

	public static int minValuePlayedBySecond(Field[][] board) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] != null && board[i][j].firstPlayer() == false) {
					list.add(board[i][j].value());
				}
			}
		}

		return list.stream().min(Integer::compare).get();
	}

	public static boolean contain1(Boolean[] a, Boolean b) {
		boolean p = false;

		for (int i = 0; i < a.length; i++) {
			if (a[i] == b) {
				p = true;
				break;
			}
		}
		return p;
	}

	public static boolean contain(boolean[] a, boolean b) {
		boolean p = false;

		for (int i = 0; i < a.length; i++) {
			if (a[i] == b) {
				p = true;
				break;
			}
		}
		return p;
	}

	public static boolean isFull(Field[][] board) {
		boolean isFull = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (board[i][j] == null) {
					isFull = false;
				}
			}
		}
		return isFull;
	}

	public static boolean firstCanNotPlayAnymore(boolean[] firstPlayedPieces, Field[][] board) {
		boolean p = true;
		if (!isFull(board)) {
			return false;
		}

		for (int i = 0; i < 9; i++) {
			if (firstPlayedPieces[i] == false && i > minValuePlayedBySecond(board)) {
				p = false;

			}
		}
		if (p == true) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean secondCanNotPlayAnymore(boolean[] secondPlayedPieces, Field[][] board) {
		boolean q = true;
		if (!isFull(board)) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
			if (secondPlayedPieces[i] == false && i > minValuePlayedByFirst(board)) {
				q = false;

			}
		}
		if (q == true) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkAlertFirst(Move a, Field[][] board, boolean[] firstPlayedPieces) {
		if ((board[a.x()][a.y()] != null && a.value() <= board[a.x()][a.y()].value())
				|| (board[a.x()][a.y()] != null && board[a.x()][a.y()].firstPlayer() == true)
				|| firstPlayedPieces[a.value()] == true || a.value() > 8 || a.x() > 2 || a.y() > 2 || a.x() < 0
				|| a.y() < 0 || a.value() < 0) {
			return true;
		}
		return false;
	}

	public static boolean checkAlertSecond(Move a, Field[][] board, boolean[] firstPlayedPieces) {
		if ((board[a.x()][a.y()] != null && a.value() <= board[a.x()][a.y()].value())
				|| (board[a.x()][a.y()] != null && board[a.x()][a.y()].firstPlayer() == true)
				|| firstPlayedPieces[a.value()] == true || a.value() > 8 || a.x() > 2 || a.y() > 2 || a.x() < 0
				|| a.y() < 0 || a.value() < 0) {
			return true;
		}
		return false;
	}

	public void playGame() {
		// tant que getWinner different to null et tous les pions n'ont pas encore ete
		// joues
		while (this.getWinner() == null && secondCanNotPlayAnymore(secondPlayedPieces, board) == false
				&& firstCanNotPlayAnymore(firstPlayedPieces, board) == false && contain(firstPlayedPieces, false)) {
			// cas ou le premier joueur peut jouer

			Move a = first.makeMove(board, true, firstPlayedPieces, secondPlayedPieces);

			if (Alert == null) {
				board[a.x()][a.y()] = new Field(a.value(), true);

				firstPlayedPieces[a.value()] = true;
			}
			if (secondCanNotPlayAnymore(secondPlayedPieces, board) == true) {
				nikx = true;
			}
			if (secondCanNotPlayAnymore(secondPlayedPieces, board) == false && this.getWinner() != first
					&& contain(secondPlayedPieces, false)) {

				Move b = second.makeMove(board, false, firstPlayedPieces, secondPlayedPieces);

				if (b.value() > 9 || b.x() > 3 || b.y() > 3 || b.x() < -1 || b.y() < -1 || b.value() < -1) {
					Alert = true;
				}
				if (Alert == null) {
					board[b.x()][b.y()] = new Field(b.value(), false);
					secondPlayedPieces[b.value()] = true;
				}
			}

		}

	}

	public static void printBoard(Field[][] board) {
		System.out.println("┏━━━┳━━━┳━━━┓");
		for (int y = 0; y < board.length; y++) {
			System.out.print("┃");
			for (int x = 0; x < board.length; x++) {
				if (board[x][y] != null) {
					System.out.print(board[x][y] + "┃");
				} else {
					System.out.print("   ┃");
				}
			}
			System.out.println();
			if (y != board.length - 1) {
				System.out.println("┣━━━╋━━━╋━━━┫");
			}
		}
		System.out.println("┗━━━┻━━━┻━━━┛");
	}

	public static void main(String[] args) {
		PenguAI firstPlayer = new HumanPlayer();
		PenguAI secondPlayer = new HumanPlayer();
		Game game = new Game(firstPlayer, secondPlayer);
		game.playGame();
		game.board[0][0] = new Field(5, true);
		game.board[1][1] = new Field(4, true);
		game.board[2][2] = new Field(0, true);
		game.board[2][0] = new Field(5, false);
		game.board[2][1] = new Field(0, true);
		game.board[0][2] = new Field(0, false);

		if (firstPlayer == game.getWinner()) {
			System.out.println("Herzlichen Glückwunsch erster Spieler");
		} else if (secondPlayer == game.getWinner()) {
			System.out.println("Herzlichen Glückwunsch zweiter Spieler");
		} else {
			System.out.println("Unentschieden");
		}
		printBoard(game.board);

	}
}
