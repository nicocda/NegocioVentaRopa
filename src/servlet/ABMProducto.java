package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CatalogoClientes;

@WebServlet("/ABMProducto")
public class ABMProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ABMProducto() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/*if(request.getParameter("addProd")!=null)
		{
			char tipo=request.getParameter("tipo").charAt(0);
			char subTipo=request.getParameter("subTipo").charAt(0);
			int cod =Integer.parseInt(request.getParameter("cod"));
			String descripcion = request.getParameter("desc");
			String codigoCompleto= Character.toString(tipo)+String.valueOf(subTipo)+Integer.toString(cod);
			request.setAttribute("desc", descripcion);
			request.setAttribute("codigo", codigoCompleto);
			request.getRequestDispatcher("codigoBarra.jsp").forward(request, response);;
		}*/
		CatalogoClientes cc = new CatalogoClientes();
		request.setAttribute("listaClientes", cc.buscarClientes());
		request.getRequestDispatcher("AbmClientes.jsp").forward(request, response);
	}

}
