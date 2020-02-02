package snake.results;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultsSerializer {
	public static final String FILE_NAME = "SnakeGameResults";
	public static final String FILE_EXTENSION = "json";

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(ResultsSerializer.class);
	private static final String PATH = System.getProperty("user.dir") + "/" + FILE_NAME + "." + FILE_EXTENSION;
	// logger file

	public static void serializeResults(final Results results) {
		try {
			MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), results);
		} catch (IOException e) {
			LOGGER.error("", e);
		}
	}

	public static Results deserializeResults() {
		try {
			return MAPPER.readValue(PATH, Results.class);
		} catch (JsonProcessingException e) {
			// file does not exist or is not in the same directory
			return null;
		}
	}
}
