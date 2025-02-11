/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.philadelphia.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.philadelphia.entidades.Multa;
import mx.itson.philadelphia.utilerias.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author taisa
 */
public class MultaDAO {
    
     public static List<Multa> obtenerTodos() {
        List<Multa> multas = new ArrayList<>();
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery< Multa> criteruaQuery = session.getCriteriaBuilder().createQuery(Multa.class);
            criteruaQuery.from(Multa.class);

           multas = session.createQuery(criteruaQuery).getResultList();

        } catch (Exception ex) {

            System.err.print("Ocurrió un error: " + ex.getMessage());

        }
        return multas;
    }
    
}
