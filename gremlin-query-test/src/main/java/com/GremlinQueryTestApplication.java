package com;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class GremlinQueryTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GremlinQueryTestApplication.class, args);
		System.out.println("Application Started");

		TinkerGraph tg = TinkerGraph.open() ;

		try	{
			tg.io(IoCore.graphml()).readGraph("C:/Users/liber/Downloads/gremlin-query-test/src/main/resources/air-routes.graphml");
		}
		catch( IOException e ) {
			System.out.println("File not found");
			System.exit(1);
		}
		GraphTraversalSource g = tg.traversal();
		Map<String, ?> aus = g.V().has("code","AUS").valueMap().next();
		System.out.println(aus);
		GraphTraversal<Vertex, Long> v = g.V().count();
		GraphTraversal<Edge, Long> e = g.E().count();
		System.out.println(v.next());
		System.out.println(e.next());

	}

}
