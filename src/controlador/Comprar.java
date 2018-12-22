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
 * Servlet implementation class Comprar
 */
@WebServlet("/Comprar")
public class Comprar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comprar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service (HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
    	ArrayList<Articulo> lista = null;
    	HttpSession sesion = request.getSession();
    	//ArrayList<Articulo> lista = (ArrayList<Articulo>)sesion.getAttribute("lista");
		if (sesion.getAttribute("lista") == null) {
			lista = new ArrayList<Articulo>();
			System.out.print("el programa me dice que la lista está vacía");
		}
		else {
	    	lista = (ArrayList<Articulo>)sesion.getAttribute("lista");
		}
    	String name = request.getParameter("producto");
    	int unidades = Integer.parseInt(request.getParameter("unidades"));
    	String color = request.getParameter("color");
    	Articulo art = new Articulo(name, unidades, color);
    	
    	System.out.println("Añadido " + art.toString());

    	lista.add(art);
    	
    	System.out.println("tamaño de la cesta:" +lista.size());
    	System.out.println("Primer elemento:" +lista.get(0));

    	sesion.setAttribute("lista", lista);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("opciones.html");
    	rd.forward(request, response);

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
