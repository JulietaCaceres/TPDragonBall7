package modelo.Personajes;

import modelo.Juego.EnemigosDeLaTierra;
import modelo.Juego.Tablero;

public class EstadoGohanNormal implements EstadoGohan {

	@Override
	public void atacar(Gohan gohan, EnemigosDeLaTierra oponente) {
		oponente.recibirAtaqueDe(gohan.obtenerCoordenadas(), 15 + 15*(gohan.usarAumentoDeAtaque()), 2);
	}
	
	@Override
	public void mover(Gohan gohan, int filaDestino, int columnaDestino, Tablero tablero) {
		tablero.moverA(gohan, filaDestino, columnaDestino, 2*gohan.usarAumentoDeVelocidad());
	}

	@Override
	public void recibirDanio(Gohan gohan, double danio) {
		if(danio < 15){
			danio = danio*80/100;
		}
		gohan.disminuirPuntosDeVidaEn(danio);
	}

}
