package com.aluracursos.screenmatch.Service;


public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
