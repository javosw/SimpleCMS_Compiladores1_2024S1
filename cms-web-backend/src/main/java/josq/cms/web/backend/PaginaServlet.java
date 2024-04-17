/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package josq.cms.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;
import java.util.Set;
import josq.cms.archivos.MiArchivo;
import josq.cms.archivos.Ruta;
import josq.cms.web.modelos.Pagina;

/**
 *
 * @author JavierOswaldo
 */
public class PaginaServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaginaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaginaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String idPagina = request.getParameter("id");
        if (idPagina == null || !existePagina(idPagina))
        {
            processRequest(request, response); 
            return;
        }
        System.out.println("@existePagina");
    }
    private boolean existePagina(String idPagina)
    {
        String ruta = Ruta.cms+idPagina;
        
        try
        {
            File paginaFile = new File(ruta);
            if (!paginaFile.exists()) return false;

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            return isPagina;
        }
        catch (Exception ex)
        {
            System.out.print("@existePagina: ");
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    // FINALIZADO
    private void exeModPagina(String idPagina)
    {
        String ruta = Ruta.cms+idPagina;
        
        try
        {
            Object rawPagina = MiArchivo.readObject(ruta);
            Pagina miPagina = (Pagina) rawPagina;
            
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>");
            
            String idPage = miPagina.getIdPage();
            String idPageRoot = miPagina.getIdPageRoot();
            String idSite = miPagina.getIdSite();

            String title = miPagina.getTitle();

            Set<String> paginas = miPagina.getPaginas();
            Map<String,Object> componentes = miPagina.getComponentes();
            Set<String> etiquetas = miPagina.getEtiquetas();
        }
        catch (Exception ex)
        {
            System.out.print("@exeModPagina: ");
            System.out.println(ex.getMessage());
        }
    }
    
    String getHTML()
    {
        return "";
    }

    
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
