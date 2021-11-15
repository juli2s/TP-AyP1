/**
* @author jdossantos
*/
package brains;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import edu.unlam.snake.engine.Point;
import game.GameDifficulty;
import game.GameMode;
import game.GameStudent;
import edu.unlam.snake.brain.Brain;
import edu.unlam.snake.engine.Direction;

import org.junit.Before;
import org.junit.Test;


public class NullPointerExceptionBrainTest  {

	public static void main(String[] args) {
		GameMode gameMode = GameMode.NORMAL;
		NullPointerExceptionBrain myBrain = new NullPointerExceptionBrain();
		GameStudent.start(gameMode, 2, 2, GameDifficulty.NORMAL, obstacleMap(gameMode), myBrain);
		// Pueden probar multiples copias de su Brain o varias copias distintas enviado
		// un array en vez del objeto
	}

	// Prueben generar distinta combinación de mapas de obstaculos
	static List<Point> obstacleMap(GameMode gameMode) {
		int SIZE = gameMode == GameMode.NORMAL ? 20 : 40;
		List<Point> obstacles = new LinkedList<Point>();
		for (int i = 1; i < SIZE; i += 2) {
			obstacles.add(new Point(i, i));
			obstacles.add(new Point(i, SIZE - i));
		}
		return obstacles;
	}

	@Test
	public void obtenerFrutaMasCercana() {
		GameMode gameMode = GameMode.NORMAL;
		Point head = new Point();

		NullPointerExceptionBrain myBrain = new NullPointerExceptionBrain();
		System.out.println(head.toString());
		
		GameStudent.start(gameMode, 2, 2, GameDifficulty.NORMAL, obstacleMap(gameMode), myBrain);
		
		
		
	}

}
