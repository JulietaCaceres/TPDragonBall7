package modelo.Personajes;

import modelo.Juego.EnemigosDeLaTierra;
import modelo.Juego.Tablero;

public class EstadoGokuSuperSayajin implements EstadoGoku {

	@Override
	public void atacar(Goku goku, EnemigosDeLaTierra oponente) {
		oponente.recibirAtaqueDe(goku.obtenerCoordenadas(), 60 + 60*(goku.usarAumentoDeAtaque()), 4);
	}

	@Override
	public void mover(Goku goku, int filaDestino, int columnaDestino, Tablero tablero) {
		tablero.moverA(goku, filaDestino, columnaDestino, 5*goku.usarAumentoDeVelocidad());
	}

	@Override
	public void recibirDanio(Goku goku, double danio) {
		if(danio < 60){
			danio = danio*80/100;
		}
		goku.disminuirPuntosDeVidaEn(danio);
	}

}
