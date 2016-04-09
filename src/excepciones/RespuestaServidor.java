package excepciones;

import java.util.ArrayList;

public class RespuestaServidor 
{
	private ArrayList<String> errors;
	private ArrayList<String> warnings;
	
	public RespuestaServidor()
	{
		errors = new ArrayList<String>();
		warnings = new ArrayList<String>();
	}
	
	public ArrayList<String> getErrors() 
	{
		return errors;
	}
	public void setErrors(ArrayList<String> errors) 
	{
		this.errors = errors;
	}
	public ArrayList<String> getWarnings()
	{
		return warnings;
	}
	public void setWarnings(ArrayList<String> warnings)
	{
		this.warnings = warnings;
	}
}
