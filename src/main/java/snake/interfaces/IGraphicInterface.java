package snake.interfaces;

import javafx.scene.Node;

/**
 * 
 * @author Yevhenii Kozhevin
 *
 */
public interface IGraphicInterface {
	/**
	 * Adds {@code node} on a pane
	 * 
	 * @param node - object to add
	 */
	void addObject(Node node);

	/**
	 * Removes {@code node} from a pane
	 * 
	 * @param node - object to remove
	 */
	void removeObject(Node node);

	/**
	 * Clears a game pane
	 */
	void clear();
}
