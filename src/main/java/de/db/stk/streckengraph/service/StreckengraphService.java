package de.db.stk.streckengraph.service;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.db.stk.streckengraph.impl.StreckengraphLoader;
import de.db.stk.streckengraph.model.KuerzelAufStrecke;
import de.systel.streckenmatching.model.StreckenGraphEdge;
import de.systel.streckenmatching.model.StreckenGraphVertex;

@Service
public class StreckengraphService {


	private static final Logger logger = LogManager.getLogger(StreckengraphService.class);
	@Autowired
	private StreckengraphLoader loader;
	private Graph<StreckenGraphVertex, StreckenGraphEdge> graph;
	private Map<KuerzelAufStrecke, StreckenGraphVertex> verticesByKuerzel;

	@PostConstruct
	private void loadGraph() {
		graph = loader.loadGraph();
		logger.info("Indexing nodes.");
		verticesByKuerzel = graph.vertexSet().stream().filter(v -> {
			return Objects.nonNull(v.getKuerzel());
		}).collect(Collectors.toMap((Function<StreckenGraphVertex, KuerzelAufStrecke>) this::kuerzelAufStrecke,
				Function.<StreckenGraphVertex>identity()));
		logger.info("Indexed " + verticesByKuerzel.size() + " betriebsstelle nodes.");
	}

	private KuerzelAufStrecke kuerzelAufStrecke(StreckenGraphVertex vertex) {
		final String kuerzel = vertex.getKuerzel();
		final int streckennummer = vertex.getStreckennummer();
		return new KuerzelAufStrecke(kuerzel, streckennummer);
	}

	public StreckenGraphVertex findByStreckennummerUndKuerzel(int streckennummer, String kuerzel) {
		
		final KuerzelAufStrecke key = new KuerzelAufStrecke(kuerzel, streckennummer);
		
		StreckenGraphVertex vertex = this.verticesByKuerzel.get(key);
		
		return vertex;
	}
}
