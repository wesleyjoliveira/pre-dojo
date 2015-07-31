package br.com.amil.game;

import java.util.Date;

public class Crime {
	private Date horaCrime;
	private Jogador vitima;
	private Jogador assassino;
	private Arma arma;
	
	public Date getHoraCrime() {
		return horaCrime;
	}
	public void setHoraCrime(Date horaCrime) {
		this.horaCrime = horaCrime;
	}
	public Jogador getVitima() {
		return vitima;
	}
	public void setVitima(Jogador vitima) {
		this.vitima = vitima;
	}
	public Jogador getAssassino() {
		return assassino;
	}
	public void setAssassino(Jogador assassino) {
		this.assassino = assassino;
	}
	public Arma getArma() {
		return arma;
	}
	public void setArma(Arma arma) {
		this.arma = arma;
	}
	
}
