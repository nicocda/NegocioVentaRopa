package datos;

import java.util.ArrayList;

import entidades.EventLog;

public class CatalogoEventLog extends CatalogoBase
{	
	@SuppressWarnings("unchecked")
	public static ArrayList<EventLog> buscarTodosEventLog()
	{
		abrirEntityManager();
		try
		{
			return (ArrayList<EventLog>)getEm().createQuery("SELECT e FROM EventLog e").getResultList();
		}
		finally
		{
			cerrarEntityManager();
		}
	}

}
