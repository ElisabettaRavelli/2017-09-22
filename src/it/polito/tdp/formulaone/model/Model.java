package it.polito.tdp.formulaone.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private FormulaOneDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<ArcoPeso> arcopeso;
	
	public Model() {
		this.dao = new FormulaOneDAO();
		this.arcopeso = new ArrayList<>();
	}
	
	public List<Season> getAllSeason() {
		return this.dao.getAllSeasons();
	}
	
	public void creaGrafo(Integer anno) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, this.dao.getGareYear(anno));
		
		for(Integer v1: this.grafo.vertexSet()) {
			for(Integer v2: this.grafo.vertexSet()) {
				if(!v1.equals(v2) && this.grafo.getEdge(v1, v2)==null) {
					Double peso = (double) this.dao.getPeso(v1, v2);
					Graphs.addEdge(this.grafo, v1, v2, peso);
					this.arcopeso.add(new ArcoPeso(v1, v2, peso));
					System.out.println("Arco aggiunto: "+v1+ " -> "+ v2+" con peso= "+peso);
				}
			}
		}
	}
	public int getVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<ArcoPeso> getPesoMax(){
		List<ArcoPeso> result = new ArrayList<>();
		Collections.sort(this.arcopeso);
		for(ArcoPeso apfisso: this.arcopeso) {
			for(ArcoPeso apmobile: this.arcopeso) {
				if(apfisso.getPeso().equals(apmobile.getPeso())) {
					result.add(new ArcoPeso(apmobile.getV1(), apmobile.getV2(), apmobile.getPeso()));
				}else 
					break;
			}
			break;
		}
		return result;
	}

}
