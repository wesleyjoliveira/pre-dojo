package br.com.amil.game;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	private String nome;	
	private int mortes;
	
	private List<Crime> crimes = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMortes() {
		return mortes;
	}
	public void setMortes(int mortes) {
		this.mortes = mortes;
	}
	
	public Jogador(String nomeJogador) {
		this.nome = nomeJogador;
	}
	public List<Crime> getCrimes() {
		return crimes;
	}
	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}
	
}
