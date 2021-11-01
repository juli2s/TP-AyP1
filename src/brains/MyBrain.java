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
		super("COMPLETAR NOMBRE DE EQUIPO");
	}

	/**
	 * Retorna el próximo movimiento que debe hacer la serpiente.
	 * 
	 * @param head
	 *            , la posición de la cabeza
	 * @param previous
	 *            , la dirección en la que venía moviéndose
	 */
	public Direction getDirection(Point head, Direction previous) {
		List<Point> fruits = info.getFruits();
		List<Point> snake = info.getSnake();
		List<List<Point>> enemies = info.getEnemies();
		List<Point> obstacles = info.getObstacles();

		// completar con la lógica necesaria para mover la serpiente,
		// intentando comer la mayor cantidad de frutas y sobrevivir
		// el mayor tiempo posible.

		// Point anterior = nextPoint;
		// aca creo una lista de posibles direcciones para empezar la partida

		List<Direction> direcciones = new ArrayList<Direction>();
		direcciones.add(Direction.DOWN);
		direcciones.add(Direction.UP);
		direcciones.add(Direction.LEFT);
		direcciones.add(Direction.RIGHT);

		// aca las mezclo de forma random
		Collections.shuffle(direcciones);
		// para empezar agarro la primer direccion que haya quedado en la lista
		// que recien se mezclo
		Direction nextDirection = direcciones.get(0);
		// Point puntoSiguiente = obtenerSiguiente(snake.get(0), nextDirection);
		Point nextPoint = head.clone();
		nextPoint.moveTo(nextDirection);
		System.out.println(nextDirection);
		// si el movimiento es posible, lo hace sin� intenta con la siguiente
		// direccion de la lista y as� hasta completar las 4

			
		if (estaOcupado(nextPoint) == false) {
				nextPoint = head.clone();
				nextPoint.moveTo(nextDirection);
				System.out.println(nextDirection);
				System.out.println(nextPoint.toString());
				if (estaOcupado(nextPoint)) {
					nextDirection = (direcciones.get(1));
					nextPoint.moveTo(nextDirection);
					System.out.println(nextDirection);
					System.out.println(nextPoint.toString());
				} else if (estaOcupado(nextPoint)) {
					nextDirection = (direcciones.get(2));
					nextPoint.moveTo(nextDirection);
					System.out.println(nextDirection);
					System.out.println(nextPoint.toString());
				} else if(estaOcupado(nextPoint)){
					nextDirection = (direcciones.get(3));
					nextPoint.moveTo(nextDirection);
					System.out.println(nextDirection);
					System.out.println(nextPoint.toString());
				}else
				{
					return nextDirection;
				}
			}else {
				nextDirection = nextDirection.turnLeft();
				nextPoint.moveTo(nextDirection);
				if(estaOcupado(nextPoint)){
					nextDirection = nextDirection.turnRight();
					nextPoint.moveTo(nextDirection);
				}else{
					throw new Error("esta ocupado");
				}
			}
		
	return nextDirection;

	}

	private Point obtenerSiguiente(Point actual, Direction direccion) {
		Point siguiente;
		if (direccion == Direction.RIGHT) {
			siguiente = new Point(actual.getX() + 1, actual.getY());
		} else if (direccion == Direction.LEFT) {
			siguiente = new Point(actual.getX() - 1, actual.getY());
		} else if (direccion == Direction.UP) {
			siguiente = new Point(actual.getX(), actual.getY() + 1);
		} else {
			siguiente = new Point(actual.getX(), actual.getY() - 1);
		}
		return siguiente;
	}

	private boolean esMovimientoPosible(List<Point> snake, Point moverHacia,
			Point anterior) {
		if (moverHacia == anterior) {
			return false;
		}
		for (Point cuerpo : info.getSnake()) {
			if (cuerpo.equals(moverHacia)) {
				return false;
			}
		}
		return true;
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

		return false;
	}
}
