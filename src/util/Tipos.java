package util;

public class Tipos 
{
	public static boolean esEntero(String entrada)
	{
		if(entrada!= null && !entrada.isEmpty())
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
		if(entrada!= null && !entrada.isEmpty())
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
