package de.systel.streckengraph;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.db.stk.streckengraph.StreckengraphServiceApplication;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { StreckengraphServiceApplication.class })
@ActiveProfiles(profiles = "none")
public class StreckengraphServiceApplicationTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void receivesActuatorHealth() throws Exception {
		mockMvc.perform(get("/actuator/health")).andExpect(jsonLenient("{\"status\":\"UP\"}"));
	}
/*
	@Test
	public void receivesVideoTrack() throws Exception {
		mockMvc.perform(get("/video-track/20190619-RDZ-RBKR-40021")).andExpect(status().isOk());
	}

	@Test
	public void receivesVideoTrackRegularized() throws Exception {
		mockMvc.perform(get("/video-track/20190619-RDZ-RBKR-40021?regularize=false")).andExpect(status().isOk());
	}

	@Test
	public void receivesVideoTrackNonRegularized() throws Exception {
		mockMvc.perform(get("/video-track/20190619-RDZ-RBKR-40021?regularize=true")).andExpect(status().isOk());
	}
*/
	private static ResultMatcher jsonLenient(final String jsonContent) {
		return result -> JSONAssert.assertEquals(jsonContent, result.getResponse().getContentAsString(),
				JSONCompareMode.LENIENT);
	}
}
