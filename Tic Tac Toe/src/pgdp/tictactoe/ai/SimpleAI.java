package pgdp.tictactoe.ai;

import java.lang.reflect.Array;

import java.util.Arrays;

import pgdp.tictactoe.Field;
import pgdp.tictactoe.Move;
import pgdp.tictactoe.PenguAI;

public class SimpleAI extends PenguAI {

	public static int valeur = 0;
	public static Move winnMove;
	public static Move safeMove;

	public static boolean Jouable(Field w, boolean firstPlayer, boolean[] firstPlayedPieces) {
		boolean p = false;
		for (int i = 8; i >= 0; i--) {
			if ((w == null && firstPlayedPieces[i] == false)
					|| (firstPlayedPieces[i] == false && i > w.value() && w.firstPlayer() != firstPlayer)) {
				valeur = i;
				p = true;
				break;
			}
		}
		if (p == true) {
			return true;
		}
		return false;
	}

	public static boolean canWinn(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces) {
		// cas ou l'IA peut gagner
		for (int k = 0; k < 24; k++) {
			switch (k) {
			case 0:
				if (board[0][0] != null && board[0][0].firstPlayer() == firstPlayer && board[0][1] != null
						&& board[0][1].firstPlayer() == firstPlayer
						&& Jouable(board[0][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 2, valeur);
					return true;
				}
				break;
			case 1:
				if (board[0][0] != null && board[0][0].firstPlayer() == firstPlayer && board[0][2] != null
						&& board[0][2].firstPlayer() == firstPlayer
						&& Jouable(board[0][1], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 1, valeur);
					return true;
				}
				break;
			case 2:
				if (board[0][1] != null && board[0][1].firstPlayer() == firstPlayer && board[0][2] != null
						&& board[0][2].firstPlayer() == firstPlayer
						&& Jouable(board[0][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 0, valeur);
					return true;
				}
				break;
			case 3:
				if (board[1][0] != null && board[1][0].firstPlayer() == firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() == firstPlayer
						&& Jouable(board[1][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 2, valeur);
					return true;
				}
				break;
			case 4:
				if (board[1][0] != null && board[1][0].firstPlayer() == firstPlayer && board[0][2] != null
						&& board[0][2].firstPlayer() == firstPlayer
						&& Jouable(board[1][1], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 1, valeur);
					return true;
				}
				break;
			case 5:
				if (board[1][1] != null && board[1][1].firstPlayer() == firstPlayer && board[1][2] != null
						&& board[1][2].firstPlayer() == firstPlayer
						&& Jouable(board[1][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 0, valeur);
					return true;
				}
				break;
			case 6:
				if (board[2][0] != null && board[2][0].firstPlayer() == firstPlayer && board[2][1] != null
						&& board[2][1].firstPlayer() == firstPlayer
						&& Jouable(board[2][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 2, valeur);
					return true;
				}
				break;
			case 7:
				if (board[2][0] != null && board[2][0].firstPlayer() == firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() == firstPlayer
						&& Jouable(board[2][1], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 1, valeur);
					return true;
				}
				break;
			case 8:
				if (board[2][2] != null && board[2][2].firstPlayer() == firstPlayer && board[2][1] != null
						&& board[2][1].firstPlayer() == firstPlayer
						&& Jouable(board[2][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 0, valeur);
					return true;
				}
				break;
			case 9:
				if (board[0][0] != null && board[0][0].firstPlayer() == firstPlayer && board[1][0] != null
						&& board[1][0].firstPlayer() == firstPlayer
						&& Jouable(board[2][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 0, valeur);
					return true;
				}
				break;
			case 10:
				if (board[0][0] != null && board[0][0].firstPlayer() == firstPlayer && board[2][0] != null
						&& board[2][0].firstPlayer() == firstPlayer
						&& Jouable(board[1][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 0, valeur);
					return true;
				}
				break;
			case 11:
				if (board[2][0] != null && board[2][0].firstPlayer() == firstPlayer && board[1][0] != null
						&& board[1][0].firstPlayer() == firstPlayer
						&& Jouable(board[0][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 0, valeur);
					return true;
				}
				break;
			case 12:
				if (board[0][1] != null && board[0][1].firstPlayer() == firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() == firstPlayer
						&& Jouable(board[2][1], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 1, valeur);
					return true;
				}
				break;
			case 13:
				if (board[0][1] != null && board[0][1].firstPlayer() == firstPlayer && board[2][1] != null
						&& board[2][1].firstPlayer() == firstPlayer
						&& Jouable(board[1][1], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 1, valeur);
					return true;
				}
				break;
			case 14:
				if (board[2][1] != null && board[2][1].firstPlayer() == firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() == firstPlayer
						&& Jouable(board[0][1], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 1, valeur);
					return true;
				}
				break;
			case 15:
				if (board[0][2] != null && board[0][2].firstPlayer() == firstPlayer && board[1][2] != null
						&& board[1][2].firstPlayer() == firstPlayer
						&& Jouable(board[2][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 2, valeur);
					return true;
				}
				break;
			case 16:
				if (board[0][2] != null && board[0][2].firstPlayer() == firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() == firstPlayer
						&& Jouable(board[1][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 2, valeur);
					return true;
				}
				break;
			case 17:
				if (board[2][2] != null && board[2][2].firstPlayer() == firstPlayer && board[1][2] != null
						&& board[1][2].firstPlayer() == firstPlayer
						&& Jouable(board[0][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 2, valeur);
					return true;
				}
				break;
			case 18:
				if (board[0][0] != null && board[0][0].firstPlayer() == firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() == firstPlayer
						&& Jouable(board[2][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 2, valeur);
					return true;
				}
				break;
			case 19:
				if (board[0][0] != null && board[0][0].firstPlayer() == firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() == firstPlayer
						&& Jouable(board[0][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 1, valeur);
					return true;
				}
				break;
			case 20:
				if (board[1][1] != null && board[1][1].firstPlayer() == firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() == firstPlayer
						&& Jouable(board[0][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 0, valeur);
					return true;
				}
				break;
			case 21:
				if (board[2][0] != null && board[2][0].firstPlayer() == firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() == firstPlayer
						&& Jouable(board[0][2], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(0, 2, valeur);
					return true;
				}
				break;
			case 22:
				if (board[0][2] != null && board[0][2].firstPlayer() == firstPlayer && board[2][0] != null
						&& board[2][0].firstPlayer() == firstPlayer
						&& Jouable(board[1][1], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(1, 1, valeur);
					return true;
				}
				break;
			case 23:
				if (board[1][1] != null && board[1][1].firstPlayer() == firstPlayer && board[0][2] != null
						&& board[0][2].firstPlayer() == firstPlayer
						&& Jouable(board[2][0], firstPlayer, firstPlayedPieces)) {
					winnMove = new Move(2, 0, valeur);
					return true;
				}
				break;

			default:
				return false;
			}

		}

		return false;
	}

	public static boolean canÜberschreiben(boolean[] firstPlayedPieces, Field a) {
		boolean p = false;
		for (int i = firstPlayedPieces.length - 1; i > 0; i--) {
			if (firstPlayedPieces[i] == false && i > a.value()) {
				valeur = i;
				p = true;
				break;
			}
		}
		return p;

	}

	public static boolean canAvoidALos(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces) {
		for (int k = 0; k < 24; k++) {
			switch (k) {
			case 0:
				if (board[0][0] != null && board[0][0].firstPlayer() != firstPlayer && board[0][1] != null
						&& board[0][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][0])) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][1])) {
						safeMove = new Move(0, 1, valeur);
						return true;
					}
				}
				break;
			case 1:
				if (board[0][0] != null && board[0][0].firstPlayer() != firstPlayer && board[0][2] != null
						&& board[0][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][0])) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][2])) {
						safeMove = new Move(0, 2, valeur);
						return true;
					}
				}
				break;
			case 2:
				if (board[0][1] != null && board[0][1].firstPlayer() != firstPlayer && board[0][2] != null
						&& board[0][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][1])) {
						safeMove = new Move(0, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][2])) {
						safeMove = new Move(0, 2, valeur);
						return true;
					}
				}
				break;
			case 3:
				if (board[1][0] != null && board[1][0].firstPlayer() != firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][0])) {
						safeMove = new Move(1, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					}
				}
				break;
			case 4:
				if (board[1][0] != null && board[1][0].firstPlayer() != firstPlayer && board[1][2] != null
						&& board[1][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][0])) {
						safeMove = new Move(1, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][2])) {
						safeMove = new Move(1, 2, valeur);
						return true;
					}
				}
				break;
			case 5:
				if (board[1][1] != null && board[1][1].firstPlayer() != firstPlayer && board[1][2] != null
						&& board[1][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][2])) {
						safeMove = new Move(1, 2, valeur);
						return true;
					}
				}
				break;
			case 6:
				if (board[2][0] != null && board[2][0].firstPlayer() != firstPlayer && board[2][1] != null
						&& board[2][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][0])) {
						safeMove = new Move(2, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][1])) {
						safeMove = new Move(2, 1, valeur);
						return true;
					}
				}
				break;
			case 7:
				if (board[2][0] != null && board[2][0].firstPlayer() != firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][0])) {
						safeMove = new Move(2, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][2])) {
						safeMove = new Move(2, 2, valeur);
						return true;
					}
				}
				break;
			case 8:
				if (board[2][2] != null && board[2][2].firstPlayer() != firstPlayer && board[2][1] != null
						&& board[2][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][2])) {
						safeMove = new Move(2, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][1])) {
						safeMove = new Move(2, 1, valeur);
						return true;
					}
				}
				break;
			case 9:
				if (board[0][0] != null && board[0][0].firstPlayer() != firstPlayer && board[1][0] != null
						&& board[1][0].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][0])) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][0])) {
						safeMove = new Move(1, 0, valeur);
						return true;
					}
				}
				break;
			case 10:
				if (board[0][0] != null && board[0][0].firstPlayer() != firstPlayer && board[2][0] != null
						&& board[2][0].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][0])) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(2, 0, valeur);
						return true;
					}
				}
				break;
			case 11:
				if (board[2][0] != null && board[2][0].firstPlayer() != firstPlayer && board[1][0] != null
						&& board[1][0].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][0])) {
						safeMove = new Move(1, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][0])) {
						safeMove = new Move(2, 0, valeur);
						return true;
					}
				}
				break;
			case 12:
				if (board[0][1] != null && board[0][1].firstPlayer() != firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][1])) {
						safeMove = new Move(0, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					}
				}
				break;
			case 13:
				if (board[0][1] != null && board[0][1].firstPlayer() != firstPlayer && board[2][1] != null
						&& board[2][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][1])) {
						safeMove = new Move(0, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][1])) {
						safeMove = new Move(2, 1, valeur);
						return true;
					}
				}
				break;
			case 14:
				if (board[2][1] != null && board[2][1].firstPlayer() != firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][1])) {
						safeMove = new Move(2, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					}
				}
				break;
			case 15:
				if (board[0][2] != null && board[0][2].firstPlayer() != firstPlayer && board[1][2] != null
						&& board[1][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][2])) {
						safeMove = new Move(0, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][2])) {
						safeMove = new Move(1, 2, valeur);
						return true;
					}
				}
				break;
			case 16:
				if (board[0][2] != null && board[0][2].firstPlayer() != firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][2])) {
						safeMove = new Move(0, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][2])) {
						safeMove = new Move(2, 2, valeur);
						return true;
					}
				}
				break;
			case 17:
				if (board[2][2] != null && board[2][2].firstPlayer() != firstPlayer && board[1][2] != null
						&& board[1][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][2])) {
						safeMove = new Move(2, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][2])) {
						safeMove = new Move(1, 2, valeur);
						return true;
					}
				}
				break;
			case 18:
				if (board[0][0] != null && board[0][0].firstPlayer() != firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][0])) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					}
				}
				break;
			case 19:
				if (board[0][0] != null && board[0][0].firstPlayer() != firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][0])) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][2])) {
						safeMove = new Move(2, 2, valeur);
						return true;
					}
				}
				break;
			case 20:
				if (board[1][1] != null && board[1][1].firstPlayer() != firstPlayer && board[2][2] != null
						&& board[2][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][2])) {
						safeMove = new Move(2, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					}
				}
				break;
			case 21:
				if (board[2][0] != null && board[2][0].firstPlayer() != firstPlayer && board[1][1] != null
						&& board[1][1].firstPlayer() != firstPlayer) {
					if (Jouable(board[0][2], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(0, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][0])) {
						safeMove = new Move(2, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					}
				}
				break;
			case 22:
				if (board[0][2] != null && board[0][2].firstPlayer() != firstPlayer && board[2][0] != null
						&& board[2][0].firstPlayer() != firstPlayer) {
					if (Jouable(board[1][1], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(1, 1, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[2][0])) {
						safeMove = new Move(2, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][2])) {
						safeMove = new Move(0, 2, valeur);
						return true;
					}
				}
				break;
			case 23:
				if (board[1][1] != null && board[1][1].firstPlayer() != firstPlayer && board[0][2] != null
						&& board[0][2].firstPlayer() != firstPlayer) {
					if (Jouable(board[2][0], firstPlayer, firstPlayedPieces)) {
						safeMove = new Move(2, 0, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[0][2])) {
						safeMove = new Move(0, 2, valeur);
						return true;
					} else if (canÜberschreiben(firstPlayedPieces, board[1][1])) {
						safeMove = new Move(1, 1, valeur);
						return true;
					}
				}
				break;

			default:
				return false;
			}

		}

		return false;
	}

	@Override
	public Move makeMove(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces,
			boolean[] secondPlayedPieces) {
		// cas ou cet AI est le premier joueur
		if (firstPlayer == true) {
			if (canWinn(board, firstPlayer, firstPlayedPieces)) {
				return winnMove;
			} else if (canAvoidALos(board, firstPlayer, firstPlayedPieces)) {
				return safeMove;
			} else {

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (Jouable(board[i][j], firstPlayer, firstPlayedPieces)) {
							return new Move(i, j, valeur);
						}
					}
				}
				return null;
			}
		} else {
			if (canWinn(board, firstPlayer, secondPlayedPieces)) {
				return winnMove;
			} else if (canAvoidALos(board, firstPlayer, secondPlayedPieces)) {
				return safeMove;
			} else {

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (Jouable(board[i][j], firstPlayer, secondPlayedPieces)) {
							return new Move(i, j, valeur);
						}
					}
				}
				return null;
			}

		}

	}
}
