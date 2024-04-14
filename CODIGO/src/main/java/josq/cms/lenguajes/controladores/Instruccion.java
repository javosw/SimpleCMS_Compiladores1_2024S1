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
public class Instruccion
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

    public Instruccion()
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

    public void procesarDesdeArchivo(String file)
    {
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
            System.out.print("@procesarDesdeArchivo > catch: ");
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
    
    private void exeNewSitio(Sitio miSitio)
    {
        String ruta = Ruta.cms+miSitio.getIdSite();
        
        try
        {
            File binSitio = new File(ruta); 
            if (binSitio.exists()) return;
            MiArchivo.writeObjet(ruta, miSitio);

            Pagina rootPagina = new Pagina(miSitio.getIdPageRoot(), miSitio.getIdSite());
            newPaginaRoot(rootPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@exeNewSitio: ");
            System.out.println(ex.getMessage());
        }
    }
    private void newPaginaRoot(Pagina miPagina)
    {
        String ruta = Ruta.cms+miPagina.getIdPage();
        
        try
        {
            File filePagina = new File(ruta); 
            if (filePagina.exists()) filePagina.delete();

            MiArchivo.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@newRootPagina: ");
            System.out.println(ex.getMessage());
        }
    }
    private void exeDelSitio(Sitio miSitio)
    {
        String ruta = Ruta.cms+miSitio.getIdSite();
        
        try
        {
            File sitioFile = new File(ruta); 
            if (!sitioFile.exists()) return;

            Object sitioRaw = MiArchivo.readObject(ruta);
            boolean isSitio = sitioRaw != null && sitioRaw instanceof Sitio;
            
            if(!isSitio) return;
            
            delPagina(((Sitio)sitioRaw).getIdPageRoot());
            sitioFile.delete();
        }
        catch (Exception ex)
        {
            System.out.print("@exeDelSitio: ");
            System.out.println(ex.getMessage());
        }
    }
    private void exeNewPagina(Pagina miPagina)
    {
        String ruta = Ruta.cms+miPagina.getIdPage();
        
        try
        {
            File filePagina = new File(ruta); 
            if (filePagina.exists()) return;

            if(miPagina.getIdPageRoot()==null) addSubPagina(miPagina.getIdPage(), "~"+miPagina.getIdSite());
            else addSubPagina(miPagina.getIdPage(), miPagina.getIdPageRoot());

            MiArchivo.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@exeNewPagina: ");
            System.out.println(ex.getMessage());
        }
    }
    private void addSubPagina(String idSubPagina,String idPagina)
    {
        String ruta = Ruta.cms+idPagina;
        try
        {
            File filePagina = new File(ruta);
            if (!filePagina.exists()) return;

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina miPagina = (Pagina)rawPagina;
            miPagina.addPagina(idSubPagina); 
            
            filePagina.delete();
            MiArchivo.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@addSubPagina: ");
            System.out.println(ex.getMessage());
        }

    }
    
    private void exeModPagina(Pagina newPagina)
    {
        String ruta = Ruta.cms+newPagina.getIdPage();
        
        try
        {
            File filePagina = new File(ruta);
            if (!filePagina.exists()) return;

            Object rawPagina = MiArchivo.readObject(ruta);
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
            
            MiArchivo.writeObjet(ruta, oldPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@exeModPagina: ");
            System.out.println(ex.getMessage());
        }
    }
    
    private void exeDelPagina(Pagina miPagina)
    {
        delPagina(miPagina.getIdPage());
    }
    
    private void delPagina(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File filePagina = new File(ruta); 
            if (!filePagina.exists()) return;
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            // si el objeto leido no es una pagina, puede que sea un sitio: NO ELIMINAR EL ARCHIVO
            if(!isPagina) return; 
            
            Set<String> subPaginas = ((Pagina)rawPagina).getPaginas();
            for(String p : subPaginas) delPagina(p);
            
            filePagina.delete();
        }
        catch (Exception ex)
        {
            System.out.print("@delPagina: ");
            System.out.println(ex.getMessage());
        }
    }
    
    void exeNewComponente(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPage = new File(ruta); 
            if (!binPage.exists()) return;
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> componentes = miPagina.getComponentes().keySet();
            if (componentes.contains(miComp.getIdComponente())) return;

            miPagina.addComponente(miComp.getIdComponente(), miComp.getWidget());

            binPage.delete();
            MiArchivo.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@exeNewComponente: ");
            System.out.println(ex.getMessage());
        }
    }

    void exeModComponente(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPagina = new File(ruta); 
            if (!binPagina.exists()) return;
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> componentes = miPagina.getComponentes().keySet();
            if (!componentes.contains(miComp.getIdComponente())) return;

            miPagina.putComponente(miComp.getIdComponente(), miComp.getWidget());

            binPagina.delete();
            MiArchivo.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@exeModComponente: ");
            System.out.println(ex.getMessage());
        }
    }

    void exeDelComponente(Componente miComp)
    {
        String ruta = Ruta.cms+miComp.getIdPagina();
        
        try
        {
            File binPagina = new File(ruta); 
            if (!binPagina.exists()) return;
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            if(!isPagina) return;
            
            Pagina miPagina = (Pagina)rawPagina;
            Set<String> componentes = miPagina.getComponentes().keySet();
            if (!componentes.contains(miComp.getIdComponente())) return;

            miPagina.delComponente(miComp.getIdComponente());

            binPagina.delete();
            MiArchivo.writeObjet(ruta, miPagina);
        }
        catch (Exception ex)
        {
            System.out.print("@exeDelComponente: ");
            System.out.println(ex.getMessage());
        }
    }

}
