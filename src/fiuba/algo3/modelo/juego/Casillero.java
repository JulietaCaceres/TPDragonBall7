package fiuba.algo3.modelo.juego;

import fiuba.algo3.modelo.personajes.Personaje;


public class Casillero {

	protected Personaje personaje;
	public Consumible consumible;

	public Casillero() {
		this.personaje = null;
		this.consumible = null;
	}
	
	public void asignarConsumible(Consumible consumible){
		this.consumible = consumible;
	}
	
	public Consumible liberarConsumible(){
		Consumible objetoADevolver = this.consumible;
		this.consumible = null;
		return objetoADevolver;
	}

	public Personaje obtenerPersonaje() {
		return this.personaje;
	}

	public void asignarPersonaje(Personaje unPersonaje) {

		this.personaje = unPersonaje;
		if (consumible != null) personaje.tomarConsumibleDe(this);

				}

	public boolean ocupado() {
		return (this.personaje != null);
	}

	public void liberarDePersonaje() {
		this.personaje = null;
	}

    public void ocuparCasilleroConPersonaje() {
         //asignarPersonaje();
	}
}
