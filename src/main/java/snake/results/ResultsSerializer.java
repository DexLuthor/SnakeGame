package snake.results;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Serializes/Deserializes {@code Results} objects
 * 
 * @author Yevhenii Kozhevin
 *
 */
public class ResultsSerializer {
	/**
	 * Path to file with saved results
	 */
	public static final String PATH = System.getProperty("user.dir") + "/" + "SnakeGameResults" + ".json";
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResultsSerializer.class);
	/**
	 * ObjectMapper provides functionality for reading and writing JSON
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * Save results to a file
	 * 
	 * @param results - current results
	 */
	public static void serializeResults(final Results results) {
		try {
			MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), results);
		} catch (IOException e) {
			LOGGER.error("", e);
		}
	}

	/**
	 * Reads file if it exists, and returns the best results or null if file does not exist
	 * 
	 * @return {@code Results} object representing the best result. null - if file
	 *         does not exist or it is damaged
	 */
	public static Results deserializeExistingResults() {
		try {
			return MAPPER.readValue(PATH, Results.class);
		} catch (JsonProcessingException e) {
			// file does not exist or is not in the same directory
			return null;
		}
	}
}
