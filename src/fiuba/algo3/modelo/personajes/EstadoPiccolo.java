package fiuba.algo3.modelo.personajes;

import fiuba.algo3.modelo.juego.Coordenada;
import fiuba.algo3.modelo.juego.EnemigosDeLaTierra;
import fiuba.algo3.modelo.juego.EstadoNubeVoladora;
import fiuba.algo3.modelo.juego.GuerrerosZ;

public interface EstadoPiccolo {
	public abstract void atacar(Piccolo piccolo, EnemigosDeLaTierra oponente);

	public abstract void recibirDanio(Piccolo piccolo, double danio);

	public abstract void makankosappo(Piccolo piccolo, EnemigosDeLaTierra oponente);

	void asignarCoordenadas(Piccolo piccolo, Coordenada coordenada);

	void recibirAtaque(Piccolo piccolo, Coordenada coordenadasDeAtacante, int alcanceDeAtaque, double poderDePelea);

	void convertir(Piccolo piccolo);

    EstadoPiccolo cambiarCoordenadas(Piccolo piccolo, Coordenada coordenadaNueva);


	void tomarNubeVoladora(EstadoNubeVoladora unaNubeVoladora);

	void referenciarAGogan(GuerrerosZ gohan);

    String obtenerDireccionDeImagen();

    double obtenerAtaque(Piccolo piccolo);

    int obtenerDistanciaDeAtaque();

	int obtenerVelocidad();
}
