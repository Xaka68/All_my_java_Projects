package pgdp.filter;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.NotImplementedException;

public class VideoContainer {

	private FrameProvider provider;

	public Stream<Frame> frameStream;

	/**
	 * Nutzt javacv um Videodatei darzustellen.
	 * 
	 * @throws FileNotFoundException
	 * @throws IllegalVideoFormatException
	 */
	public VideoContainer(FrameProvider fp) throws FileNotFoundException, IllegalVideoFormatException {
		// TODO: Implementieren

		provider = fp;

		if (!fp.fileExists()) {
			throw new FileNotFoundException();
		}
		if (provider.getHeight() == 0 || provider.getWidth() == 0) {
			throw new IllegalVideoFormatException();
		}
		frameStream = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(new FrameIterator(fp), Spliterator.ORDERED), false);

	}

	/**
	 * TODO: zu implementieren Appliziert Funktion auf den Frame Stream
	 *
	 * @param checkFunction
	 */
	public void applyFunc(Function<Frame, Frame> function) {

		// TODO: Implementieren

		frameStream = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(new FrameIterator(provider), Spliterator.ORDERED), false)
				.map(function);

	}

	public void limit(long frames) {

		// TODO: Implementieren
		frameStream = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(new FrameIterator(provider), Spliterator.ORDERED), false)
				.limit(frames);

	}

	public FrameProvider getProvider() {
		return provider;
	}

	public void setProvider(FrameProvider provider) {
		this.provider = provider;
	}

	public Stream<Frame> getFrameStream() {
		return frameStream;
	}

	public void write(FrameConsumer fc) throws org.bytedeco.javacv.FrameRecorder.Exception {

		// TODO: Implementieren
		try {
			frameStream.forEach(n -> {
				try {
					fc.consume(n);
				} catch (org.bytedeco.javacv.FFmpegFrameRecorder.Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		fc.close();

	}

	private class FrameIterator implements Iterator<Frame> {
		private FrameProvider provider;
		private Frame nextFrame;

		public FrameIterator(FrameProvider provider) {

			this.provider = provider;

			if (provider == null) {
				throw new NoSuchElementException();
			}

		}

		@Override
		public boolean hasNext() {
			// TODO: Implementieren
			try {

			} catch (Exception e) {
				throw new NoSuchElementException();

			}
			return nextFrame != null;

		}

		@Override
		public Frame next() {
			// TODO: Implementieren
			try {
				return this.provider.nextFrame();
			} catch (org.bytedeco.javacv.FFmpegFrameGrabber.Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
