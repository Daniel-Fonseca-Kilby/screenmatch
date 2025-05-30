package com.aluracursos.screenmatch.Principal;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import com.aluracursos.screenmatch.Service.consumoAPI;
import com.aluracursos.screenmatch.Service.ConvierteDatos;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private consumoAPI consumoAPI = new consumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=13135fb1";
    private ConvierteDatos conversor = new ConvierteDatos();
    public void muestraElMenu(){
        System.out.println("Ingrese el nombre de la serie que deseas buscar: ");
        var nombreSerie = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
       //Busca los datos de todas la temporadas de la serie
       List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
			json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
			var datosTemporadas = conversor.obtenerDatos(json,DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}   
		//temporadas.forEach(System.out::println);

        //mostrar solo el titulo de los episodios para las temporadas
        //   for (int i = 0; i < datos.totalDeTemporadas(); i++) {
        //      List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
        //    for (int j = 0; j < episodiosTemporada.size(); j++) {
        //      System.out.println(episodiosTemporada.get(j).titulo());
        //}
        //}
        temporadas.forEach(t -> t.episodios().forEach(e-> System.out.println(e.titulo())));
    }

}
