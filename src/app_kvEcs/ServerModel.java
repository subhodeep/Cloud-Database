package app_kvEcs;

/**
 * @author Anant
 *
 */
public class ServerModel {
private String Name;
private String IP;
private int Port;
private int cacheSize;
private String cacheStrategy;
private int HashValue;
private int StartIndex, EndIndex;

public ServerModel(String name, String iP, int port) {
	
	this.Name = name;
	this.IP = iP;
	this.Port = port;

}
public ServerModel(String name, String iP, int port, int hashValue, int startIndex, int endIndex) {
	
	this.Name = name;
	this.IP = iP;
	this.Port = port;
	this.HashValue = hashValue;
	this.StartIndex = startIndex;
	this.EndIndex = endIndex;
}


/**
 * @return the name
 */
public String getName() {
	return Name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	Name = name;
}
/**
 * @return the iP
 */
public String getIP() {
	return IP;
}
/**
 * @param iP the iP to set
 */
public void setIP(String iP) {
	IP = iP;
}
/**
 * @return the port
 */
public int getPort() {
	return Port;
}
/**
 * @param port the port to set
 */
public void setPort(int port) {
	Port = port;
}

public String getCacheStrategy() {
	return cacheStrategy;
}
public void setCacheStrategy(String cacheStrategy) {
	this.cacheStrategy = cacheStrategy;
}
/**
 * @return the hashValue
 */
public int getHashValue() {
	return HashValue;
}
/**
 * @param hashValue the hashValue to set
 */
public void setHashValue(int hashValue) {
	HashValue = hashValue;
}
/**
 * @return the startIndex
 */
public int getStartIndex() {
	return StartIndex;
}
/**
 * @param startIndex the startIndex to set
 */
public void setStartIndex(int startIndex) {
	StartIndex = startIndex;
}
/**
 * @return the endIndex
 */
public int getEndIndex() {
	return EndIndex;
}
/**
 * @param endIndex the endIndex to set
 */
public void setEndIndex(int endIndex) {
	EndIndex = endIndex;
}


}

