package de.db.stk.streckengraph.model;

import java.util.Objects;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.db.stk.streckenkilometer.strecke.model.StreckeKilometrierung;
import de.db.systel.stk.commons.feature.model.Feature;
import de.db.systel.stk.commons.feature.model.Point;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public class StreckengraphVertex extends Feature<Point, double[], StreckengraphVertex.Properties> {

	@JsonCreator
	public StreckengraphVertex(@JsonProperty("geometry") Point geometry,
			@JsonProperty("properties") Properties properties, @JsonProperty("type") String ignoreType) {
		super(geometry, properties);
	}

	public StreckengraphVertex(Point geometry, Properties properties) {
		this(geometry, properties, null);
	}

	public StreckengraphVertex(Coordinate coordinate) {
		this(new Point(coordinate), new Properties(null, null));
	}

	@AllArgsConstructor
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class Properties {
		private String kuerzel;
		private StreckeKilometrierung kilometrierung;

		public String calculateId() {
			final String id;
			final int streckennummer = kilometrierung == null ? 0 : kilometrierung.getStreckennummer();
			final int richtung = kilometrierung == null ? 0 : kilometrierung.getRichtung();
			final int m = kilometrierung == null || kilometrierung.getKilometrierung() == null ? 0
					: kilometrierung.getKilometrierung().getKilometrierung();
			if (kuerzel != null) {
				id = "B-" + streckennummer + "-" + richtung + "-" + kuerzel.replace(' ', '_');
			} else {
				id = "S-" + streckennummer + "-" + richtung + "-" + m;
			}
			return id;
		}
	}

	public static StreckengraphVertex forStreckengraphEdgeKilometrierung(Coordinate coordinate,
			StreckeKilometrierung kilometrierung) {
		Objects.requireNonNull(kilometrierung, "kilometrierung must not be null");
		final Properties properties = new Properties(null, kilometrierung);
		return new StreckengraphVertex(new Point(coordinate), properties);
	}
}