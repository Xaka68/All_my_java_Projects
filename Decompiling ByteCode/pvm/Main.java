package pgdp.pvm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Main {
	public static void main(String[] args) throws URISyntaxException, IOException {

		// every step on it's own
		new PVMParser(Files.lines(Path.of("src/pgdp/pvm/example2.jvm"))).parse().split().verify().optimize()
				.run(IO.system());

		// short form
		new PVMParser("test/example.jvm").run(IO.system());

	}
}
