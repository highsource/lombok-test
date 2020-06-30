package de.db.stk.streckengraph.model;

import org.jgrapht.Graph;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.db.stk.streckenkilometer.strecke.model.StreckeKilometrierung;
import de.db.systel.stk.commons.feature.model.Feature;
import de.db.systel.stk.commons.feature.model.LineString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class StreckengraphLink extends Feature<LineString, double[][], StreckengraphLink.Properties> {

	@JsonCreator
	public StreckengraphLink(@JsonProperty("geometry") LineString lineString,
			@JsonProperty("properties") Properties properties, @JsonProperty("type") String ignoreType) {
		super(lineString, properties);
	}

	public StreckengraphLink(LineString lineString, Properties properties) {
		this(lineString, properties, null);
	}

	@RequiredArgsConstructor
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class Properties {
		private final String id;
		private final String sourceNodeId;
		private final String targetNodeId;
		private final String kuerzel;
		private final Integer streckennummer;
		private final Integer richtung;
		private final Integer von;
		private final Integer bis;
	}
}