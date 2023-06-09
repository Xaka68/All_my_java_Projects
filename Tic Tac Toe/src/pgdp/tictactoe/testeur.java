package pgdp.tictactoe;

public class testeur {

	public static void main(String[] args) {
		Field a = new Field(1, true);
		a = new Field(2, false);
		System.out.println(a.firstPlayer());
	}
}
