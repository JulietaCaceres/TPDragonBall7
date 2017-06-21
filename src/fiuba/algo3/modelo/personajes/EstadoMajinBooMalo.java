package fiuba.algo3.modelo.personajes;
import fiuba.algo3.modelo.juego.*;

public class EstadoMajinBooMalo implements EstadoMajinBoo {
	
	private int ki = 0;

    private int velocidad = 3;
	@Override
	public void atacar(MajinBoo majinBoo, GuerrerosZ oponente) {
		oponente.recibirAtaqueDe(majinBoo.obtenerCoordenadas(),50 + 50*(majinBoo.usarAumentoDeAtaque()), 2);
		this.ki += 5;
		this.transformar(majinBoo);
	}
	
	@Override
	public void recibirDanio(MajinBoo majinBoo, double danio) {
		if(danio < 50){
			danio = danio*80/100;
		}
		majinBoo.disminuirPuntosDeVidaEn(danio);
	}
	
	public void transformar(MajinBoo majinBoo) {
		if(this.ki == 50){
			EstadoMajinBooMalo nuevaForma = new EstadoMajinBooMalo();
			majinBoo.obtenerCoordenadas().obtenerCasillero().liberarDePersonaje();
			nuevaForma.asignarCoordenadas(majinBoo, majinBoo.obtenerCoordenadas());
			majinBoo.asignarEstado(nuevaForma);
		}
	}
	
	@Override
	public void convertirEnChocolate(GuerrerosZ oponente) {
		if (this.ki < 5){
			throw new ExceptionAtaqueEspecial();
		}
		oponente.convertirseEnChocolate();
	}

	@Override
	public void mover(MajinBoo majinBoo, Coordenada coordenadaDestino){
		int distanciaHorizontal = Math.abs(majinBoo.obtenerCoordenadas().obtenerColumna() - coordenadaDestino.obtenerColumna());
		int distanciaVertical = Math.abs(majinBoo.obtenerCoordenadas().obtenerFila() - coordenadaDestino.obtenerFila());
		
		if(distanciaHorizontal > 3 || distanciaVertical > 3){
			throw new ExceptionCantidadDeCasillerosSuperaVelocidad();
		}
		//this.majinBoo.obtenerCoordenadas().vaciarCasillero();
		//this.majinBoo.obtenerCoordenadas() = coordenadaDestino;
		coordenadaDestino.asignarPersonajeACasillero(majinBoo);
		this.ki += 5;
		this.transformar(majinBoo);
	}

	@Override
	public void asignarCoordenadas(MajinBoo majinBoo, Coordenada coordenada) {
		//this.majinBoo.obtenerCoordenadas() = coordenada;
		coordenada.asignarPersonajeACasillero(majinBoo);
	}
	
	@Override
	public void recibirAtaque(MajinBoo majinBoo, Coordenada coordenadasDeAtacante, int alcanceDeAtaque, double poderDePelea) {
		int distanciaHorizontal = Math.abs(majinBoo.obtenerCoordenadas().obtenerColumna() - coordenadasDeAtacante.obtenerColumna());
		int distanciaVertical = Math.abs(majinBoo.obtenerCoordenadas().obtenerFila() - coordenadasDeAtacante.obtenerFila());
		if(distanciaHorizontal > alcanceDeAtaque || distanciaVertical > alcanceDeAtaque){
			throw new ExceptionNoAlcanzaAlOponente();
		}
		this.recibirDanio(majinBoo, poderDePelea);
	}

    @Override
	public void cambiarCoordenadas(Coordenada coordenadaActual,Coordenada coordenadaNueva) {
		if ((Math.abs(coordenadaActual.obtenerColumna() - coordenadaNueva.obtenerColumna()) > velocidad) || (Math.abs(coordenadaActual.obtenerFila() - coordenadaNueva.obtenerFila()) > velocidad))
			throw new ExceptionLaDistanciaEntreLasCoordenadasNoEsValida();
		coordenadaActual.cambiarCoordenadas(coordenadaNueva);
		aumentarKi();
	}

	private void aumentarKi() { ki = ki + 5;
	}

}
