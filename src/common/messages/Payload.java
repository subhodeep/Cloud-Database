package common.messages;

public class Payload implements KVMessage{

	private static final String LOG = "LOG:PAYLOAD$:";
	private String requestType;
	private String key;
	private String value;
	
	public Payload(String key, String value, String requestType){
		System.out.println(LOG + "creating payload in constructor with <K,V,T>" + key + ":" + value + ":" + requestType);
		this.key = key;
		this.value = value;
		this.requestType = requestType;
	}
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public StatusType getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setKey(String key){
		
	}
	
	public void setValue(String value){
		
	}
	
	public void setRequestType(){
		
	}
	public String getRequestType(){
		return this.requestType;
	}
	

}
