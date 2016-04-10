package excepciones;

public class ErrorServidor 
{
	//Fields
	private String errorCode, errorMessage, errorDetail;
	private nivelErrorServidor nivelError;
	
	//Constructores
	public ErrorServidor()
	{
	}
	
	public ErrorServidor(Exception ex)
	{
		errorMessage = ex.getMessage();
		errorDetail = ex.toString();
		nivelError = nivelErrorServidor.EXCEPTION;
		errorCode = "";
	}
	
	public ErrorServidor(String errorMessage)
	{
		this.errorMessage = errorMessage;
		nivelError = nivelErrorServidor.VALIDATION_ERROR;
		errorCode = "N/A";
	}
	
	public ErrorServidor(String errorCode, String errorMessage)
	{
		this.errorMessage = errorMessage;
		nivelError = nivelErrorServidor.VALIDATION_ERROR;
		this.errorCode = errorCode;
	}
	
	public ErrorServidor(String errorCode, String errorMessage, nivelErrorServidor nivelError)
	{
		this.errorMessage = errorMessage;
		this.nivelError = nivelError;
		this.errorCode = errorCode;
	}
	
	//Getters - Setters
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public nivelErrorServidor getNivelError() {
		return nivelError;
	}

	public void setNivelError(nivelErrorServidor nivelError) {
		this.nivelError = nivelError;
	}
	
	
}
