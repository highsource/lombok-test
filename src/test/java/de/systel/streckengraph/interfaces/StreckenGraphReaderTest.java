package de.systel.streckengraph.interfaces;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

import de.systel.streckenmatching.impl.StreckenGraphReaderBuilder;
import de.systel.streckenmatching.interfaces.StreckenGraphReader;
import de.systel.streckenmatching.model.StreckenGraphEdge;
import de.systel.streckenmatching.model.StreckenGraphVertex;

public class StreckenGraphReaderTest {

	@Test
	public void readsData() throws IOException {
		StreckenGraphReaderBuilder builder = StreckenGraphReaderBuilder.getInstance();
		StreckenGraphReader reader = builder.build();
		Collection<StreckenGraphVertex> vertices = reader.vertexes();
		Collection<StreckenGraphEdge> edges = reader.edges();

		assertTrue(vertices.size() > 1000);
		System.out.println(vertices.size());
		assertTrue(edges.size() > 1000);
		System.out.println(edges.size());

	}
}
