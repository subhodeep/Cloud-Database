import java.util.*;
package app_kvEcs;


public class ServerContainerModel {
	private List<ServerModel> ServerList = new ArrayList<ServerModel>();
	
	String Delimiter;//the delimiter of the metadata
	
	private boolean add(ServerModel NewNode)
	{
		this.ServerList.add(NewNode);
	}// appends a node to the serverList array
	private boolean remove(int Index)
	{
		this.ServerList.remove(Index);
	}//remove a node at an arbitary position, 
	private String stringify()
	{
	for (int ctr=0; ctr<this.ServerList.size(); ctr++)	
	{
		StringBuilder builder = new StringBuilder();
		ServerModel Temp= ServerList.get(ctr);
		if(Temp.getName()!=null)
		{
		builder.append(Temp.getName());
		builder.append(",");
		}
		if(Temp.getIP()!=null)
		{
		builder.append(Temp.getIP());
		builder.append(",");
		}
		if(Temp.getPort()>0)
		{
		builder.append(Temp.getPort());
		builder.append(",");
		}
		if(Temp.getStartIndex()>0)
		{
		builder.append(Temp.getStartIndex());
		builder.append(",");
		}
		if(Temp.getEndIndex()>0)
		{
		builder.append(Temp.getEndIndex());
		builder.append(",");
		}
         builder.append("\n");
         return builder.toString();
	}
	}// converts the serverList object into a metadata format for passing to server, delimited with the specified delimiter
	private boolean sortHash()
	{
		Collections.sort(this.ServerList, new Comparator<ServerModel>() {
	        @Override
	        public int compare(ServerModel Server2, ServerModel Server1)
	        {

Integer i = new Integer(Server1.getHashValue()); 
	            return  i.compareTo(Server2.getHashValue();
	        }
	    });
	}//sorts the serverList as per the hash value

	private boolean isSorted()
	{
		
	}//returns true if the serverList is already sorted in ascending order

	private void prepareMetaData()
	{
		for (int ctr=0; ctr<this.ServerList.size(); ctr++)	
		{
		 
		}
    }//populates beginning index and end index, if isSORTED() function returns true;

	//getters and setters for serverList
}
