package business;

public class Notification
{
	private String msg;
	private boolean urgent;
	
	/**
	 * 
	 * @param m Notification message to be sent
	 * @param u Whether this is urgent
	 */
	public Notification(String m, boolean u)
	{
		msg = m;
		urgent = u;
	}
}
