package app_kvServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import app_kvClient.Condition;
import app_kvClient.State;
import app_kvClient.StateMachine;
import app_kvClient.Transition;
import common.messages.SystemStates;

public class ServerSystem {

	private final static String LOG = "LOG:SERVRSYS:";
	
	private static boolean isClosed;
	private static boolean isRunning;
	private static boolean isInitialized;
	private static boolean isHalted;
	private static boolean isWriteLocked;
	private static boolean isShutDown;
	
	
	private static ServerContainerModel metadata = new ServerContainerModel();
	/*
	 * TODO: FSM needs to be corrected.
	 */
	public static List<String> validCommands = new ArrayList<String>();
	public enum ValidCommands{
		GET,
		PUT,
		ADD,
		REMOVE
	}

	public static void initializeSystem(){
		System.out.println(LOG + "initializing commands...");
		initializeCommands();
		
		System.out.println(LOG + "valid Server commands : " + validCommands);
		//initializeFSM();	
	}
	
	private static void initializeCommands(){
		validCommands.add("GET");
		validCommands.add("PUT");
		/*
		 * TODO add remaining commands.
		 */
	}
	
	private StateMachine stateMachine;
	//public static boolean isInit;
	//public static boolean isCmdInit;
	

	State closed = new State(SystemStates.CLOSED);
	State started = new State(SystemStates.STARTED);
	State initialized = new State(SystemStates.INITIALIZED);
	State halted = new State(SystemStates.HALT);
	State writeLock = new State(SystemStates.WRITE_LOCKED);
	State stopped = new State(SystemStates.STOPPED);

	Condition start = new Condition("sshTriggered");
	Condition init = new Condition("parametersGiven");
	Condition halt = new Condition("haltIssued");
	Condition isWriteLock = new Condition("writeLocked");
	Condition isStopped = new Condition("requestSent");
	
	private void initializeFSM(){
		//System.out.println(LOG + "initializing system");
		List<Transition> transitions = new ArrayList<>();
		transitions.add(new Transition(closed, new HashSet<>(Arrays.asList(start)), started));
		transitions.add(new Transition(started, new HashSet<>(Arrays.asList(init)), initialized));
		transitions.add(new Transition(initialized, new HashSet<>(Arrays.asList(halt)), halted));
		transitions.add(new Transition(initialized, new HashSet<>(Arrays.asList(isWriteLock)), writeLock));
		transitions.add(new Transition(initialized, new HashSet<>(Arrays.asList()), stopped));
		transitions.add(new Transition(halted, new HashSet<>(Arrays.asList(init)), initialized));
		transitions.add(new Transition(halted, new HashSet<>(Arrays.asList(isWriteLock)), writeLock));
		transitions.add(new Transition(halted, new HashSet<>(Arrays.asList()), stopped));
		transitions.add(new Transition(writeLock, new HashSet<>(Arrays.asList(init)), initialized));
		transitions.add(new Transition(writeLock, new HashSet<>(Arrays.asList(halt)), halted));
		transitions.add(new Transition(writeLock, new HashSet<>(Arrays.asList()), stopped));
		
		stateMachine = new StateMachine(closed, transitions);
	}
	
	public boolean isValidTransition(State toState){

		boolean valid = false;
		State curr = stateMachine.getCurrent();
		switch(toState.getState()){
		case CLOSED:
			break;
		case STARTED:
			valid = (curr.getState() == SystemStates.CLOSED);
			break;
		case INITIALIZED:
			valid = (curr.getState() == SystemStates.STARTED);
			break;
		case HALT:
			valid = (curr.getState() != SystemStates.STARTED);
			break;
		case WRITE_LOCKED:
			valid = (curr.getState() != SystemStates.STARTED);
			break;
		case STOPPED:
			valid = (curr.getState() != SystemStates.CLOSED);
			break;
		default:
			break;

		}
		return valid;
	}
	
	/*public void updateState(){
		State curr = stateMachine.getCurrent();
		switch(curr.getState()){
		case CLOSED:
			stateMachine.apply(new HashSet<>(Arrays.asList(start)));
			break;
		case STARTED:
			stateMachine.apply(new HashSet<>(Arrays.asList(init)));
			break;
		case INITIALIZED:
			stateMachine.apply(new HashSet<>(Arrays.asList(cmdReady)));
			break;
		case READY:
			stateMachine.apply(new HashSet<>(Arrays.asList(connect)));
			break;
		case RUNNING:
			break;
		case TIMED_WAIT:
			break;
		case WAIT:
			break;
		default:
			break;
	
		}
	}*/
	
	public static ServerContainerModel getMetadata(){
		
		return metadata;
		
	}

	public static boolean isClosed() {
		return isClosed;
	}

	public static void setClosed(boolean isClosed) {
		ServerSystem.isClosed = isClosed;
	}

	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		ServerSystem.isRunning = isRunning;
	}

	public static boolean isInitialized() {
		return isInitialized;
	}

	public static void setInitialized(boolean isInitialized) {
		ServerSystem.isInitialized = isInitialized;
	}

	public static boolean isHalted() {
		return isHalted;
	}

	public static void setHalted(boolean isHalted) {
		ServerSystem.isHalted = isHalted;
	}

	public static boolean isWriteLocked() {
		return isWriteLocked;
	}

	public static void setWriteLocked(boolean isWriteLocked) {
		ServerSystem.isWriteLocked = isWriteLocked;
	}

	public static boolean isShutDown() {
		return isShutDown;
	}

	public static void setShutDown(boolean isShutDown) {
		ServerSystem.isShutDown = isShutDown;
	}
}
