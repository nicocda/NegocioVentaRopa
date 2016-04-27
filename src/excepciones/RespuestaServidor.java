package excepciones;

import java.util.ArrayList;

public class RespuestaServidor extends Exception 
{
	private static final long serialVersionUID = 1L;
	private ArrayList<ErrorServidor> errors;
	private int returnId;
	private String returnCode, returnName;
	boolean status;
	
	//Constructores
	public RespuestaServidor()
	{
		errors = new ArrayList<ErrorServidor>();
		returnId = -1;
		returnCode = "";
		returnName = "";
	}
	
	//Métodos
	
	public void addError(Exception ex)
	{
		errors.add(new ErrorServidor(ex));
	}
	
	public void addError(String errorMessage)
	{
		errors.add(new ErrorServidor(errorMessage));
	}
	
	public void addError(String errorMessage, String errorCode)
	{
		errors.add(new ErrorServidor(errorCode, errorMessage));
	}
	
	public void addError(String errorCode, String errorMessage, nivelErrorServidor nivelError)
	{
		errors.add(new ErrorServidor(errorCode, errorMessage, nivelError));
	}
	
	@Override
	public String toString()
	{
		String rsp ="";
		rsp += "Status: " + status;
		rsp += "\nReturn Id: " + returnId;
		rsp += "\nReturn Code: " + returnCode;
		rsp += "\nReturn Name: " + returnName;
		rsp += "\nErrors:\n";
		
		for(ErrorServidor e : errors)
			rsp += " >" + e.getErrorMessage() + " / " + e.getErrorDetail();
		
		rsp += "\n";
		
		return rsp;
	}
	//Getters - Setters
	
	public boolean getStatus()
	{
		for(ErrorServidor e : errors)
		{
			if (e.getNivelError() == nivelErrorServidor.EXCEPTION || e.getNivelError() == nivelErrorServidor.VALIDATION_ERROR)
			{
				status=false;
				return status;
			}
		}
		status = true;
		return status;
	}
	
	public ArrayList<ErrorServidor> getErrors() 
	{
		return errors;
	}

	public void setErrors(ArrayList<ErrorServidor> errors) 
	{
		this.errors = errors;
	}

	public int getReturnId() 
	{
		return returnId;
	}

	public void setReturnId(int returnId)
	{
		this.returnId = returnId;
	}

	public String getReturnCode() 
	{
		return returnCode;
	}

	public void setReturnCode(String returnCode)
	{
		this.returnCode = returnCode;
	}

	public String getReturnName()
	{
		return returnName;
	}

	public void setReturnName(String returnName) 
	{
		this.returnName = returnName;
	}
	
}
