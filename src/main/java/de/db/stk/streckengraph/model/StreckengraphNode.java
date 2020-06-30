package de.db.stk.streckengraph.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.db.stk.streckenkilometer.strecke.model.StreckeKilometrierung;
import de.db.systel.stk.commons.feature.model.Feature;
import de.db.systel.stk.commons.feature.model.Point;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public class StreckengraphNode extends Feature<Point, double[], StreckengraphNode.Properties> {

	@JsonCreator
	public StreckengraphNode(@JsonProperty("geometry") Point geometry,
			@JsonProperty("properties") Properties properties, @JsonProperty("type") String ignoreType) {
		super(geometry, properties);
	}

	public StreckengraphNode(Point geometry, Properties properties) {
		this(geometry, properties, null);
	}

	@AllArgsConstructor
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class Properties {

		private String id;

		private String kuerzel;

		private final int streckennummer;

		private final int richtung;

		private final int kilometrierung;

		public static final Properties fromVertexProperties(StreckengraphVertex.Properties p) {

			final String kuerzel = p.getKuerzel();
			final StreckeKilometrierung kilometrierung = p.getKilometrierung();
			final int streckennummer = kilometrierung == null ? 0 : kilometrierung.getStreckennummer();
			final int richtung = kilometrierung == null ? 0 : kilometrierung.getRichtung();
			final int m = kilometrierung == null || kilometrierung.getKilometrierung() == null ? 0
					: kilometrierung.getKilometrierung().getKilometrierung();

			final String id = p.calculateId();
			return new Properties(id, kuerzel, streckennummer, richtung, m);
		}
	}

	public static final StreckengraphNode fromVertex(StreckengraphVertex v) {
		return new StreckengraphNode(v.getGeometry(), Properties.fromVertexProperties(v.getProperties()));
	}
}