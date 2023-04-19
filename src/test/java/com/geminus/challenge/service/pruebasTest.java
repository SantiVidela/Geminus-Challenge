package com.geminus.challenge.service;

import org.junit.Test;

import java.util.logging.Logger;

public class pruebasTest {

	private static Logger logger = Logger.getLogger("Logger Test");
	private pruebas pruebas = new pruebas();

	@Test
	public void pruebaCaso1() {
		logger.info("CASO 1:");
		Integer impar = 3;
		Integer par = 98;
		System.out.println("impar: "+this.pruebas.caso1(impar));
		System.out.println("par: "+this.pruebas.caso1(par));

		logger.info("FIN 1:");
	}

	@Test
	public void pruebaCaso2() {
		logger.info("CASO 2:");
		int[] arreglo = {1, 23, 5, 80, 2, 53, 8, 75, 23, 75, 80, 23, 2, 1, 8};
		Integer resultado = this.pruebas.caso2(arreglo);
		System.out.println("Resultado: "+ resultado);
		logger.info("FIN 2:");
	}

	@Test
	public void pruebaCaso3() {
		logger.info("CASO 3:");
		Integer cantidadJaulasPorGrupo = 4;
		Integer cantidadDeGrupos = 60;
		this.pruebas.caso3(cantidadJaulasPorGrupo,cantidadDeGrupos);
		logger.info("FIN 3:");
	}



}
