package assign2.command;

import assign2.exceptions.*;
import assign2.MovieDatabase;

public abstract class AbstractCommand implements Command {
	@Override
	public void apply(MovieDatabase db, String args) throws DatabaseException {
		String[] arga = parse(args);
		queryDatabase(db, arga);
	}

	private String[] parse(String args) throws CommandParseException {
		if (args.isEmpty()) {
			return new String[] {};
		} else {
			// FIXME implement this
			// Parse the input appropriately.
			// You may need to change the return value.
			return args.split(" ");
		}
	}

	protected abstract void queryDatabase(MovieDatabase db, String[] arga) throws DatabaseException;
}
