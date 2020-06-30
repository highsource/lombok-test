package de.systel.streckengraph.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import de.db.stk.streckengraph.impl.StreckengraphLoader;
import de.db.stk.streckengraph.service.StreckengraphService;
import de.systel.streckenmatching.model.StreckenGraphVertex;

@RunWith(SpringRunner.class)
public class StreckengraphServiceTest {

	@Autowired
	private StreckengraphService sut;
	
	@Test
	public void findsByKuerzelUndStreckennummer() {
		final StreckenGraphVertex expectedVertex = new StreckenGraphVertex("B-3611-0-FSG", 20273, "FSG", 3611, 50.24190212649943, 8.646051137545857);
		final StreckenGraphVertex foundVertex = sut.findByStreckennummerUndKuerzel(3611, "FSG");

		assertThat(foundVertex).isNotNull();
		assertThat(foundVertex).isEqualTo(expectedVertex);
	}
	
	/*
	 * {
    "geometry" : {
      "type" : "Point",
      "coordinates" : [ 8.646051137545857, 50.24190212649943 ]
    },
    "properties" : {
      "id" : "B-3611-0-FSG",
      "kuerzel" : "FSG",
      "streckennummer" : 3611,
      "richtung" : 0,
      "kilometrierung" : 102270040
    },
    "type" : "Feature"
  }
	 */
	
	@Configuration
	@Import({ StreckengraphService.class, StreckengraphLoader.class })
	static class Config {
	}

}
