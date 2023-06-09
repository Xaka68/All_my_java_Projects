package pgdp.image;

import java.util.Arrays;

public class SeamCarving {

	public int computeGradientMagnitude(int v1, int v2) {

		String a = Integer.toHexString(v1);
		String b = Integer.toHexString(v2);

		char[] tab = new char[6];
		char[] tab2 = new char[6];

		for (int i = 0; i < 6; i++) {
			tab[i] = '0';

		}
		for (int i = 0; i < 6; i++) {
			tab2[i] = '0';

		}

		for (int i = 0; i < 6 && i < a.length(); i++) {
			tab[i] = a.charAt(a.length() - 1 - i);

		}
		String a0 = Character.toString(tab[1]) + Character.toString(tab[0]);
		String a1 = Character.toString(tab[3]) + Character.toString(tab[2]);
		String a2 = Character.toString(tab[5]) + Character.toString(tab[4]);

		for (int i = 0; i < 6 && i < b.length(); i++) {
			tab2[i] = b.charAt(b.length() - 1 - i);

		}
		String b0 = Character.toString(tab2[1]) + Character.toString(tab2[0]);
		String b1 = Character.toString(tab2[3]) + Character.toString(tab2[2]);
		String b2 = Character.toString(tab2[5]) + Character.toString(tab2[4]);

		int s = (Integer.parseInt(a0, 16) - Integer.parseInt(b0, 16))
				* (Integer.parseInt(a0, 16) - Integer.parseInt(b0, 16))
				+ (Integer.parseInt(a1, 16) - Integer.parseInt(b1, 16))
						* (Integer.parseInt(a1, 16) - Integer.parseInt(b1, 16))
				+ (Integer.parseInt(a2, 16) - Integer.parseInt(b2, 16))
						* (Integer.parseInt(a2, 16) - Integer.parseInt(b2, 16));

		return s;

	}

	public void toGradientMagnitude(int[] picture, int[] gradientMagnitude, int width, int height) {

		// creation du tableau qui contient les pixels de l'immage

		int[][] s = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s[i][j] = picture[j + i * width];
			}
		}

		int[][] grad = new int[height][width];

		// Calcul du gradient de chaque pixel

		for (int j = 0; j < s[0].length; j++) {

			grad[0][j] = Integer.MAX_VALUE;

		}
		for (int j = 0; j < s[height - 1].length; j++) {
			grad[height - 1][j] = Integer.MAX_VALUE;
		}
		for (int j = 1; j < s.length - 1; j++) {
			grad[j][0] = Integer.MAX_VALUE;
		}
		for (int j = 1; j < s.length - 1; j++) {
			grad[j][width - 1] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {
				grad[i][j] = computeGradientMagnitude(s[i + 1][j], s[i - 1][j])
						+ computeGradientMagnitude(s[i][j + 1], s[i][j - 1]);
			}
		}
		for (int i = 0; i < grad.length; i++) {
			for (int j = 0; j < grad[i].length; j++) {

				gradientMagnitude[j + i * width] = grad[i][j];
			}
		}
	}

	public void combineMagnitudeWithMask(int[] gradientMagnitude, int[] mask, int width, int height) {

		for (int i = 0; i < width * height; i++) {
			String a = Integer.toHexString(mask[i]);

			char[] tab = new char[6];

			for (int j = 0; j < 6; j++) {
				tab[j] = '0';

			}

			for (int k = 0; k < 6 && k < a.length(); k++) {
				tab[k] = a.charAt(a.length() - 1 - k);

			}
			String a0 = Character.toString(tab[1]) + Character.toString(tab[0]);
			String a1 = Character.toString(tab[3]) + Character.toString(tab[2]);
			String a2 = Character.toString(tab[5]) + Character.toString(tab[4]);

			if (Integer.parseInt(a0, 16) == 0 && Integer.parseInt(a1, 16) == 0 && Integer.parseInt(a2, 16) == 0) {

				gradientMagnitude[i] = Integer.MAX_VALUE;
			}
		}

	}

	public void buildSeams(int[][] seams, long[] seamWeights, int[] gradientMagnitude, int width, int height) {
		int[][] grad = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grad[i][j] = gradientMagnitude[j + i * width];
			}
		}
		for (int i = 0; i < width; i++) {
			seamWeights[i] = grad[0][i];
		}
		int[][] seam1 = new int[seams[0].length][seams.length];
		for (int i = 0; i < seams.length; i++) {
			seam1[0][i] = i;
		}

		for (int j = 0; j < seams.length; j++) {

			for (int i = 0; i < height - 1; i++) {

				if (seam1[i][j] == 0) {

					if (grad[i + 1][seam1[i][j]] <= grad[i + 1][seam1[i][j] + 1]) {
						seam1[i + 1][j] = seam1[i][j];

					} else {
						seam1[i + 1][j] = seam1[i][j] + 1;

					}

				} else if (seam1[i][j] == width - 1) {
					if (grad[i + 1][seam1[i][j] - 1] < grad[i + 1][seam1[i][j]]) {
						seam1[i + 1][j] = seam1[i][j] - 1;

					} else {
						seam1[i + 1][j] = seam1[i][j];

					}

				} else {
					if (grad[i + 1][seam1[i][j]] <= grad[i + 1][seam1[i][j] - 1]
							&& grad[i + 1][seam1[i][j]] <= grad[i + 1][seam1[i][j] + 1]) {

						seam1[i + 1][j] = seam1[i][j];

					} else if (grad[i + 1][seam1[i][j] - 1] == grad[i + 1][seam1[i][j] + 1]) {
						seam1[i + 1][j] = seam1[i][j] - 1;

					} else {
						if (grad[i + 1][seam1[i][j] - 1] < grad[i + 1][seam1[i][j]]
								&& grad[i + 1][seam1[i][j] - 1] < grad[i + 1][seam1[i][j] + 1]) {

							seam1[i + 1][j] = seam1[i][j] - 1;

						} else if (grad[i + 1][seam1[i][j] + 1] < grad[i + 1][seam1[i][j]]
								&& grad[i + 1][seam1[i][j] + 1] < grad[i + 1][seam1[i][j] - 1]) {

							seam1[i + 1][j] = seam1[i][j] + 1;

						} else {
							seam1[i + 1][j] = seam1[i][j];

						}
					}

				}
				seamWeights[j] += grad[i + 1][seam1[i + 1][j]];

			}
		}

		for (int i = 0; i < seams.length; i++) {
			for (int j = 0; j < seams[i].length; j++) {
				seams[i][j] = seam1[j][i];
			}
		}

	}

	public void removeSeam(int[] seam, int[] image, int height, int oldWidth) {

		int[][] s = new int[height][oldWidth];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < oldWidth; j++) {
				s[i][j] = image[j + i * oldWidth];
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < oldWidth; j++) {
				if (seam[i] == j) {
					for (int k = j; k < oldWidth - 1; k++) {
						s[i][k] = s[i][k + 1];
					}
				}
			}
		}

		// Mettre le resultat dans image

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < oldWidth - 1; j++) {
				image[j + i * (oldWidth - 1)] = s[i][j];
			}
		}

	}

	public int[] shrink(int[] image, int[] mask, int width, int height, int newWidth) {
		if (image.length == 0) {
			return new int[] {};
		} else {
			while (newWidth < width) {

				int[] gradientMagnitude = new int[height * width];

				toGradientMagnitude(image, gradientMagnitude, width, height);
				combineMagnitudeWithMask(gradientMagnitude, mask, width, height);

				int[][] seams = new int[width][height];
				long[] seamWeights = new long[width];

				buildSeams(seams, seamWeights, gradientMagnitude, width, height);
				// Trouver le seam minimal
				long min = seamWeights[0];
				int index = 0;

				for (int i = 0; i < width; i++) {
					if (min > seamWeights[i]) {
						min = seamWeights[i];
					}
				}

				for (int i = 0; i < width; i++) {
					if (min == seamWeights[i]) {
						index = i;
						break;
					}
				}

				int[] remseam = new int[height];

				for (int i = 0; i < height; i++) {
					remseam[i] = seams[index][i];

				}

				removeSeam(remseam, image, height, width);
				width--;

			}
			int[] finalimage = new int[height * width];

			for (int i = 0; i < height * width; i++) {

				finalimage[i] = image[i];

			}

			return finalimage;

		}
	}

}
