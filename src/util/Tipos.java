package util;

public class Tipos 
{
	public static boolean esEntero(String entrada)
	{
		if(!entrada.isEmpty() && entrada!= null)
		{			
			try
			{
				Integer.parseInt(entrada);
			}
			catch(NumberFormatException e)
			{
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public static boolean esFloat(String entrada)
	{
		if(!entrada.isEmpty() && entrada!= null)
		{			
			try
			{
				Float.parseFloat(entrada);
			}
			catch(NumberFormatException e)
			{
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}
