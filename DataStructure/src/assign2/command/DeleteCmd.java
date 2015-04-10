package assign2.command;
import java.util.Arrays;
import assign2.MovieDatabase;
import assign2.exceptions.*;

public class DeleteCmd extends AbstractCommand {
	@Override
	protected void queryDatabase(MovieDatabase db, String[] arga) throws DatabaseException {
		checkArga(arga);
		db.delete(arga[0], arga[1]);
	}

	private void checkArga(String[] arga) throws DatabaseException {
		if (arga.length != 2)
			throw new CommandParseException("DELETE", Arrays.toString(arga), "insufficient argument");
	}
}
