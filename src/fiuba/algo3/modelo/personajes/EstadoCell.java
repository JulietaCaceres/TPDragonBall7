package fiuba.algo3.modelo.personajes;

import fiuba.algo3.modelo.juego.Coordenada;
import fiuba.algo3.modelo.juego.GuerrerosZ;

public interface EstadoCell {
	
	public void atacar(Cell cell, GuerrerosZ oponente);

	public void recibirDanio(Cell cell, double danio);
	
	public void absorberVida(Cell cell, GuerrerosZ oponente);

	public void mover(Cell cell, Coordenada coordenadaDestino);

	public void asignarCoordenadas(Cell cell, Coordenada coordenada);

	public void recibirAtaque(Cell cell, Coordenada coordenadasDeAtacante, int alcanceDeAtaque, double poderDePelea);
}
