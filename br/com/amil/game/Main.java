package br.com.amil.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static void main(String[] args) {
		String nomeArq = "input.txt";
		File arq = new File(nomeArq);
		BufferedReader leitor = null;
		Partida partida = new Partida();

		if (arq.exists()) {
			try {
				FileReader reader = new FileReader(nomeArq);
				leitor = new BufferedReader(reader);
				while (true) {
					String linha = leitor.readLine();
					if (linha == null)
						break;

					if (linha.startsWith("New match", 22)) {
						partida = Util.carregaInicio(linha, partida);
					} else if (linha.endsWith("has ended")) {
						partida = Util.carregaFim(linha, partida);
					} else {
						Crime crime = Util.getCrime(linha);
						if (crime != null){
							partida.getCrimes().add(crime);							
						}
					}
				}
			} catch (Exception erro) {
				erro.printStackTrace();
			} finally {
				try {
					leitor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Dados da partida:[" + partida.getNumeroPartida() + "]");
			System.out.println("Inicio Partida:[" + sdf.format(partida.getHoraInicio()) + "]");
			System.out.println("Fim Partida:[" + sdf.format(partida.getHoraTermino())+ "]");
			System.out.println("Total de Crimes:[" + partida.getCrimes().size() + "]");
			
			System.out.println("\nRanking da Partida");
			List<Jogador> jogadores = Util.getJogadoresOrdenados(partida);
			int i = 0;
			for (Jogador j: jogadores) {
				i++;
				System.out.printf("#%d %s\t[%d]Assassinatos\t[%d]Mortes\n", i, j.getNome(), j.getCrimes().size(), j.getMortes());				
			}
			
			System.out.println("\nArma Preferida do vencedor");
			Jogador j = jogadores.get(0);
			Arma armaPreferida = Util.getArmaPreferida(j);
			System.out.printf("Jogador: %s Arma Preferida:[%s]\n", j.getNome(), armaPreferida.getNome());

			System.out.println("\nVencedor ganhou award por nao morrer?");
			boolean ganhouSemMorrer = j.getMortes() == 0;
			System.out.printf("Jogador: %s Ganhou sem morrer:[%s]\n", j.getNome(), ganhouSemMorrer?"SIM":"NAO");
			
			
		} else {
			System.out.println("arquivo input.txt inexistente");
		}

	}
}
