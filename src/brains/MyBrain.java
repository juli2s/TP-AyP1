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
		List<Point> snake = info.getSnake();
		List<List<Point>> enemies = info.getEnemies();
		List<Point> obstacles = info.getObstacles();
		

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
		siguientePunto.moveTo(direccionSiguiente);
		System.out.println(siguientePunto.toString());
		if (this.estaOcupado(siguientePunto)) {
			// Si no es seguro, el proximo punto lo clonamos y lo movemos a la
			// direccion siguente pero girando a la derecha.
			
			siguientePunto.moveTo(previous.turnRight());
			System.out.println(siguientePunto.toString());
			// volvemos a evaluar, si no es seguro ir a la derecha, hacemos que
			// vaya a la izquierda.
			if (this.estaOcupado(siguientePunto)) {
				direccionSiguiente = previous.turnLeft();
			} else {
				// en este caso como no puede ir para atras, la hacemos que siga
				// y muera.
				direccionSiguiente = previous;
			}
		}

		return direccionSiguiente;

	}
	private void comer(Point frutaMasCercana,Point head){
		
		/**
		 * guardar las frutas en un array
		 * ordenarlo de mas cercana a mas lejana
		 * llamar a la primer pos y ese punto guardarlo en una variable nueva. 
		 *  
		 * 
		 */
		
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
