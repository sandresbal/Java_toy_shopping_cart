package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Articulo;

/**
 * Servlet implementation class Procesar
 */
@WebServlet("/Procesar")
public class Procesar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Procesar() {
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
		 * Este método gestiona las peticiones de la página de opciones (comprar o
		 * listar los productos del carrito) así como las las del propio listado
		 * de productos
		 */

		RequestDispatcher rd = null;
		HttpSession sesion = request.getSession();
		String opcion = request.getParameter("opcion");
		PrintWriter out = response.getWriter();
		ArrayList<Articulo> lista = null;

		/*
		 * Si el usuario quiere comenzar su compra, comprobamos que si hay una lista
		 * previa como atributo de la sesión. Si no la hay, creamos un objeto ArrayList
		 * que contendrá artículos. En cualquier caso, le dirigimos a la página con el
		 * formulario de compra
		 */

		switch (opcion) {
		case "comprar":
			if (sesion.getAttribute("lista") == null) {
				lista = new ArrayList<Articulo>();
				System.out.println("creamos lista, no la había");
			} else {
				System.out.println("vamos a setear la lista existente");
			}
			rd = request.getRequestDispatcher("comprar.html");
			rd.forward(request, response);
			break;

		/*
		 * Si el usuario quiere ver su carrito, le llevamos al servlet que generará la
		 * lista de productos
		 */
			
		case "vercarrito":
			rd = request.getRequestDispatcher("Vercarrito");
			rd.forward(request, response);
			break;

		/*
		 * La opción de eliminar un producto concreto desde la lista de productos se
		 * trae la lista de allí. Construimos un nuevo artículo con los argumentos que
		 * nos traemos como parámetros (los del artículo que queremos eliminar).
		 * Después, lo eliminamos de la lista y volvemos a la página del carrito
		 */

		case "eliminar":
			lista = (ArrayList<Articulo>) sesion.getAttribute("lista");
			Articulo para_eliminar = new Articulo(request.getParameter("producto"),
					Integer.parseInt(request.getParameter("unidades")), request.getParameter("color"));
			lista.remove(para_eliminar);
			rd = request.getRequestDispatcher("Vercarrito");
			rd.forward(request, response);
			break;
		/*
		 * La opción de eliminar todo, quita el atributo "lista" de la sesión y hace que
		 * el usuario vuelva a la página de opciones
		 */
		case "eliminartodo":
			sesion.removeAttribute("lista");
			sesion.invalidate();
			rd = request.getRequestDispatcher("opciones.html");
			rd.forward(request, response);
			break;
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
