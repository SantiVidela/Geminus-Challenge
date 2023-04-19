package com.geminus.challenge.service;


import com.geminus.challenge.entity.Jaula;
import io.vavr.Lazy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class pruebas {
    private final Lazy<SplittableRandom> random = Lazy.of(SplittableRandom::new);

    /**
     * El metodo debe retornar un booleano indicando si el parametro `cadena` cumple con alguna de las siguientes propiedades:
     * 1- Todos los caracteres aparecen la misma cantidad de veces.
     * Ejemplos: "aabbcc", "abcdef", "aaaaaa"
     * 2- Todos los caracteres aparecen la misma cantidad de veces, a excepcion de 1, que aparece un vez mas o una vez menos.
     * Ejemplos: "aabbccc", "aabbc", "aaaaccccc"
     *
     * @param cadena la cadena a evaluar
     * @return booleano indicando si la cadena cumple con las propiedades
     */
    public Boolean isValid(String cadena) {
        if (cadena.length() > 0 && cadena != null) {
            Boolean todosLaMismaCantidadMenosUno = false;

            String cadenaSinEspacios = cadena.replace(" ", "");
            //int cadenaSinEspaciosLength = cadenaSinEspacios.length();
            int contador = 0;
            int contadorCaracteresDistintos = 0;

            while (cadenaSinEspacios.length() > 0) {
                if (contador == 0) {
                    contador = cadenaSinEspacios.length() - cadenaSinEspacios.replace(String.valueOf(cadenaSinEspacios.charAt(0)), "").length();
                }
                cadenaSinEspacios = cadenaSinEspacios.replace(String.valueOf(cadenaSinEspacios.charAt(0)), "");
                if (cadenaSinEspacios.length() > 0) {
                    if (cadenaSinEspacios.length() - cadenaSinEspacios.replace(String.valueOf(cadenaSinEspacios.charAt(0)), "").length() == contador) {
                        todosLaMismaCantidadMenosUno = true;
                    } else {
                        if (Math.abs(cadenaSinEspacios.length() - cadenaSinEspacios.replace(String.valueOf(cadenaSinEspacios.charAt(0)), "").length() - contador) == 1) {
                            contadorCaracteresDistintos++;
                            if (contadorCaracteresDistintos > 1) {
                                todosLaMismaCantidadMenosUno = false;
                            } else {
                                todosLaMismaCantidadMenosUno = true;
                            }
                        } else {
                            todosLaMismaCantidadMenosUno = false;
                        }
                    }
                }
            }
            return todosLaMismaCantidadMenosUno;

        } else {
            throw new UnsupportedOperationException();
        }


    }


    public static final Double SRG = 1590d;

    /**
     * Caso 1
     * Ingresar un número. Si el número ingresado es par, multiplicarlo por 10. Si el número ingresado es impar, multiplicarlo por 3.
     * Luego dividir el valor de SGR, por el producto resultante del paso anterior.
     * Nota: SGR es una variable de tipo Double con valor = 1590d
     */

    public Double caso1(Integer nro) {
        Integer multiplicador = isPar(nro) ? 10 : 3;
        return SRG / (nro * multiplicador);
    }

    /**
     * Verifica si es par o impar
     */
    public Boolean isPar(Integer nro) {
        Integer mod = nro % 2;
        return (mod == 0);
    }


    /**
     * Caso 2
     * <p>
     * Dado un arreglo de n valores int[primitivo], retornar por consola el número menor, cuya ocurrencia en el arreglo sea única.
     * Por ejemplo, en el arreglo [1, 23, 5, 80, 2, 53, 8, 75, 23, 75, 80, 23, 2, 1, 8],
     * los números 5 y 53 tienen una única ocurrencia, pero se debe mostrar el 5 dado que es menor a 53.
     *
     * @return
     */

    public Integer caso2(int[] arreglo) {
        Set<Object> result = new HashSet();
        /*convierto el arreglo en un List<Integer> para manejarlo con Strams()*/
        List<Integer> arregloList = Arrays.stream(arreglo).boxed().collect(Collectors.toList());
        List<Integer> unicos = findUniques(arregloList);/**busca los valores que no se repiten**/
        return unicos.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);/**retorna el menor de la lista*/
    }

    public List<Integer> findUniques(List<Integer> arregloList) {
        Set<Integer> hashSet = new HashSet<Integer>();
        List<Integer> repetidos = arregloList.stream()
                .filter(e -> !hashSet.add(e)) //hashSet.add(e) devuelve true si no esta presente "e" en el conjunto,
                // al negarlo quedan guardados todos los valores repetidos.
                .collect(Collectors.toList());
        return arregloList.stream()
                .filter(e -> !repetidos.contains(e)).collect(Collectors.toList()); //me quedo con los valores que no estan repetidos

    }

    /**
     * Caso 3
     * <p>
     * Dado una colección de 20 jaulas, cuyos identificadores van del 101 al 120, siendo estos de tipo Integer.
     * Es decir tenemos la jaula 101, la jaula 102 y así sucesivamente hasta 120.
     * Se solicita: armar 60 grupos, con 4 jaulas por grupo.
     * Las condiciones a cumplir son:
     * - Cada jaula debe tener la misma probabilidad de ser elegida para formar parte de un grupo (distribución uniforme)
     * - Las 4 jaulas de un grupo deben ser distintas. Ejemplo de grupo [Jaula 105, Jaula 108, Jaula 116, Jaula 120]
     * - Mostrar por consola la estructura de grupos formada.
     */

    public void caso3(Integer cantidadJaulasPorGrupo, Integer cantidadDeGrupos) {
        String name = "Jaula "; // string de nombre generico
        Random rnd = new Random();

        List<Integer> ids = IntStream.rangeClosed(101, 120).boxed().collect(Collectors.toList());//lista de ids desde el 101 al 120
        List<Jaula> jaulas = new ArrayList<>(); // lista de jaulas

        //Recorro la lista de ids y por cada una creo una jaula y la agrego a la lista de jaulas
        ids.stream().forEach(id -> {
            jaulas.add(new Jaula(id, name + String.valueOf(id)));
            Collections.shuffle(jaulas);//para agarantizar aleatoriedad se mezcla desde que se va armando la lista
        });

        List<List<Jaula>> grupos = new ArrayList<>(); //lista de grupos

        for (int i = 0; i < cantidadDeGrupos; i++) {
            Collections.shuffle(jaulas,rnd);//Mezcla de form aleatoria la lista de jaulas
            List<Jaula> grupo = jaulas.subList(0, cantidadJaulasPorGrupo); // grupo  contiene "cantidadJaulasPorGrupo" Jaulas
            grupos.add(grupo);
            System.out.println("Grupo " + String.valueOf(i) + ":");
            grupo.stream().forEach(jaula -> {
                System.out.println(jaula.getName());
            });
            System.out.println(" ");
        }

    }
}
