package pgdp.trains.processing;

import pgdp.trains.connections.Station;

import pgdp.trains.connections.TrainConnection;
import pgdp.trains.connections.TrainStop;
import pgdp.trains.connections.TrainStop.Kind;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing {

	public static int compare(LocalDateTime a, LocalDateTime b) {
		if (a.equals(b)) {
			return 0;
		} else if (a.isAfter(b)) {
			return 1;
		} else {
			return -1;
		}
	}

	public static Stream<TrainConnection> cleanDataset(Stream<TrainConnection> connections) {
		// TODO Task 1.

		return connections.distinct()
				.sorted((a, b) -> compare(a.stops().get(0).scheduled(), b.stops().get(0).scheduled())).map(n -> {

					List<TrainStop> l = new ArrayList<>(n.stops());
					n.stops().stream().filter(i -> i.kind() == Kind.CANCELLED).peek(e -> l.remove(e)).count();

					return n.withUpdatedStops(l);
				});

	}

	public static TrainConnection worstDelayedTrain(Stream<TrainConnection> connections) {
		// TODO Task 2.
		try {
			return connections.sorted((a, b) -> {
				int max1 = a.stops().stream().takeWhile(q -> q != null).mapToInt(n -> n.getDelay()).sorted()
						.reduce((p, q) -> q).getAsInt();
				int max2 = b.stops().stream().takeWhile(q -> q != null).mapToInt(n -> n.getDelay()).sorted()
						.reduce((p, q) -> q).getAsInt();

				return Integer.compare(max1, max2);

			}).takeWhile(q -> q != null).reduce((p, q) -> q).get();

		} catch (Exception e) {
			return null;
		}

	}

	public static double percentOfKindStops(Stream<TrainConnection> connections, TrainStop.Kind kind) {
		// TODO Task 3.

		return connections.flatMap(n -> n.stops().stream()).mapToInt(n -> n.kind() == kind ? 1 : 0).summaryStatistics()
				.getAverage() * 100;

	}

	public static double averageDelayAt(Stream<TrainConnection> connections, Station station) {
		// TODO Task 4.
		try {
			return connections.flatMap(n -> n.stops().stream()).filter(a -> a.station().equals(station))
					.mapToInt(p -> p.getDelay()).average().getAsDouble();
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static double max(double a, double b) {
		return a >= b ? a : b;
	}

	public static Map<String, Double> delayComparedToTotalTravelTimeByTransport(Stream<TrainConnection> connections) {
		// TODO Task 5.

		Map<String, List<TrainConnection>> a = connections
				.collect(Collectors.groupingBy(TrainConnection -> TrainConnection.type()));
		Map<String, Double> map = a.entrySet().stream().collect(Collectors.toMap(b -> b.getKey(), c -> {
			List<TrainConnection> p = new ArrayList();
			p.addAll(c.getValue());
			double n = p.stream().mapToDouble(k -> max(k.totalTimeTraveledActual(), k.totalTimeTraveledScheduled()))
					.sum();
			double l = c.getValue().stream().mapToDouble(m -> m.totalTimeTraveledScheduled()).sum();
			double k = ((n - (double) l) / (double) n) * 100.0;
			if (Double.isNaN(k)) {
				return 0.0;
			} else {
				return k;
			}
		}));
		return map;

	}

	public static Map<Integer, Double> averageDelayByHour(Stream<TrainConnection> connections) {
		// TODO Task 6.
		Map<Integer, List<TrainStop>> a = connections.flatMap(n -> n.stops().stream())
				.collect(Collectors.groupingBy(TrainStop -> TrainStop.actual().getHour()));
		return a.entrySet().stream().collect(Collectors.toMap(b -> b.getKey(),
				c -> c.getValue().stream().mapToDouble(k -> k.getDelay()).average().getAsDouble()));

	}

	public static void main(String[] args) {
		// Um alle Verbindungen aus einer Datei zu lesen, verwendet
		// DataAccess.loadFile("path/to/file.json"), etwa:
		// Stream<TrainConnection> trainConnections =
		// DataAccess.loadFile("connections_test/fullDay.json");

		// Oder alternativ über die API, dies aber bitte sparsam verwenden, um die API
		// nicht zu überlasten.
		// Stream<TrainConnection> trainsMunich =
		// DataAccess.getDepartureBoardNowFor(Station.MUENCHEN_HBF);

		List<TrainConnection> trainConnections = List.of(
				new TrainConnection("ICE 1", "ICE", "1", "DB",
						List.of(new TrainStop(Station.MUENCHEN_HBF, LocalDateTime.of(2022, 12, 1, 10, 0),
								LocalDateTime.of(2022, 12, 1, 10, 0), TrainStop.Kind.REGULAR),
								new TrainStop(Station.NUERNBERG_HBF, LocalDateTime.of(2022, 12, 1, 17, 00),
										// 1 hour too early
										LocalDateTime.of(2022, 12, 1, 16, 00), TrainStop.Kind.REGULAR))),
				new TrainConnection("ICE 2", "ICE", "2", "DB",
						List.of(new TrainStop(Station.MUENCHEN_HBF, LocalDateTime.of(2022, 12, 1, 10, 0),
								LocalDateTime.of(2022, 12, 1, 10, 0), TrainStop.Kind.REGULAR),
								new TrainStop(Station.NUERNBERG_HBF, LocalDateTime.of(2022, 12, 1, 13, 0),
										// 1 hour delay
										LocalDateTime.of(2022, 12, 1, 14, 0), TrainStop.Kind.REGULAR))));

		List<TrainConnection> cleanDataset = cleanDataset(trainConnections.stream()).toList();

		// cleanDataset sollte sortiert sein: [ICE 1, ICE 2, ICE 3] und bei ICE 3 sollte
		// der Stopp in AUGSBURG_HBF
		// nicht mehr enthalten sein.

		System.out.println(cleanDataset.get(0).totalTimeTraveledActual());
		System.out.println(cleanDataset.get(1).totalTimeTraveledActual());

		TrainConnection worstDelayedTrain = worstDelayedTrain(trainConnections.stream());
		// worstDelayedTrain sollte ICE 3 sein. (Da der Stop in AUGSBURG_HBF mit 40
		// Minuten Verspätung am spätesten ist.)
		System.out.println(worstDelayedTrain.toString());

		double percentOfKindStops = percentOfKindStops(trainConnections.stream(), TrainStop.Kind.REGULAR);
		// percentOfKindStops REGULAR sollte 85.71428571428571 sein, CANCELLED
		// 14.285714285714285.
		System.out.println(percentOfKindStops);

		double averageDelayAt = averageDelayAt(trainConnections.stream(), Station.NUERNBERG_HBF);
		// averageDelayAt sollte 10.0 sein. (Da dreimal angefahren und einmal 30 Minuten
		// Verspätung).
		System.out.println(averageDelayAt);

		Map<String, Double> delayCompared = delayComparedToTotalTravelTimeByTransport(trainConnections.stream());
		// delayCompared sollte ein Map sein, die für ICE den Wert 16.666666666666668
		// hat.
		// Da ICE 2 0:30 geplant hatte, aber 1:00 gebraucht hat, ICE 1 0:30 geplant und
		// gebraucht hatte, und
		// ICE 3 1:30 geplant und gebraucht hat. Zusammen also 2:30 geplant und 3:00
		// gebraucht, und damit
		// (3:00 - 2:30) / 3:00 = 16.666666666666668.
		System.out.println(delayCompared.get("ICE"));
		Map<Integer, Double> averageDelayByHourOfDay = averageDelayByHour(trainConnections.stream());
		// averageDelayByHourOfDay sollte ein Map sein, die für 10, 11 den Wert 0.0 hat,
		// für 12 den Wert 15.0 und
		// für 13 den Wert 20.0.
		System.out.println(averageDelayByHourOfDay.get(13));

	}

}
