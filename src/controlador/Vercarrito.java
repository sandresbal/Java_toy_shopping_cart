package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Articulo;

/**
 * Servlet implementation class Vercarrito
 */
@WebServlet("/Vercarrito")
public class Vercarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Vercarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * El método service se trae el atributo de sesión "lista". Si inicialmente éste
		 * está vacío, se informa al usuario y se le da la oportunidad de volver a la página
		 * de opciones.
		 */
		
		PrintWriter out = response.getWriter();
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("lista") == null) {
			System.out.println("no hay artículos en la cesta");
			out.println("<html><head><title>Carro Vacío</title></head><body>");
			out.println("<h3>No hay artículos en tu cesta");
			out.println("<a href='opciones.html'>Volver atrás</a>");
			out.println("</body></html>");
			out.close();
		/*
		 * Si el atributo de sesión "lista" no está vacío, creamos un objeto ArrayList
		 * que contiene los artículos de la lista y construimos la tabla que permite
		 * visualizar esos artículos, así como las opciones de eliminar cada producto,
		 * borrar el carrito y volver a opciones
		 */
		} else {
			ArrayList<Articulo> lista = (ArrayList<Articulo>) sesion.getAttribute("lista");
			System.out.println("tamaño de la lista:" + lista.size());
			out.println("<html><head><title>Lista</title></head><body>");
			out.println("<table border='1'>");
			out.println("<th>Eliminar ?</th><th>Producto</th><th>Unidades</th><th>Color</th>");
			for (Articulo artic : lista) {
				out.println("<tr><td><a href='Procesar?opcion=eliminar&producto="+ artic.getProducto() + 
						"&unidades=" + artic.getUnidades() + "&color="+ artic.getColor()+ "'>Eliminar</a></td>" + 
						"<td>" + artic.getProducto() + "</td>" + 
						"<td>" + artic.getUnidades() + "</td>" + 
						"<td>" + artic.getColor() + "</td></tr>");
			}
			out.println("</table><br>");
			out.println("<a href='Procesar?opcion=eliminartodo'>Vaciar carrito</a>");
			out.println("<p><a href='opciones.html'>Opciones</p>");
			out.println("</body></html>");
			out.close();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
