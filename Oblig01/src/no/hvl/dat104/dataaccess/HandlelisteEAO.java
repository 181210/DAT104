package no.hvl.dat104.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import no.hvl.dat104.model.Vare;

@Stateless
public class HandlelisteEAO {
    @PersistenceContext(name = "handlelistePersistenceUnit")
    private EntityManager em;

    public List<Vare> visAlleVarerTilBruker(Integer id) {
        TypedQuery<Vare> vare = em.createQuery("SELECT v FROM Vare v WHERE v.kurv.kurv_id =: id", Vare.class).setParameter("id",id);
        return vare.getResultList();
    }
    public void leggTilVare(Vare v){
        em.persist(v);
    }
    public Vare finnVare(String id) {
        return em.find(Vare.class, id);
    }
    public void slettVare(String id) {
        em.remove(em.find(Vare.class, id));
    }

}
