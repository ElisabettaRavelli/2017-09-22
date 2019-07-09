package it.polito.tdp.formulaone.model;

public class ArcoPeso implements Comparable<ArcoPeso> {
	
	private Integer v1;
	private Integer v2;
	private Double peso;
	public ArcoPeso(Integer v1, Integer v2, Double peso) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.peso = peso;
	}
	public Integer getV1() {
		return v1;
	}
	public Integer getV2() {
		return v2;
	}
	public Double getPeso() {
		return peso;
	}
	@Override
	public int compareTo(ArcoPeso o) {
		return -(peso.compareTo(o.peso));
	}
	@Override
	public String toString() {
		return String.format("Gara1=%s, Gara2=%s, Peso=%s", v1, v2, peso);
	}
	

}
