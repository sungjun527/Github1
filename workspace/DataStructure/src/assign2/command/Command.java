package assign2.command;
import assign2.MovieDatabase;
import assign2.exceptions.*;
public interface Command {
	void apply(MovieDatabase db, String args) throws DatabaseException;
}
