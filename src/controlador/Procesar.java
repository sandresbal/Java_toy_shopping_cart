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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession sesion = request.getSession();
		//sesion.getAttribute("lista");
		String opcion = request.getParameter("opcion");
		PrintWriter out = response.getWriter();
		ArrayList<Articulo> lista = null;
		switch (opcion) {
			case "comprar":
				if (sesion.getAttribute("lista") == null) {
					lista = new ArrayList<Articulo>();
					System.out.println("creamos lista, no la había");
					}
				else {
					System.out.println("seteamos lista existente");
				}
			rd = request.getRequestDispatcher("comprar.html");
			rd.forward(request, response);
			break;
		case "vercarrito":
			sesion.getAttribute("lista");
			rd = request.getRequestDispatcher("Vercarrito");
			rd.forward(request, response);
			break;
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
