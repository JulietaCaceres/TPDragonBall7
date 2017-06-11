package modelo.Piccolo;
import Juego.Ataque;
import Juego.Personaje;
import Juego.Tablero;

public class EstadoPiccoloNormal implements EstadoPiccolo {
	@Override
	public void atacar(Piccolo piccolo, Personaje oponente, Tablero tablero) {
		Ataque ataque = new Ataque();
		ataque.atacar(piccolo, oponente, tablero, 20 + 20*(piccolo.usarAumentoDeAtaque()), 2);
	}

	@Override
	public void mover(Piccolo piccolo, int filaDestino, int columnaDestino, Tablero tablero) {
		tablero.moverA(piccolo, filaDestino, columnaDestino, 2*piccolo.usarAumentoDeVelocidad());
	}
}
