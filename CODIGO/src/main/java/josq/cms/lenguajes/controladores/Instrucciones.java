/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import josq.cms.archivos.MyFile;
import josq.cms.archivos.Ruta;
import josq.cms.lenguajes.automatas.modelos.Indicador;
import josq.cms.lenguajes.automatas.modelos.cup.simbolos.Accion;
import josq.cms.web.modelos.Componente;
import josq.cms.web.modelos.Pagina;
import josq.cms.web.modelos.Sitio;

/**
 *
 * @author JavierOswaldo
 */
public class Instrucciones
{
    // get acciones[]
    // crear modelos web
    // guardar modelos web segun el tipo de accion requerida
    // opcion ejecutar instrucciones
    
    ArrayList<Sitio> newSitios;
    ArrayList<Sitio> delSitios;
    ArrayList<Pagina> newPaginas;
    ArrayList<Pagina> delPaginas;
    ArrayList<Pagina> modPaginas;
    ArrayList<Componente> newComponentes;
    ArrayList<Componente> modComponentes;
    ArrayList<Componente> delComponentes;

    public void procesarDesdeArchivo(String file)
    {
        try
        {
            ArrayList<Accion> acciones = Procesar.accionesDesdeArchivo(file);
            for(Accion a : acciones) setWebModel(a);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private void setWebModel(Accion miAccion)
    {
        Indicador tipo = miAccion.getTipo();
        
        switch (tipo)
        {
            case SITE_NEW:
                Sitio newSitio = WebModelFactory.newSitioForCreation(miAccion);
                if (newSitio != null) newSitios.add(newSitio);
                break;
            case SITE_DEL:
                Sitio delSitio = WebModelFactory.newSitioForDeletion(miAccion);
                if (delSitio != null) delSitios.add(delSitio);
                break;
            case PAGE_NEW: 
                Pagina newPagina = WebModelFactory.newPaginaForCreation(miAccion);
                if(newPagina != null) newPaginas.add(newPagina);
                break;
            case PAGE_MOD: 
                Pagina modPagina = WebModelFactory.newPaginaForModification(miAccion);
                if(modPagina != null) modPaginas.add(modPagina);
                break;
            case PAGE_DEL: 
                Pagina delPagina = WebModelFactory.newPaginaForDeletion(miAccion);
                if(delPagina != null) delPaginas.add(delPagina);
                break;
            case COMP_NEW: 
                Componente newComponente = WebModelFactory.newComponente(miAccion);
                if(newComponente != null) newComponentes.add(newComponente);
                break;
            case COMP_MOD: 
                Componente modComponente = WebModelFactory.newComponente(miAccion);
                if(modComponente != null) modComponentes.add(modComponente);
                break;
            case COMP_DEL: 
                Componente delComponente = WebModelFactory.newComponenteForDeletion(miAccion);
                if(delComponente != null) delComponentes.add(delComponente);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void newSitio(Sitio miSitio)
    {
        String ruta = Ruta.cms+miSitio.getIdSite();
        
        try
        {
            File binSitio = new File(ruta); 
            if (binSitio.exists()) return;

            MyFile.writeObjet(ruta, miSitio);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void delSitio(Sitio oldSitio)
    {
        String ruta = Ruta.cms+oldSitio.getIdSite();
        
        try
        {
            File fileSitio = new File(ruta); 
            if (!fileSitio.exists()) return;

            Object rawSitio = MyFile.readObject(ruta);
            boolean isSitio = rawSitio != null && rawSitio instanceof Sitio;
            
            if(!isSitio) return;
            
            ArrayList<Pagina> paginas = ((Sitio)rawSitio).getPaginas();
            for(Pagina p : paginas) delPage(p.getIdPage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
        
    private void newPage(Pagina miPagina)
    {
        String ruta = Ruta.cms+miPagina.getIdPage();
        
        try
        {
            File filePagina = new File(ruta); 
            if (filePagina.exists()) return;

            MyFile.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private void modPage(Pagina newPagina)
    {
        String ruta = Ruta.cms+newPagina.getIdPage();
        
        try
        {
            File filePagina = new File(ruta);
            if (!filePagina.exists()) return;

            Object rawPagina = MyFile.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina oldPagina = (Pagina) rawPagina;
            if(newPagina.getTitle() != null) oldPagina.setTitle(newPagina.getTitle());
            if(newPagina.getEtiquetas() != null && !newPagina.getEtiquetas().isEmpty()) 
            {
                oldPagina.getEtiquetas().clear();
                oldPagina.getEtiquetas().addAll(newPagina.getEtiquetas());
            }
            filePagina.delete();
            
            MyFile.writeObjet(ruta, oldPagina);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private void delPage(Pagina miPagina)
    {
        delPage(miPagina.getIdPage());
    }
    
    private void delPage(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File filePagina = new File(ruta); 
            if (!filePagina.exists()) return;
            
            Object rawPagina = MyFile.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            // si el objeto leido no es una pagina, puede que sea un sitio: NO ELIMINAR EL ARCHIVO
            if(!isPagina) return; 
            
            Set<String> subPaginas = ((Pagina)rawPagina).getPaginas().keySet();
            for(String p : subPaginas) delPage(p);
            
            filePagina.delete();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    void newComp(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPage = new File(ruta); 
            if (!binPage.exists()) return;
            
            Object rawPagina = MyFile.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> componentes = miPagina.getComponentes().keySet();
            if (componentes.contains(miComp.getIdComponente())) return;

            miPagina.addComponente(miComp.getIdComponente(), miComp.getWidget());

            binPage.delete();
            MyFile.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void modComp(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPagina = new File(ruta); 
            if (!binPagina.exists()) return;
            
            Object rawPagina = MyFile.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> componentes = miPagina.getComponentes().keySet();
            if (!componentes.contains(miComp.getIdComponente())) return;

            miPagina.putComponente(miComp.getIdComponente(), miComp.getWidget());

            binPagina.delete();
            MyFile.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void delComp(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPagina = new File(ruta); 
            if (!binPagina.exists()) return;
            
            Object rawPagina = MyFile.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> componentes = miPagina.getComponentes().keySet();
            if (!componentes.contains(miComp.getIdComponente())) return;

            miPagina.delComponente(miComp.getIdComponente());

            binPagina.delete();
            MyFile.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
