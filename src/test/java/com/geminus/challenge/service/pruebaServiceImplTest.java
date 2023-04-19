package com.geminus.challenge.service;
import com.geminus.challenge.service.impl.PruebaServiceImpl;
import org.junit.Test;


import java.util.logging.Logger;

public class pruebaServiceImplTest {

    public PruebaServiceImpl pruebaService = new PruebaServiceImpl();
    private static Logger logger = Logger.getLogger("Logger Test");


    @Test
    public void pruebaCaso1() {
        logger.info("CASO 1:");
        Integer impar = 3;
        Integer par = 98;
        System.out.println("impar: " + pruebaService.caso1(impar));
        System.out.println("par: " + pruebaService.caso1(par));

        logger.info("FIN 1:");
    }

    @Test
    public void pruebaCaso2() {
        logger.info("CASO 2:");
        int[] arreglo = {1, 23, 5, 80, 2, 53, 8, 75, 23, 75, 80, 23, 2, 1, 8};
        Integer resultado = pruebaService.caso2(arreglo);
        System.out.println("Resultado: " + resultado);
        logger.info("FIN 2:");
    }

    @Test
    public void pruebaCaso3() {
        logger.info("CASO 3:");
        Integer cantidadJaulasPorGrupo = 4;
        Integer cantidadDeGrupos = 60;
        pruebaService.caso3(cantidadJaulasPorGrupo, cantidadDeGrupos);
        logger.info("FIN 3:");
    }


}
