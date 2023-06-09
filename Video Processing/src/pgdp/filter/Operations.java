
package pgdp.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import org.apache.commons.lang3.NotImplementedException;

public final class Operations {

	// Verstecke den Konstruktor einer utility KlasseS
	private Operations() {
	}

	public static Frame grayscale(Frame frame) {

		// TODO: Implementieren
		if (frame == null) {
			return null;
		}
		for (int y = 0; y < frame.getHeight(); y++) {
			for (int x = 0; x < frame.getWidth(); x++) {
				Color color = new Color(frame.getPixels().getRGB(x, y));
				int R = color.getRed();
				int G = color.getGreen();
				int B = color.getBlue();

				int a = (int) (0.299 * R + 0.587 * G + 0.114 * B);
				Color col = new Color(a, a, a);

				frame.getPixels().setRGB(x, y, col.getRGB());
			}
		}
		return new Frame(frame.getPixels(), frame.getFrameNumber());
	}

	public static int min(int a, int b) {
		return a <= b ? a : b;
	}

	public static int max(int a, int b) {
		return a >= b ? a : b;
	}

	public static Function<Frame, Frame> crop(int width, int height) {

		// TODO: Implementieren
		if (width <= 0 || height <= 0) {
			Function<Frame, Frame> function = n -> {
				return null;
			};
			return function;
		}

		Function<Frame, Frame> function = n -> {
			int a, b;
			b = (height - n.getHeight()) / 2;
			a = (width - n.getWidth()) / 2;
			if (a < 0) {
				a = -1 * a;
			}
			if (b < 0) {
				b = -1 * b;
			}

			Frame frame = new Frame(width, height, n.getFrameNumber());
			if (width >= n.getWidth() && height >= n.getHeight()) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						Color col = new Color(0, 0, 0);
						frame.getPixels().setRGB(x, y, col.getRGB());
					}

				}
				for (int x = 0; x < min(width, n.getWidth()); x++) {
					for (int y = 0; y < min(height, n.getHeight()); y++) {
						frame.getPixels().setRGB(x + a, y + b, n.getPixels().getRGB(x, y));
					}
				}
			} else if (width < n.getWidth() && height < n.getHeight()) {
				for (int x = 0; x < min(width, n.getWidth()); x++) {
					for (int y = 0; y < min(height, n.getHeight()); y++) {
						frame.getPixels().setRGB(x, y, n.getPixels().getRGB(x + a, y + b));
					}
				}

			} else if (width < n.getWidth() && height >= n.getHeight()) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						Color col = new Color(0, 0, 0);
						frame.getPixels().setRGB(x, y, col.getRGB());
					}

				}

				for (int x = 0; x < min(width, n.getWidth()); x++) {
					for (int y = 0; y < min(height, n.getHeight()); y++) {
						frame.getPixels().setRGB(x, y + b, n.getPixels().getRGB(x + a, y));
					}
				}
			} else if (width >= n.getWidth() && height < n.getHeight()) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						Color col = new Color(0, 0, 0);
						frame.getPixels().setRGB(x, y, col.getRGB());
					}

				}

				for (int x = 0; x < min(width, n.getWidth()); x++) {
					for (int y = 0; y < min(height, n.getHeight()); y++) {
						frame.getPixels().setRGB(x + a, y, n.getPixels().getRGB(x, y + b));
					}
				}
			}

			return frame;
		};

		return function;
	}

	public static int[] convertCharToBinaire(char a) {
		String s = Integer.toBinaryString(a);
		int[] n = new int[8];

		for (int i = 0; i < 8 - s.length(); i++) {
			n[i] = 0;
		}
		for (int i = 8 - s.length(); i < 8; i++) {
			if (s.charAt(i - (8 - s.length())) == '0') {
				n[i] = 0;
			} else if (s.charAt(i - (8 - s.length())) == '1') {
				n[i] = 1;
			}
		}

		return n;
	}

	public static Function<Frame, Frame> encode(String msg) {

		// TODO: Implementieren
		if (msg == null) {
			Function<Frame, Frame> function = n -> {
				return n;
			};
			return function;
		}

		char[] T = new char[msg.length()];
		for (int i = 0; i < T.length; i++) {
			T[i] = msg.charAt(i);
		}

		Function<Frame, Frame> function = n -> {

			int l = msg.length();
			int nombreCaracterParFrame = n.getWidth() / 8;
			if (nombreCaracterParFrame == 0) {
				return n;
			}
			if (nombreCaracterParFrame > 0) {
				int nombreFrameUsefull = l % nombreCaracterParFrame + 1;
				if (n.getFrameNumber() > nombreFrameUsefull - 1) {
					return n;
				}
			}
			int f = nombreCaracterParFrame;

			for (int k = n.getFrameNumber() * f; k < (n.getFrameNumber() + 1) * f; k++) {

				if (k < l) {
					int[] z = convertCharToBinaire(T[k]);
					int a = k - (n.getFrameNumber() * f);

					for (int i = 0; i < z.length; i++) {
						if (z[i] == 0) {

							n.getPixels().setRGB((a * 8) + i, n.getHeight() - 1, Color.black.getRGB());
						} else {

							n.getPixels().setRGB((a * 8) + i, n.getHeight() - 1, Color.WHITE.getRGB());

						}
					}

				}
			}
			return n;

		};

		return function;
	}

	public static String decode(Frame frame) {

		// TODO: Implementieren
		if (frame == null) {
			return null;
		}
		int f = frame.getWidth() / 8;
		int p = f * 8;
		String s = "";
		Color col = new Color(128, 128, 128);
		for (int i = 0; i < p; i++) {
			if (frame.getPixels().getRGB(i, frame.getHeight() - 1) > col.getRGB()) {
				s = s + "1";
			} else {
				s = s + "0";
			}
		}
		// convertir maintenant

		char[] T = new char[p];
		for (int i = 0; i < T.length; i++) {
			T[i] = s.charAt(i);
		}
		String[] nikx = new String[f];
		for (int i = 0; i < f; i++) {
			nikx[i] = "";
			for (int k = 0; k < 8; k++) {
				nikx[i] += T[k + (8 * i)];
			}
		}
		char[] l = new char[f];
		for (int i = 0; i < f; i++) {
			l[i] = (char) Integer.parseInt(nikx[i], 2);
		}
		String m = "";
		for (int i = 0; i < f; i++) {
			m = m + l[i];
		}

		return m;
	}

}
