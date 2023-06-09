package pgdp.pvm;

public class PVMExecutable {

	private final BasicBlock entryPoint;
	private final int localVariables;
	private final int maxStack;

	public PVMExecutable(BasicBlock entryPoint, int localVariables, int maxStack) {
		this.entryPoint = entryPoint;
		this.localVariables = localVariables;
		this.maxStack = maxStack;
	}

	/**
	 * Simuliert das nun verifizierte und optimierte Bytecode-Programm. Wie genau
	 * die Nutzereingaben und die Ausgaben funktionieren sollen, wird dabei durch
	 * das übergebene Objekt 'io' beschrieben (io.read() für eine Eingabe, io.write
	 * für eine Ausgabe).
	 *
	 * @param io Implementierung von IO für Eingaben (READ) und Ausgaben (WRITE).
	 */
	public void run(IO io) {
		// Beginne mit der Simulation bei dem 'entryPoint'
		BasicBlock currentBlock = entryPoint;
		// Im Verifikationsschritt wurde festgehalten, auf welche Größe der Stack
		// maximal wachsen kann.
		// Lege nun genau so viele Felder auf dem Stack an.
		int[] stack = new int[maxStack];
		// Lege zudem ausreichend Platz für die Variablen an (Simulation von ALLOC).
		int[] variables = new int[localVariables];
		// Speichere stets, an welchem Index in 'stack' das aktuell oberste
		// Stack-Element liegt.
		// Da anfangs noch nichts auf dem Stack liegt, ist dieser Wert erstmal -1.
		int stackPtr = -1;
		// Speichere, ob (durch einen HALT-Befehl) das Programm angehalten wurde.
		boolean halted = false;

		// Solange nicht angehalten wurde, führe die Simulation fort.
		while (!halted) {
			// Merke stets, ob gesprungen wurde, um am Ende, falls nicht, einfach in den
			// nächsten Block übergehen zu können.
			boolean jumped = false;

			// Simuliere jede Instruktion in dem aktuellen Block nacheinander
			for (Instruction instruction : currentBlock.getInstructions()) {
				switch (instruction.type) {
				// Arithmetische Operationen
				case ADD -> {
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] + stack[stackPtr + 1];

				}
				case SUB -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] - stack[stackPtr + 1];

				}
				case MUL -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] * stack[stackPtr + 1];

				}
				case DIV -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] / stack[stackPtr + 1];

				}
				case MOD -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] % stack[stackPtr + 1];

				}
				case NEG -> {
					// TODO

					stack[stackPtr] = -1 * stack[stackPtr];

				}

				// Boolesche Operationen
				case AND -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] * stack[stackPtr + 1];

				}
				case OR -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] + stack[stackPtr + 1];
					if (stack[stackPtr] == 2) {
						stack[stackPtr] = 1;
					}

				}
				case NOT -> {
					// TODO
					if (stack[stackPtr] == 0) {
						stack[stackPtr] = 1;
					} else if (stack[stackPtr] == 1) {
						stack[stackPtr] = 0;
					}

				}

				// Vergleichs-Operationen
				case LESS -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] < stack[stackPtr + 1] ? 1 : 0;

				}
				case LEQ -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] <= stack[stackPtr + 1] ? 1 : 0;

				}
				case EQ -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] == stack[stackPtr + 1] ? 1 : 0;

				}
				case NEQ -> {
					// TODO
					stackPtr--;
					stack[stackPtr] = stack[stackPtr] != stack[stackPtr + 1] ? 1 : 0;

				}

				// IO-Befehle
				case READ -> {
					// TODO
					stackPtr++;
					stack[stackPtr] = io.read();

				}
				case WRITE -> {
					// TODO
					io.write(stack[stackPtr]);
					stackPtr--;

				}

				// Konstanten
				case CONST -> {
					// TODO
					stackPtr++;
					IntInstruction instruct = (IntInstruction) instruction;
					stack[stackPtr] = instruct.value;

				}
				case TRUE -> {
					// TODO
					stackPtr++;
					stack[stackPtr] = 1;

				}
				case FALSE -> {
					// TODO
					stackPtr++;
					stack[stackPtr] = 0;

				}

				// Laden/Speichern
				case LOAD -> {
					// TODO
					stackPtr++;
					IntInstruction instruct = (IntInstruction) instruction;
					stack[stackPtr] = variables[instruct.value];

				}
				case STORE -> {
					// TODO

					IntInstruction instruct = (IntInstruction) instruction;
					variables[instruct.value] = stack[stackPtr];
					stackPtr--;
				}

				// Stack-Befehle
				case POP -> {
					// TODO
					stackPtr--;

				}
				case DUP -> {
					// TODO
					int a = stack[stackPtr];
					stackPtr++;
					stack[stackPtr] = a;

				}
				case SWAP -> {
					// TODO

					int a = stack[stackPtr];
					stack[stackPtr] = stack[stackPtr - 1];
					stack[stackPtr - 1] = a;

				}

				// Kontrollfluss-Befehle
				case JUMP -> {
					// TODO
					jumped = true;

				}
				case FJUMP -> {
					// TODO
					if (stack[stackPtr] == 0) {
						jumped = true;

					}
					stackPtr--;
				}
				case HALT -> {
					// TODO
					halted = true;
				}
				}
			}

			// Wenn nicht in einen anderen Block gesprungen oder angehalten wurde, gehe
			// einfach zum nächsten Block weiter.
			if (!jumped && !halted) {
				currentBlock = currentBlock.next;
			}
		}
	}

	public BasicBlock getEntryPoint() {
		return entryPoint;
	}
}
