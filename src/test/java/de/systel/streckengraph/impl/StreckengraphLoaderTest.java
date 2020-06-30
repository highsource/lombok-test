package de.systel.streckengraph.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.jgrapht.Graph;
import org.junit.Test;

import de.db.stk.streckengraph.impl.StreckengraphLoader;
import de.systel.streckenmatching.model.StreckenGraphEdge;
import de.systel.streckenmatching.model.StreckenGraphVertex;

public class StreckengraphLoaderTest {

	@Test
	public void loadsStreckengraph() throws IOException {
		final StreckengraphLoader loader = new StreckengraphLoader();
		
		Graph<StreckenGraphVertex, StreckenGraphEdge> graph = loader.loadGraph();
		assertThat(graph).isNotNull();
		assertThat(graph.vertexSet()).hasSize(24304);
		assertThat(graph.edgeSet()).hasSize(26967);
	}
}
