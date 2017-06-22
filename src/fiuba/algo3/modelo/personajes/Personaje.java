package fiuba.algo3.modelo.personajes;

import fiuba.algo3.modelo.juego.*;

public abstract class Personaje {
	
	protected String nombre;
	protected double puntosDeVida;
	protected EstadoEsferaDelDragon estadoEsferaDragon;
	protected EstadoNubeVoladora estadoNubeVoladora;
	protected Coordenada coordenada;
	protected int velocidad;
	private Consumible consumible;

	public void asignarCoordenadas(Coordenada unaCoordenada){
		if(unaCoordenada.obtenerCasillero().ocupado())
        throw new ExcptionLaCoordenadaLePerteneceAUnCasilleroOcupado();
		coordenada = unaCoordenada;
		coordenada.obtenerCasillero().asignarPersonaje(this);
	}
	
	public void tomarConsumibleDe(Casillero casillero) {
		this.consumible = casillero.liberarConsumible();
		this.consumir(this.consumible);
	}

	private void consumir(Consumible consumible) {
		if(this.consumible != null){
			this.consumible.aplicarEfecto(this);
		}
	}

	public Coordenada obtenerCoordenadas(){
		return this.coordenada;
	}

	public double obtenerPuntosDeVida() {
		return this.puntosDeVida;
	}
	
	public void disminuirPuntosDeVidaEn(double danio){
		this.puntosDeVida -= danio;
	}
	
	public void comerSemillaDelErmitanio(){
		this.puntosDeVida += 100;
	}
	
	public void tomarEsferaDelDragon(){
		this.estadoEsferaDragon = new EstadoEsferaDelDragon();
	}
	
	public double usarAumentoDeAtaque() {
		double aumento = 0;
		if(this.estadoEsferaDragon != null){
			aumento = this.estadoEsferaDragon.obtenerAumentoDeAtaque(this);
		}
		return aumento;
	}
	
	public void gastarEsferaDelDragon(){
			this.estadoEsferaDragon = null;	
	}

	public abstract void tomarNubeVoladora();

	public abstract void cambiarCoordenadas(Coordenada coordenadaNueva);

	}
