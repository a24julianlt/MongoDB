package com.model;

public class Partida {
    private String xogador;
    private String xogo;
    private int puntuacion;
    private float duracion;
    private int nivel;

    public Partida(String xogador, String xogo, int puntuacion, float duracion, int nivel) {
        this.xogador = xogador; // nombre del jugador
        this.xogo = xogo;   // nombre del juego
        this.puntuacion = puntuacion;
        this.duracion = duracion;
        this.nivel = nivel;
    }

    /**
     * Devuelve el nombre de un jugador
     * 
     * @return
     */
    public String getXogador() {
        return xogador;
    }

    /**
     * Modifica el nombre de un jugador
     * 
     * @param xogador
     */
    public void setXogador(String xogador) {
        this.xogador = xogador;
    }

    /**
     * Devuelve el nombre del juego
     * 
     * @return nombre del juego
     */
    public String getXogo() {
        return xogo;
    }

    /**
     * Modifica el nombre de un juego
     * 
     * @param xogo
     */
    public void setXogo(String xogo) {
        this.xogo = xogo;
    }

    /**
     * Devuelve la puntuación de la partida
     * 
     * @return puntuación de la partida
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Modifica la puntuación de la partida
     * 
     * @param puntuacion
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Devuelve la duración de la partida
     * 
     * @return duración de la partida
     */
    public float getDuracion() {
        return duracion;
    }

    /**
     * Modifica la duración de la partida
     * 
     * @param duracion
     */
    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    /**
     * Devuelve el nivel de la partida
     * 
     * @return nivel de la partida
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Modifica el nivel de la partida
     * 
     * @param nivel
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
