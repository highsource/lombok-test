package de.systel.streckengraph.model;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import de.db.stk.streckengraph.model.KuerzelAufStrecke;

public class KuerzelAufStreckeTest {

	
	@Test
	public void generatesConstructorAndGetters() {
		final KuerzelAufStrecke fsg0 = new KuerzelAufStrecke("FSG", 3611);
		final KuerzelAufStrecke fsg1 = new KuerzelAufStrecke("FSG", 3611);
		assertThat(fsg0).isEqualTo(fsg1);
		assertThat(fsg0.getKuerzel()).isEqualTo("FSG");
		assertThat(fsg0.getStreckennummer()).isEqualTo(3611);
		
	}
}
