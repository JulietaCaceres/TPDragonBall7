package fiuba.algo3.modelo.personajes;
import fiuba.algo3.modelo.juego.Coordenada;
import fiuba.algo3.modelo.juego.EnemigosDeLaTierra;
import fiuba.algo3.modelo.juego.EstadoNubeVoladora;
import fiuba.algo3.modelo.juego.excepciones.ExceptionAtaqueAMismoEquipo;
import fiuba.algo3.modelo.juego.GuerrerosZ;

public class Freezer extends Personaje implements EnemigosDeLaTierra{
	
	private EstadoFreezer estado;
	
	public Freezer(){
		this.nombre = "Freezer";
		this.puntosDeVida = 400;
		this.estado = new EstadoFreezerNormal();
		}
	
	public void recibirDanio(double danio){
		estado.recibirDanio(this, danio);
	}

    @Override
    public double obtenerAtaque() {
        return estado.obtenerAtaque(this);
    }

    @Override
    public int obtenerDistanciaDeAtaque() {
        return estado.obtenerDistanciaDeAtaque();
    }

    @Override
    public int obtnerVelocidad() {
        return estado.obtenerVelocidad();
    }

    @Override
	public void atacar(GuerrerosZ oponente) {
		estado.atacar(this, oponente);
	}

	@Override
	public void atacar(EnemigosDeLaTierra oponente) {
		throw new ExceptionAtaqueAMismoEquipo();
	}

	@Override
	public void realizarAtaqueEspecial(GuerrerosZ oponente) {
		estado.rayoMortal(this, oponente);		
	}

	@Override
	public void realizarAtaqueEspecial(EnemigosDeLaTierra oponente) {
		throw new ExceptionAtaqueAMismoEquipo();
	}
/*
	@Override
	public void convertirseEnChocolate() {

	}
*/
	@Override
	public void recibirAtaqueDe(Coordenada coordenadasDeAtacante, double poderDePelea, int alcanceDeAtaque) {
		estado.recibirAtaque(this, coordenadasDeAtacante, alcanceDeAtaque, poderDePelea);
	}

	public void asignarEstado(EstadoFreezer nuevaForma) {
		this.estado = nuevaForma;
	}

    @Override
    public void tomarNubeVoladora() {
		estadoNubeVoladora = new EstadoNubeVoladora();
		estado.tomarNubeVoladora(estadoNubeVoladora);
    }

    @Override
    public void cambiarCoordenadas(Coordenada coordenadaNueva) {
        estado = estado.cambiarCoordenadas(this, coordenadaNueva);
    }

	@Override
	public double porcentajeDeVida() {
		return 0;
	}

    @Override
    public String obtenerDireccionDeImagen() {
        return estado.obtenerDireccionDeImagen();
    }
}
