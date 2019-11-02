package snake.interfaces;

import javafx.scene.Node;

public interface IGraphicInterface {
	void addObject(Node node);

	void addAllObjects(Node... nodes);

	void removeObject(Node node);

	void removeAllObjects(Node... nodes);
	
	void clear();
}
