package brains;

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
	 * @param head,     la posición de la cabeza
	 * @param previous, la dirección en la que venía moviéndose
	 */
	public Direction getDirection(Point head, Direction previous) {
		List<Point> fruits = info.getFruits();
		List<Point> snake = info.getSnake();
		List<List<Point>> enemies = info.getEnemies();
		List<Point> obstacles = info.getObstacles();

		Direction nextDirection = previous;
		Point nextPoint = head.clone();
		nextPoint.moveTo(previous);
		// completar con la lógica necesaria para mover la serpiente,
		// intentando comer la mayor cantidad de frutas y sobrevivir
		// el mayor tiempo posible.

		Point anterior = nextPoint;
		Direction siguiente = Direction.RIGHT;
		Point puntoSiguiente = obtenerSiguiente(snake.get(0), siguiente);

		if (esMovimientoPosible(snake, puntoSiguiente, anterior) && !estaOcupado(puntoSiguiente)) {
			anterior = nextPoint;
			nextPoint.moveTo(siguiente);
		} else {
			doblar();
		}

		return nextDirection;
	}

	// devuelve true si hay un enemigo, una pared o una parte de la misma snake

	private void doblar() {

	};

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

	private boolean esMovimientoPosible(List<Point> snake, Point moverHacia, Point anterior) {
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
			if (obst.equals(point)) {
				return true;
			}
		}

		return false;
	}
}
