package fiuba.algo3.modelo.personajes;

import fiuba.algo3.modelo.juego.*;
import fiuba.algo3.modelo.juego.excepciones.ExceptionLaDistanciaEntreLasCoordenadasNoEsValida;
import fiuba.algo3.modelo.juego.excepciones.ExceptionNoAlcanzaAlOponente;

public class EstadoFreezerNormal implements EstadoFreezer {
	
	private int ki = 0;
	private int velocidad = 4;
    private String direccionDeImagen;
	private EstadoNubeVoladora nubeVoladora = new EstadoNubeVoladora();

	public EstadoFreezerNormal()
	{
		direccionDeImagen =  "file:src/fiuba/algo3/vista/images/Freezer.jpg";
	}
	@Override
	public void atacar(Freezer freezer, GuerrerosZ oponente) {
		oponente.recibirAtaqueDe(freezer.obtenerCoordenadas(), 20 + 20*(freezer.usarAumentoDeAtaque()), 2);
		this.ki += 5;
		this.transformar(freezer);
	}

	private void transformar(Freezer freezer) {
		if(this.ki == 20){
			freezer.asignarEstado(new EstadoFreezerSegundaForma());
		}
	}

	@Override
	public void recibirDanio(Freezer freezer, double danio) {
		if(danio < 20){
			danio = danio*80/100;
		}
		freezer.disminuirPuntosDeVidaEn(danio);
	}
	
	@Override
	public void rayoMortal(Freezer freezer, GuerrerosZ oponente) {
		if(this.ki < 20)
			throw new ExceptionAtaqueEspecial();
		oponente.recibirAtaqueDe(freezer.obtenerCoordenadas(), 30 + 30*(freezer.usarAumentoDeAtaque()), 2);
		this.ki -= 20;
	}

	@Override
	public void asignarCoordenadas(Freezer freezer, Coordenada coordenada) {
	freezer.asignarCoordenadas(coordenada);
	}
	
	@Override
	public void recibirAtaque(Freezer freezer, Coordenada coordenadasDeAtacante, int alcanceDeAtaque, double poderDePelea){
		int distanciaHorizontal = Math.abs(freezer.obtenerCoordenadas().obtenerColumna() - coordenadasDeAtacante.obtenerColumna());
		int distanciaVertical = Math.abs(freezer.obtenerCoordenadas().obtenerFila() - coordenadasDeAtacante.obtenerFila());
		if(distanciaHorizontal > alcanceDeAtaque || distanciaVertical > alcanceDeAtaque){
			throw new ExceptionNoAlcanzaAlOponente();
		}
		this.recibirDanio(freezer, poderDePelea);
	}

    @Override
    public EstadoFreezer cambiarCoordenadas(Freezer freezer, Coordenada coordenadaNueva) {
		if ((Math.abs(freezer.obtenerCoordenadas().obtenerColumna() - coordenadaNueva.obtenerColumna()) > (velocidad * nubeVoladora.obtenerAumentoDeVelocidad()))
				|| (Math.abs(freezer.obtenerCoordenadas().obtenerFila() - coordenadaNueva.obtenerFila()) > (velocidad*nubeVoladora.obtenerAumentoDeVelocidad())))
			throw new ExceptionLaDistanciaEntreLasCoordenadasNoEsValida();
		asignarCoordenadas(freezer,coordenadaNueva);
		return aumentarKi();

	}

    @Override
    public void tomarNubeVoladora(EstadoNubeVoladora unaNubeVoladora) {
    	 nubeVoladora = unaNubeVoladora.iniciarNube();
    }

    @Override
    public String obtenerDireccionDeImagen() {
        return direccionDeImagen;
    }

    @Override
    public double obtenerAtaque(Freezer freezer) {
        return 20 + 20*(freezer.usarAumentoDeAtaque());
    }

    @Override
    public int obtenerDistanciaDeAtaque() {
        return 2;
    }

    @Override
    public int obtenerVelocidad() {
        return 4;
    }

    private EstadoFreezer aumentarKi() {
		ki = ki + 5;
		if (ki == 20) return new EstadoFreezerSegundaForma();
		return this;
	}
}
