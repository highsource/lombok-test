package de.db.stk.streckengraph.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class KuerzelAufStrecke {

	
	private final String kuerzel;
	private final int streckennummer;
	
}
