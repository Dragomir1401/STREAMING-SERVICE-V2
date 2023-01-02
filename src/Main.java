import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import commands.HomepageSetter;
import momentary.PageNow;
import commands.CommandParser;
import input.Input;
import input.UserInput;
import input.MovieInput;
import output.Output;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public final class Main {

    private Main() {

    }

    /**
     * main function
     * @param args - args[0] - input; args[1] - output
     * @throws IOException - file opening exception
     */
    public static void main(final String[] args) throws IOException {
        // initialise mapper
        ObjectMapper objectMapper = new ObjectMapper();

        // parse input for given test
        Input programInput = objectMapper.readValue(new File(args[0]), Input.class);

        // initialise output
        Output programOutput = new Output();

        // set homepage
        PageNow pageNow = HomepageSetter.run();

        // differentiate commands and begin program ENTRY POINT
        CommandParser.parse(programInput, pageNow, programOutput);

        // generate output and ignore subscribed genres for output
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(args[1]), programOutput.getOutput());

        // reset current user
        pageNow.getUser().setUser(null);

        // reset input
        programInput.getUsers().forEach(UserInput::resetUser);
        programInput.getMovies().forEach(MovieInput::resetMovie);
    }
}



