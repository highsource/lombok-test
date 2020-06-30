package de.db.stk.streckengraph.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.springframework.stereotype.Service;

import de.db.stk.streckengraph.jgrapht.graph.DefaultUndirectedMultigraph;
import de.systel.streckenmatching.impl.StreckenGraphReaderBuilder;
import de.systel.streckenmatching.interfaces.StreckenGraphReader;
import de.systel.streckenmatching.model.StreckenGraphEdge;
import de.systel.streckenmatching.model.StreckenGraphVertex;

@Service
public class StreckengraphLoader {

	private StreckenGraphReader reader;

	public StreckengraphLoader() throws IOException {
		StreckenGraphReaderBuilder builder = StreckenGraphReaderBuilder.getInstance();
		reader = builder.build();
	}

	public Graph<StreckenGraphVertex, StreckenGraphEdge> loadGraph() {
		final Graph<StreckenGraphVertex, StreckenGraphEdge> graph = new DefaultUndirectedMultigraph<StreckenGraphVertex, StreckenGraphEdge>();
		
		Collection<StreckenGraphVertex> vertices = reader.vertexes();
		
		final Map<String, StreckenGraphVertex> vertexById = vertices.stream().collect(Collectors.toMap(StreckenGraphVertex::getId, Function.identity()));
		
		Collection<StreckenGraphEdge> edges = reader.edges();
		
		vertices.forEach(vertex -> {
			graph.addVertex(vertex);
		});
		
		edges.forEach(edge -> {
			String firstNodeId = edge.getFirstNodeId();
			StreckenGraphVertex sourceVertex = vertexById.get(firstNodeId);
			String lastNodeId = edge.getLastNodeId();
			StreckenGraphVertex targetVertex = vertexById.get(lastNodeId);
			graph.addEdge(sourceVertex, targetVertex, edge);
		});

		return graph;
	}
}