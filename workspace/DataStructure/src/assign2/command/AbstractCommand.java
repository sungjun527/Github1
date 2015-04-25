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
			String[] splitedArgs=args.trim().split("%");
			String[] arga;
			if(splitedArgs.length==2){
				arga=new String[]{splitedArgs[1]};
			} else {
				arga=new String[]{splitedArgs[1], splitedArgs[3]};
			}
			return arga;
		}
	}

	protected abstract void queryDatabase(MovieDatabase db, String[] arga) throws DatabaseException;
}
