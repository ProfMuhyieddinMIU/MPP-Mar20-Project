package business;

public class Notification
{
	private String msg;
	private boolean isUrgent;
	
	/**
	 * 
	 * @param m Notification message to be sent
	 * @param u Whether this is urgent
	 */
	public Notification(String m, boolean u)
	{
		msg = m;
		isUrgent = u;
	}
	
	public String getMessage()
	{
		return msg;
	}
	
	public boolean getUrgency()
	{
		return isUrgent;
	}
}
