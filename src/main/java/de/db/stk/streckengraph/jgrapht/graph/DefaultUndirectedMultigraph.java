package de.db.stk.streckengraph.jgrapht.graph;

import java.util.function.Supplier;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultGraphType;

public class DefaultUndirectedMultigraph<V, E> extends AbstractBaseGraph<V, E> {

	private static final long serialVersionUID = -4949970329314082273L;

	public static final <T> Supplier<T> throwingSupplier() {
		return () -> {
			throw new UnsupportedOperationException();
		};
	}

	public DefaultUndirectedMultigraph() {
		super(DefaultUndirectedMultigraph.<V>throwingSupplier(), DefaultUndirectedMultigraph.<E>throwingSupplier(),
				new DefaultGraphType.Builder().undirected().allowMultipleEdges(true).allowSelfLoops(true)
						.weighted(false).build());
	}
}
