package pgdp.casino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

	public static void penguBlackJack() {

		// Here is a card deck for your games :)
		// Remember for testing you can use seeds:
		// CardDeck deck = CardDeck.getDeck(420);
		CardDeck deck = CardDeck.getDeck();

		System.out.println("Welcome to Pengu-BlackJack!");

		int kontostand = 1000;
		int input = 1;
		while (input == 1) {
			System.out.println("(1) Start a game or (2) exit");
			input = readInt();
			while (input != 1 && input != 2) {
				System.out.println("What?!");
				System.out.println("(1) Start a game or (2) exit");
				input = readInt();
			}
			if (input == 1) {
				System.out.println("Your current balance: " + kontostand);
				System.out.println("How much do you want to bet?");
				int bet = readInt();
				System.out.println("Player cards:");
				int punktzahl = deck.drawCard();
				int answer = 1;
				int i = 1;
				System.out.println(i + " : " + punktzahl);
				while (answer == 1 && punktzahl <= 20) {

					int card = deck.drawCard();
					punktzahl = punktzahl + card;
					System.out.println(i + " : " + card);
					i++;
					System.out.println("Current standing: " + punktzahl);
					if (punktzahl <= 20) {
						System.out.println("(1) draw another card or (2) stay");
						answer = readInt();
					}
					while (answer != 1 && answer != 2) {
						System.out.println("What?!");
						System.out.println("(1) draw another card or (2) stay");
						answer = readInt();

					}

				}

				if (punktzahl > 21) {
					System.out.println("You lost " + bet + " tokens.");
					kontostand = kontostand - bet;
				} else if (punktzahl == 21) {
					System.out.println("Blackjack! You won " + bet + " tokens.");
					kontostand = kontostand + bet;
				} else {
					System.out.println("Dealer cards:");
					int punkzahldealer = 0;
					int j = 1;
					while (punkzahldealer < punktzahl) {
						int card = deck.drawCard();
						System.out.println(j + " : " + card);
						j++;
						punkzahldealer = punkzahldealer + card;
					}
					System.out.println("Dealer: " + punkzahldealer);

					if (punkzahldealer > 21) {
						System.out.println("You won " + bet + " tokens.");
						kontostand = kontostand + bet;
					} else {
						System.out.println("Dealer wins. You lost " + bet + " tokens.");
						kontostand = kontostand - bet;

					}
				}
			}
			if (kontostand == 0) {
				System.out.println("Sorry, you are broke. Better Luck next time.");
				input = 2;
			}
		}
		if (input == 2) {
			System.out.println("Your final balance: " + kontostand);
			if (kontostand > 1000) {
				System.out.println("Wohooo! Ez profit! :D");
			} else {
				System.out.println("That's very very sad :(");
			}
			System.out.println("Thank you for playing. See you next time.");

		}

	}

	public static String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int readInt() {
		while (true) {
			String input = readString();
			try {
				return Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("This was not a valid number, please try again.");
			}
		}
	}

	public static void main(String[] args) {
		penguBlackJack();
	}

}
