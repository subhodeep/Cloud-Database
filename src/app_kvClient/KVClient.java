package app_kvClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class KVClient {

	private boolean stop = false;
	private static final String PROMPT = "EchoClient> ";
	private BufferedReader stdin;
	private ClientView clientView = new ClientView();
	private final static String LOG = "LOG:KVCLIENT:";
	
	private void run(){
		
		CommandController.initializeCommands();
		System.out.println(LOG + "the valid commands [command=type] are : " + CommandController.getValidCommands());
		while(!stop){
			stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(PROMPT);
			CommandModel command = buildCommand(stdin);
			System.out.println(LOG + "class of command after building to check polymorphism: " + command.getClass().getSimpleName());
			
			// TODO spawning a new thread for each command is better?
			CommandController cmdController = new CommandController(command, clientView);
			cmdController.initProcessing();
			cmdController.updateView();
		}
	}
	
	private CommandModel buildCommand(BufferedReader stdin){
		CommandModel command = null;
		try {
			String cmdLine = stdin.readLine();
			System.out.println(LOG + "cmdLine : " + cmdLine);
			command = new CommandController().buildCommand(cmdLine);
			System.out.println(LOG + "command type : " + command.getClass().getSimpleName());
			return command;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
		
	}
	public static void main(String[] args){
		KVClient clientApp = new KVClient();
		
		System.out.println(LOG + "Running Client Application");
		clientApp.run();
	}
}
