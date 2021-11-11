package brains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

import edu.unlam.snake.brain.Brain;
import edu.unlam.snake.engine.Direction;
import edu.unlam.snake.engine.Point;

public class MyBrain extends Brain {

	// Pueden agregarse todos los atributos necesarios

	public MyBrain() {
		super("NullPointerExeption");
	}

	/**
	 * Retorna el pr贸ximo movimiento que debe hacer la serpiente.
	 * 
	 * @param head
	 *            , la posici贸n de la cabeza
	 * @param previous
	 *            , la direcci贸n en la que ven铆a movi茅ndose
	 */
	public Direction getDirection(Point head, Direction previous) {
		List<Point> fruits = info.getFruits();

		// completar con la l贸gica necesaria para mover la serpiente,
		// intentando comer la mayor cantidad de frutas y sobrevivir
		// el mayor tiempo posible.

		// para empezar, la direccion siguiente es la misma que ven韆 ( sigue
		// derecho)
		Direction direccionSiguiente = previous;
		// el proximo punto, es un clon de la cabeza actual de la snake
		Point siguientePunto = head.clone();
		// movemos el proximo punto a la direcci髇 siguiente y evaluamos si es
		// seguro
		
		Point frutaMasCercana;
		
		 frutaMasCercana = this.obtenerFrutaMasCercana(fruits, siguientePunto);
			if(frutaMasCercana.getX()== siguientePunto.getX()){
				if(frutaMasCercana.getY()>siguientePunto.getY()){
					
					direccionSiguiente = this.obtenerDireccionPosible(siguientePunto, Direction.UP,previous);
				}else{
					
					direccionSiguiente = this.obtenerDireccionPosible(siguientePunto, Direction.DOWN,previous);
				}
			
			}else if(frutaMasCercana.getX()> siguientePunto.getX()){
		
				direccionSiguiente = this.obtenerDireccionPosible(siguientePunto,  Direction.RIGHT,previous);
			}else{
				
				direccionSiguiente = this.obtenerDireccionPosible(siguientePunto, Direction.LEFT,previous);
			}
				
		
		return direccionSiguiente;

	}

	private Direction obtenerDireccionPosible(Point siguientePunto,Direction direccionSugerida,Direction previous){
		siguientePunto.moveTo(direccionSugerida);
		if (this.estaOcupado(siguientePunto)) {
			// Si no es seguro,lo movemos a la
			// direccion siguente pero girando a la derecha.
			siguientePunto.moveTo(direccionSugerida.turnRight());
			direccionSugerida = direccionSugerida.turnRight();
			// volvemos a evaluar, si no es seguro ir a la derecha, hacemos que
			// vaya a la izquierda.
			if (this.estaOcupado(siguientePunto)) {
				siguientePunto.moveTo(direccionSugerida.turnLeft());
				direccionSugerida = direccionSugerida.turnLeft();
				if(this.estaOcupado(siguientePunto)){
					siguientePunto.moveTo(direccionSugerida.turnLeft());
					direccionSugerida = direccionSugerida.turnLeft();
				}
			} 
		}
		return direccionSugerida;
	}

	private Point obtenerFrutaMasCercana(List<Point> frutas, Point puntoActual) {
		Point frutaMasCercana = frutas.get(0);
		int distanciaMenor = Math
				.abs(frutas.get(0).getX() - puntoActual.getX())
				+ Math.abs(frutas.get(0).getY() - puntoActual.getY());

		for (Point fruta : frutas) {

			int distancia = Math.abs(fruta.getX() - puntoActual.getX())
					+ Math.abs(fruta.getY() - puntoActual.getY());
			if (distancia < distanciaMenor) {
				distanciaMenor = distancia;
				frutaMasCercana = fruta;
			}

		}

		return frutaMasCercana;

	}

	private boolean estaOcupado(Point point) {
		// despues busco si en el punto al que voy hay un enemigo
		for (List<Point> enemigos : info.getEnemies()) {
			for (Point enemigo : enemigos) {
				if (enemigo.equals(point)) {
					return true;
				}
			}
		}

		// me fijo si al punto al que voy hay un obstaculo

		for (Point obst : info.getObstacles()) {
			if ((obst.getX() == point.getX()) && (obst.getY() == point.getY())) {
				return true;
			}
		}
		// me fijo si al punto al que voy hay una parte de la propia snake
		for (Point cuerpo : info.getSnake()) {
			if (cuerpo.equals(point)) {
				return true;
			}
		}

		return false;
	}
}
