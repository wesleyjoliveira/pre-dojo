package br.com.amil.game;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Util {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss");

	public static Comparator<Jogador> getJogadorComparatorExecucoes = new Comparator<Jogador>() {
		public int compare(Jogador jogador1, Jogador jogador2) {
			return new Integer(jogador2.getCrimes().size()).compareTo(new Integer(jogador1.getCrimes().size()));
		}
	};		
	
	public static Crime getCrime(String linha) {
		//System.out.println("Carregando crime:[" + linha + "]" );
		Crime crime = null;
		
		String[] det = linha.split(" ");
		
		if (det.length == 8) {
			crime = new Crime();
			crime.setArma(new Arma(det[7]));
			
			if (!"<WORLD>".equals(det[3])){
				crime.setAssassino(new Jogador(det[3]));				
			}
			try {
				crime.setHoraCrime(sdf.parse(det[0] + det[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			crime.setVitima(new Jogador(det[5]));		
		}		
		
		return crime;
	}
	
	public static Partida carregaInicio(String linha, Partida partida){
		//System.out.println("Carregando inicio:[" + linha + "]" );
		
		String[] det = linha.split(" ");
		
		if (det.length == 8) {
			partida.setNumeroPartida(det[5]);
			try {
				partida.setHoraInicio(sdf.parse(det[0] + det[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return partida;
	}
	
	public static Partida carregaFim(String linha, Partida partida){
		//System.out.println("Carregando fim:[" + linha + "]" );
		
		String[] det = linha.split(" ");
		
		if (det.length == 7) {
			try {
				partida.setHoraTermino(sdf.parse(det[0] + det[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return partida;
	}
	
	public static List<Jogador> getJogadoresOrdenados(Partida partida){
		HashMap<String,Jogador> jogadores = new HashMap<String, Jogador>();
		
		for (Crime c : partida.getCrimes()){
			Jogador assassino = null;
			Jogador vitima = null;
			
			if (c.getAssassino() != null) {
				if (jogadores.containsKey(c.getAssassino().getNome())){
					assassino = jogadores.get(c.getAssassino().getNome());
				} else {
					assassino = c.getAssassino();				
					jogadores.put(assassino.getNome(), assassino);
				}
				List<Crime> crimesAtuais = assassino.getCrimes();
				crimesAtuais.add(c);
				assassino.setCrimes(crimesAtuais);				
			}
			
			if (jogadores.containsKey(c.getVitima().getNome())){
				vitima = jogadores.get(c.getVitima().getNome());
			} else {
				vitima = c.getVitima();
				jogadores.put(c.getVitima().getNome(), vitima);
			}

			vitima.setMortes(vitima.getMortes()+1);
		}
		
		List<Jogador> listJogadores = new ArrayList<Jogador>();
		listJogadores.addAll(jogadores.values());		
		Collections.sort(listJogadores, getJogadorComparatorExecucoes);
		
		return listJogadores;
	}
	
	public static Arma getArmaPreferida(Jogador jogador){
		Arma arma = null;
		
		ArrayList<Arma> armas = new ArrayList<Arma>();
		
		for (Crime c: jogador.getCrimes()){
			armas.add(c.getArma());
		}
		
		int frequenciaMaxima = 0;
		
		for (Arma a: armas){
			int frequenciaAtual = Collections.frequency(armas, a);
			
			if (frequenciaAtual > frequenciaMaxima){
				frequenciaMaxima = frequenciaAtual;
				arma = a;
			}
		}
						
		return arma;
	}

		
}
