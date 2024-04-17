/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import josq.cms.archivos.MiArchivo;
import josq.cms.archivos.Ruta;
import josq.cms.lenguajes.automatas.modelos.Indicador;
import josq.cms.lenguajes.automatas.modelos.cup.Accion;
import josq.cms.web.modelos.Componente;
import josq.cms.web.modelos.Pagina;
import josq.cms.web.modelos.Sitio;

/**
 *
 * @author JavierOswaldo
 */
public class EjecutarAcciones
{
    public final static StringBuilder logGramaticas = new StringBuilder();
    public final static StringBuilder logResultados = new StringBuilder();
    public final static StringBuilder logErrores = new StringBuilder();

    public static void clearLogs()
    {
        logGramaticas.delete(0, logGramaticas.length());
        logResultados.delete(0, logResultados.length());
        logErrores.delete(0, logErrores.length());
    }
    
    ArrayList<Sitio> newSitios;
    ArrayList<Sitio> delSitios;
    ArrayList<Pagina> newPaginas;
    ArrayList<Pagina> delPaginas;
    ArrayList<Pagina> modPaginas;
    ArrayList<Componente> newComponentes;
    ArrayList<Componente> modComponentes;
    ArrayList<Componente> delComponentes;

    public EjecutarAcciones()
    {
        this.newSitios = new ArrayList<>();
        this.delSitios = new ArrayList<>();
        this.newPaginas = new ArrayList<>();
        this.delPaginas = new ArrayList<>();
        this.modPaginas = new ArrayList<>();
        this.newComponentes = new ArrayList<>();
        this.modComponentes = new ArrayList<>();
        this.delComponentes = new ArrayList<>();
    }

    void clearAcciones()
    {
        newSitios.clear();
        newSitios.clear();
        delSitios.clear();
        newPaginas.clear();
        delPaginas.clear();
        modPaginas.clear();
        newComponentes.clear();
        modComponentes.clear();
        delComponentes.clear();
    }
    
    public void procesarDesdeString(String texto)
    {
        clearAcciones();
        try
        {
            System.out.println("@procesarDesdeString > Procesar.procesarDesdeString: ");
            ArrayList<Accion> acciones = Procesar.accionesDesdeString(texto);
            System.out.println("@procesarDesdeString > setWebModel");
            for(Accion a : acciones) getWebModel(a);
            System.out.println("@procesarDesdeString > ejecutarAcciones");
            ejecutarAcciones();
            System.out.println("@procesarDesdeString <");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@procesarDesdeString\n");
            System.out.println(ex.getMessage());
        }
    }

    public void procesarDesdeArchivo(String file)
    {
        clearAcciones();
        try
        {
            System.out.println("@procesarDesdeArchivo > Procesar.accionesDesdeArchivo: ");
            ArrayList<Accion> acciones = Procesar.accionesDesdeArchivo(file);
            System.out.println("@procesarDesdeArchivo > setWebModel");
            for(Accion a : acciones) getWebModel(a);
            System.out.println("@procesarDesdeArchivo > ejecutarAcciones");
            ejecutarAcciones();
            System.out.println("@procesarDesdeArchivo <");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@procesarDesdeArchivo\n");
            System.out.println(ex.getMessage());
        }
    }
    
    void ejecutarAcciones()
    {
        // orden de ejecucion de acciones: del -> new -> mod  
        for(Sitio s : delSitios) exeDelSitio(s);
        for(Sitio s : newSitios) exeNewSitio(s);
        for(Pagina p : delPaginas) exeDelPagina(p);
        for(Pagina p : newPaginas) exeNewPagina(p);
        for(Pagina p : modPaginas) exeModPagina(p);
        for(Componente c : delComponentes) exeDelComponente(c);
        for(Componente c : newComponentes) exeNewComponente(c);
        for(Componente c : modComponentes) exeModComponente(c);
    }
    
    private void getWebModel(Accion miAccion)
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
        }
    }
    
    // FINALIZADO
    private void exeNewSitio(Sitio miSitio)
    {
        String ruta = Ruta.cms+miSitio.getIdSite();
        
        try
        {
            File binSitio = new File(ruta); 
            if (binSitio.exists()) throw new Exception(ruta);
            MiArchivo.writeObjet(ruta, miSitio);

            Pagina rootPagina = new Pagina(miSitio.getIdPageRoot(), miSitio.getIdSite());
            newPaginaRoot(rootPagina);
            
            EjecutarAcciones.logResultados.append("@exeNewSitio: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@exeNewSitio: ").append(ex.getMessage()).append("\n");
        }
    }
    // FINALIZADO 
    private void newPaginaRoot(Pagina miPagina)
    {
        String ruta = Ruta.cms+miPagina.getIdPage();
        
        try
        {
            File filePagina = new File(ruta); 
            if (filePagina.exists()) filePagina.delete();

            MiArchivo.writeObjet(ruta, miPagina);
            EjecutarAcciones.logResultados.append("@newPaginaRoot: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@newPaginaRoot: ").append(ex.getMessage()).append("\n");
        }
    }
    // FINALIZADO
    private void exeDelSitio(Sitio miSitio)
    {
        String ruta = Ruta.cms+miSitio.getIdSite();
        
        try
        {
            File sitioFile = new File(ruta); 
            if (!sitioFile.exists()) throw new Exception(ruta);

            Object sitioRaw = MiArchivo.readObject(ruta);
            boolean isSitio = sitioRaw != null && sitioRaw instanceof Sitio;
            
            if(!isSitio) throw new Exception(ruta);
            
            delPagina(((Sitio)sitioRaw).getIdPageRoot());
            sitioFile.delete();
            EjecutarAcciones.logResultados.append("@exeDelSitio: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@exeDelSitio: ").append(ex.getMessage()).append("\n");
        }
    }
    // FINALIZADO
    private void exeNewPagina(Pagina miPagina)
    {
        String ruta = Ruta.cms+miPagina.getIdPage();
        
        try
        {
            File filePagina = new File(ruta); 
            if (filePagina.exists()) throw new Exception(ruta);

            if(miPagina.getIdPageRoot()==null) addSubPagina(miPagina.getIdPage(), "~"+miPagina.getIdSite());
            else addSubPagina(miPagina.getIdPage(), miPagina.getIdPageRoot());

            MiArchivo.writeObjet(ruta, miPagina);
            EjecutarAcciones.logResultados.append("@exeNewPagina: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@exeNewPagina: ").append(ex.getMessage()).append("\n");
        }
    }
    // FINALIZADO
    private void addSubPagina(String idSubPagina,String idPagina)
    {
        String ruta = Ruta.cms+idPagina;
        try
        {
            File filePagina = new File(ruta);
            if (!filePagina.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) throw new Exception(ruta);
            
            Pagina miPagina = (Pagina)rawPagina;
            miPagina.addPagina(idSubPagina); 
            
            filePagina.delete();
            MiArchivo.writeObjet(ruta, miPagina);
            EjecutarAcciones.logResultados.append("@addSubPagina: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@addSubPagina: ").append(ex.getMessage()).append("\n");
        }

    }

    // FINALIZADO
    private void exeModPagina(Pagina newPagina)
    {
        String ruta = Ruta.cms+newPagina.getIdPage();
        
        try
        {
            File paginaFile = new File(ruta);
            if (!paginaFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) throw new Exception(ruta);
            
            Pagina oldPagina = (Pagina) rawPagina;
            if(newPagina.getTitle() != null) oldPagina.setTitle(newPagina.getTitle());
            if(newPagina.getEtiquetas() != null && !newPagina.getEtiquetas().isEmpty()) 
            {
                oldPagina.getEtiquetas().clear();
                oldPagina.getEtiquetas().addAll(newPagina.getEtiquetas());
            }
            
            paginaFile.delete();
            MiArchivo.writeObjet(ruta, oldPagina);
            EjecutarAcciones.logResultados.append("@exeModPagina: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@exeModPagina: ").append(ex.getMessage()).append("\n");
        }
    }
    
    // FINALIZADO
    private void exeDelPagina(Pagina miPagina)
    {
        delPagina(miPagina.getIdPage());
    }
    
    // FINALIZADO
    private void delPagina(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File filePagina = new File(ruta); 
            if (!filePagina.exists()) throw new Exception(ruta);
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            // si el objeto leido no es una pagina, puede que sea un sitio: NO ELIMINAR EL ARCHIVO
            if(!isPagina) throw new Exception(ruta);
            
            Set<String> subPaginas = ((Pagina)rawPagina).getPaginas();
            for(String p : subPaginas) delPagina(p);
            
            filePagina.delete();
            EjecutarAcciones.logResultados.append("@delPagina: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@delPagina: ").append(ex.getMessage()).append("\n");
        }
    }
    
    // FINALIZADO
    void exeNewComponente(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPage = new File(ruta); 
            if (!binPage.exists()) return;
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) throw new Exception(ruta);
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> idsComponentes = miPagina.getComponentes().keySet();
            if (idsComponentes.contains(miComp.getIdComponente())) throw new Exception(ruta);

            miPagina.addComponente(miComp.getIdComponente(), miComp.getWidget());

            binPage.delete();
            MiArchivo.writeObjet(ruta, miPagina);
            EjecutarAcciones.logResultados.append("@exeNewComponente: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@exeNewComponente: ").append(ex.getMessage()).append("\n");
        }
    }

    void exeModComponente(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPagina = new File(ruta); 
            if (!binPagina.exists()) throw new Exception(ruta);
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) throw new Exception(ruta);
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> idsComponentes = miPagina.getComponentes().keySet();
            if (!idsComponentes.contains(miComp.getIdComponente())) throw new Exception(ruta);

            miPagina.putComponente(miComp.getIdComponente(), miComp.getWidget());

            binPagina.delete();
            MiArchivo.writeObjet(ruta, miPagina);
            EjecutarAcciones.logResultados.append("@exeModComponente: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@exeModComponente: ").append(ex.getMessage()).append("\n");
        }
    }

    // FINALIZADO
    void exeDelComponente(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPagina = new File(ruta); 
            if (!binPagina.exists()) throw new Exception(ruta);
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) throw new Exception(ruta);
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> idsComponentes = miPagina.getComponentes().keySet();
            if (!idsComponentes.contains(miComp.getIdComponente())) throw new Exception(ruta);

            miPagina.delComponente(miComp.getIdComponente());

            binPagina.delete();
            MiArchivo.writeObjet(ruta, miPagina);
            EjecutarAcciones.logResultados.append("@exeDelComponente: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logErrores.append("@exeDelComponente: ").append(ex.getMessage()).append("\n");
        }
    }

}
