package assign2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import assign2.command.*;
import assign2.exceptions.*;

public class MovieDatabaseConsole {
	/* This project is contributed by the following people (in alphabetical order).
	 * ipkn <ipknhama AT gmail DOT com>
	 * shurain <shurain AT gmail DOT com>
	 * stania <stania.pe.kr AT gmail DOT com>
	 * wookayin <wookayin AT gmail DOT com>
	 */
	private static Map<String, Command> commands = new HashMap<String, Command>();

	static {
		commands.put("INSERT", new InsertCmd());
		commands.put("DELETE", new DeleteCmd());
		commands.put("SEARCH", new SearchCmd());
		commands.put("PRINT", new PrintCmd());
	}

	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		MovieDatabase db = new MovieDatabase();

		while (true)
		{
			String input = null;
			try
			{
				input = br.readLine().trim();

				if (input.isEmpty())
					continue;

				/*previous source code - delete comments
				String[] inputs = input.split(" ", 2);

				String cmd = inputs[0].toUpperCase();

				if (cmd.equals("QUIT"))
					break;

				Command command = commands.get(cmd);

				if (command == null)
					throw new CommandNotFoundException(cmd);

				String arguments = inputs.length > 1 ? inputs[1] : "";
				*/

				//fixed ver.
				int cmdindex = input.indexOf("%");
				String cmd = cmdindex < 0 ? input.toUpperCase() : input.substring(0, cmdindex).toUpperCase();
				cmd = cmd.trim();

				if (cmd.equals("QUIT"))
					break;

				Command command = commands.get(cmd);

				if (command == null)
					throw new CommandNotFoundException(cmd);

				String arguments = cmdindex < 0 ? "" : input.substring(cmdindex);
				//end

				command.apply(db, arguments);
			} catch (CommandParseException e) {
				System.err.printf("command parse failure: %s [cmd=%s, input=%s]\n",
						e.getMessage(), e.getCommand(), e.getInput());
				e.printStackTrace(System.err);
			} catch (CommandNotFoundException e) {
				System.err.printf("command not found: %s\n", e.getCommand());
				e.printStackTrace(System.err);
			} catch (Exception e) {
				System.out.printf("unexpected exception with input: [%s]\n", input);
				e.printStackTrace(System.err);
			}
		}
	}

}
