package fiuba.algo3.modelo.personajes;

import fiuba.algo3.modelo.juego.*;
import fiuba.algo3.modelo.juego.excepciones.ExceptionLaDistanciaEntreLasCoordenadasNoEsValida;
import fiuba.algo3.modelo.juego.excepciones.ExceptionNoAlcanzaAlOponente;

public class EstadoGokuSuperSayajin implements EstadoGoku {
	
	private int ki = 0;
    private String direccionDeImagen;
	private int velocidad = 5;
	private EstadoNubeVoladora nubeVoladora = new EstadoNubeVoladora();

	public EstadoGokuSuperSayajin(){
		direccionDeImagen =  "file:src/fiuba/algo3/vista/images/GokuSuperSayayin.jpg";
	}

	@Override
	public void atacar(Goku goku, EnemigosDeLaTierra oponente) {
		oponente.recibirAtaqueDe(goku.obtenerCoordenadas(), 60*goku.aumentoDePoderPorAdrenalina() + 60*(goku.usarAumentoDeAtaque()), 4);
		this.ki += 5;
	}

	@Override
	public void recibirDanio(Goku goku, double danio) {
		if(danio < 60){
			danio = danio*80/100;
		}
		goku.disminuirPuntosDeVidaEn(danio);
	}
	
	@Override
	public void kamehameha(Goku goku, EnemigosDeLaTierra oponente) {
		if(this.ki<20)
			throw new ExceptionAtaqueEspecial();
		oponente.recibirAtaqueDe(goku.obtenerCoordenadas(), 90*goku.aumentoDePoderPorAdrenalina() + 90*(goku.usarAumentoDeAtaque()), 4);
		this.ki -= 20;
	}
	
	@Override
	public void asignarCoordenadas(Goku goku, Coordenada coordenada) {
		goku.asignarCoordenadas(coordenada);
	}

	@Override
	public void recibirAtaque(Goku goku, Coordenada coordenadasDeAtacante, int alcanceDeAtaque, double poderDePelea) {
		int distanciaHorizontal = Math.abs(goku.obtenerCoordenadas().obtenerColumna() - coordenadasDeAtacante.obtenerColumna());
		int distanciaVertical = Math.abs(goku.obtenerCoordenadas().obtenerFila() - coordenadasDeAtacante.obtenerFila());
		if(distanciaHorizontal > alcanceDeAtaque || distanciaVertical > alcanceDeAtaque){
			throw new ExceptionNoAlcanzaAlOponente();
		}
		this.recibirDanio(goku, poderDePelea);
	}
	
	@Override
	public void convertir(Goku goku) {
		EstadoGoku formaChocolate = new EstadoGokuChocolate();
		goku.obtenerCoordenadas().obtenerCasillero().liberarDePersonaje();
		formaChocolate.asignarCoordenadas(goku,goku.obtenerCoordenadas());
		goku.asignarEstado(formaChocolate);		
	}

    @Override
    public EstadoGoku cambiarCoordenadas(Goku goku, Coordenada coordenadaNueva) {
		if ((Math.abs(goku.obtenerCoordenadas().obtenerColumna() - coordenadaNueva.obtenerColumna()) > (velocidad * nubeVoladora.obtenerAumentoDeVelocidad()))
				|| (Math.abs(goku.obtenerCoordenadas().obtenerFila() - coordenadaNueva.obtenerFila()) > (velocidad*nubeVoladora.obtenerAumentoDeVelocidad())))
			throw new ExceptionLaDistanciaEntreLasCoordenadasNoEsValida();
		goku.obtenerCoordenadas().cambiarCoordenadas(coordenadaNueva);
		return aumentarKi();

	}


    @Override
    public void tomarNubeVoladora(EstadoNubeVoladora unaNubeVoladora) {
		this.nubeVoladora = unaNubeVoladora.iniciarNube();
    }

    @Override
    public String obtenerDireccionDeImagen() {
        return direccionDeImagen;
    }

    @Override
    public double obtenerAtaque(Goku goku) {
        return 60*goku.aumentoDePoderPorAdrenalina() + 60*(goku.usarAumentoDeAtaque());
    }

    @Override
    public int obtenerDistanciaDeAtaque() {
        return 4;
    }

    @Override
    public int obtenerVelocidad() {
        return 5;
    }

    private EstadoGoku aumentarKi() {
		ki = ki + 5;
		return this;
	}
}
