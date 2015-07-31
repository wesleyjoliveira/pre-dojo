package br.com.amil.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partida {
	private Date horaInicio;
	private Date horaTermino;
	private String numeroPartida;
	private List<Crime> crimes = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return numeroPartida + " [" + crimes.size() + "] crimes";
	}


	public String getNumeroPartida() {
		return numeroPartida;
	}


	public void setNumeroPartida(String numeroPartida) {
		this.numeroPartida = numeroPartida;
	}

	public List<Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}


	public Date getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}


	public Date getHoraTermino() {
		return horaTermino;
	}


	public void setHoraTermino(Date horaTermino) {
		this.horaTermino = horaTermino;
	}	
	
	
}
