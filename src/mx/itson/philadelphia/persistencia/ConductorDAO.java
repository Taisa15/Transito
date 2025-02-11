package mx.itson.philadelphia.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.philadelphia.entidades.Conductor;
import mx.itson.philadelphia.presentacion.ConductorListado;
import mx.itson.philadelphia.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author taisa
 */
public class ConductorDAO {

    public static List<Conductor> obtenerTodos() {
        List<Conductor> conductores = new ArrayList<>();
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery< Conductor> criteruaQuery = session.getCriteriaBuilder().createQuery(Conductor.class);
            criteruaQuery.from(Conductor.class);

            conductores = session.createQuery(criteruaQuery).getResultList();

        } catch (Exception ex) {

            System.err.print("Ocurrió un error: " + ex.getMessage());

        }
        return conductores;
    }

    
    /**
     * 
     * 
     * @param nombre
     * @param numeroLicencia
     * @param fechaAlta 
     * @return Indica si el registro fue guardado correctamente
     */
    public static boolean guardar(String nombre, String numeroLicencia, Date fechaAlta) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Conductor c = new Conductor();
            c.setNombre(nombre);
            c.setNumeroLicencia(numeroLicencia);
            c.setFechaAlta(fechaAlta);
            
            session.save(c);
            
            session.getTransaction().commit();
            
            resultado = c.getId() != 0;
        } catch (Exception ex) {
            System.err.print("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }

    public Conductor obtenerPorId(int id) {
        Conductor conductor = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            conductor = session.get(Conductor.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return conductor;

    }

    public boolean editar(int id, String nombre, String numeroLicencia, Date fechaAlta){
       boolean resultado = false;
        try{
             Session session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           Conductor conductor = obtenerPorId(id);
           if(conductor !=null){
               conductor.setNombre(nombre);
               conductor.setFechaAlta(fechaAlta);
               conductor.setNumeroLicencia(numeroLicencia);
             
               session.saveOrUpdate(conductor);
               session.getTransaction().commit();
               
           }
        }catch(HibernateException ex){
              System.err.println("Ocurrio un error" + ex.getMessage());
          
        }
        return resultado;
    }
    
    public boolean eliminar(int id){
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           
           Conductor conductor = obtenerPorId(id);
            if(conductor !=null){
               session.delete(conductor);
               session.getTransaction().commit();
               resultado = true;
               
           }       
        }catch(HibernateException ex){
              System.err.println("Ocurrio un error" + ex.getMessage());
          
        }
        return resultado;
    }
    
    
}
