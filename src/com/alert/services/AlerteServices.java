package com.alert.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.alert.entities.*;
import com.alerte.remote.AlerteInterface;

@Stateless
public class AlerteServices implements AlerteInterface  {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<RevenueStream> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from RevenueStream u").getResultList();
	}

	@Override
	public void addRevenueStream(RevenueStream u) {
		// TODO Auto-generated method stub
		em.persist(u);
	}

	@Override
	public void deleteRevenueStream(RevenueStream u) {
		// TODO Auto-generated method stub
		em.remove(em.contains(u) ? u : em.merge(u));
		
	}
	@Override
	public void removeRevenue(Long id) {
		RevenueStream rev=em.find(RevenueStream.class, id);
		if(rev!=null) {
			em.remove(rev);
		}
	}
	 @Override
	    public RevenueStream getRevenueStreamById(Long id) {
	        return em.find(RevenueStream.class, id);
	    }

	@Override
	public void updateRevenueStream(RevenueStream u) {
		// TODO Auto-generated method stub
		em.merge(u);
	}

	@Override
	public List<DomainControl> getAllDomain() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from DomainControl u").getResultList();
	}

	@Override
	public void addDomain(DomainControl u) {
		// TODO Auto-generated method stub
		em.persist(u);

	}

	@Override
	public void deleteDomain(DomainControl u) {
		// TODO Auto-generated method stub
		em.remove(em.contains(u) ? u : em.merge(u));
	}
	@Override
	public void removeDomain(Long id) {
		DomainControl dom=em.find(DomainControl.class, id);
		if(dom!=null) {
			em.remove(dom);
		}
	}

	@Override
	public void updateDomain(DomainControl u) {
		// TODO Auto-generated method stub
		em.merge(u);
	}

	@Override
	public List<Rarule> getAllRarules() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from Rarule u").getResultList();
	}

	@Override
	public void addRarule(Rarule u) {
		// TODO Auto-generated method stub
		em.persist(u);

	}

	@Override
	public void deleteRarule(Rarule u) {
		em.remove(em.contains(u) ? u : em.merge(u));
	}
	@Override
	public void removeRarule(Long id) {
		Rarule rarule=em.find(Rarule.class, id);
		if(rarule!=null) {
			em.remove(rarule);
		}
		
	}

	@Override
	public void updateRarule(Rarule u) {
		// TODO Auto-generated method stub
		em.merge(u);

	}

	@Override
	public List<ParametresRarule> getAllParametres() {
		// TODO Auto-generated method stub
		List<ParametresRarule> listPR = new ArrayList<ParametresRarule>();
		listPR = em.createQuery("select u from ParametresRarule u").getResultList();
		for (int i = 0; i < listPR.size(); i++) {
			System.out.println(listPR.get(i).getFonction());
			System.out.println(listPR.get(i).getIdParametre());
			System.out.println(listPR.get(i).getIdRule());
			System.out.println(listPR.get(i).getId());

		}
		return listPR;
	}

	@Override
	public void addParametre(ParametresRarule u) {
		// TODO Auto-generated method stub

		String nameparametre = "";
		nameparametre = em.createQuery("select u.table_name from Flow u where u.id=" + u.getIdParametre())
				.getSingleResult().toString();
		u.setNameparametre(nameparametre);
		em.persist(u);

	}
	@Override
	public void removeParametre(Long id) {
		ParametresRarule param=em.find(ParametresRarule.class, id);
		if(param!=null) {
			em.remove(param);
		}
		
	}

	@Override
	public void deleteParametre(ParametresRarule u) {
		// TODO Auto-generated method stub

		em.remove(em.contains(u) ? u : em.merge(u));

	}

	@Override
	public void updatePrametre(ParametresRarule u) {
		// TODO Auto-generated method stub
		String nameparametre = "";
		nameparametre = em.createQuery("select u.table_name from Flow u where u.id=" + u.getIdParametre())
				.getSingleResult().toString();
		u.setNameparametre(nameparametre);
		em.merge(u);

	}

	@Override
	public List<ReportRarule> getAllReport() {
		// TODO Auto-generated method stub

		return em.createQuery("select u from ReportRarule u").getResultList();

	}

	@Override
	public void addReport(ReportRarule u) {
		// TODO Auto-generated method stub

		String namerep = em.createQuery("select r.name from RepRapport r where r.id=" + u.getIdReport())
				.getSingleResult().toString();
		u.setNamerep(namerep);
		String namerule = em.createQuery("select u.nom from Rarule u where u.id=" + u.getIdRule()).getSingleResult()
				.toString();
		u.setNamerul(namerule);
		em.persist(u);

	}

	@Override
	public void removeReport(Long id) {
		
		ReportRarule rep=em.find(ReportRarule.class, id);
		if(rep!=null) {
			em.remove(rep);
		}

	}

	@Override
	public void updateReport(ReportRarule u) {
		// TODO Auto-generated method stub
		String namerep = em.createQuery("select r.name from RepRapport r where r.id=" + u.getIdReport())
				.getSingleResult().toString();
		u.setNamerep(namerep);
		String namerule = em.createQuery("select u.nom from Rarule u where u.id=" + u.getIdRule()).getSingleResult()
				.toString();
		u.setNamerul(namerule);
		em.merge(u);
	}

	@Override
	public List<AlerteRa> getAllAlertes() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from AlerteRa u").getResultList();

	}

	@Override
	public void addAlerte(AlerteRa u) {
		em.persist(u);

	}

	@Override
	public void deleteAlerte(AlerteRa u) {
		// TODO Auto-generated method stub
		em.remove(em.contains(u) ? u : em.merge(u));

	}

	@Override
	public void updatAlerte(AlerteRa u) {
		// TODO Auto-generated method stub
		em.merge(u);
	}

	@Override
	public List<HistoriqueAlerte> getAllHist() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from HistoriqueAlerte u").getResultList();

	}

	@Override
	public void addHist(HistoriqueAlerte u) {
		// TODO Auto-generated method stub
		em.persist(u);
	}

	@Override
	public void deleteHist(HistoriqueAlerte u) {
		// TODO Auto-generated method stub
		em.remove(em.contains(u) ? u : em.merge(u));

	}

	@Override
	public void updateHist(HistoriqueAlerte u) {
		// TODO Auto-generated method stub
		em.merge(u);
	}

	@Override
	public List<RepRapport> getAllrapports() {
		// TODO Auto-generated method stub
		return em.createQuery("select r from RepRapport r").getResultList();
	}

	@Override
	public List<Function> getAllFunction() {
		// TODO Auto-generated method stub
		return em.createQuery("select r from Function r").getResultList();
	}

	@Override
	public List<Flow> getAllFlow() {
		// TODO Auto-generated method stub
		return em.createQuery("select r from Flow r where flow_type.id = 13").getResultList();

	}

	@Override
	public String descriptioRule(Long idRule) {
		// TODO Auto-generated method stub

		return em.createQuery("select r.description from Rarule r where r.id=" + idRule).getSingleResult().toString();

	}

	@Override
	public void deleteReport(ReportRarule u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRevenue(Long id, RevenueStream updatedRevenue) {
		// TODO Auto-generated method stub
		RevenueStream rev = em.find(RevenueStream.class, id);
	    if (rev != null) {
	        // Update the properties of the existing revenue stream with the new values
	        rev.setRevenuestream(updatedRevenue.getRevenuestream());
	        // Update other properties as needed

	        // Save the changes to the database
	        em.merge(rev);
	    }
		
	}

	@Override
	public void updateDomainControl(Long id, DomainControl updatedControl) {
		// TODO Auto-generated method stub
		
		DomainControl dom = em.find(DomainControl.class, id);
	    if (dom != null) {
	        // Update the properties of the existing revenue stream with the new values
	    	dom.setDomain(updatedControl.getDomain());
	        // Update other properties as needed

	        // Save the changes to the database
	        em.merge(dom);
	    }
		
	}

	@Override
	public void updateRarule(Long id, Rarule updatedrule) {
		// TODO Auto-generated method stub
		Rarule rule = em.find(Rarule.class, id);
	    if (rule != null) {
	        // Update the properties of the existing revenue stream with the new values
	    	rule.setNom(updatedrule.getNom());
	    	rule.setDescription(updatedrule.getDescription());
	    	rule.setNomUtilisateur(updatedrule.getNomUtilisateur());
	    	rule.setType(updatedrule.getType());
	    	rule.setIdDomain(updatedrule.getIdDomain());
	    	rule.setIdrevenue(updatedrule.getIdrevenue());
	        // Update other properties as needed

	        // Save the changes to the database
	        em.merge(rule);
	    }
		
	}

	@Override
	public void updateParametre(Long id, ParametresRarule updatedParam) {
		// TODO Auto-generated method stub
		
		ParametresRarule param = em.find(ParametresRarule.class, id);
	    if (param != null) {
	        // Update the properties of the existing revenue stream with the new values
	    	param.setFonction(updatedParam.getFonction());
	    	
	    	param.setIdParametre(updatedParam.getIdParametre());
	    	param.setIdRule(updatedParam.getIdRule());
	    	param.setSeuil(updatedParam.getSeuil());
	    	String nameparametre = em.createQuery("select f.table_name from Flow f where f.id = :id")
	                .setParameter("id", updatedParam.getIdParametre())
	                .getSingleResult().toString();

	        param.setNameparametre(nameparametre);
	    	
	        // Update other properties as needed
	    	
	        // Save the changes to the database
	        em.merge(param);
	    }
		
	}

	@Override
	public void updateRaport(Long id, ReportRarule updatedRep) {
		// TODO Auto-generated method stub
		
		ReportRarule rep = em.find(ReportRarule.class, id);
	    if (rep != null) {
	        // Update the properties of the existing revenue stream with the new values
	    	
	    	rep.setIdReport(updatedRep.getIdReport());
	    	rep.setIdRule(updatedRep.getIdRule());
	    	
	    	 String namerep = em.createQuery("select r.name from RepRapport r where r.id = :idReport", String.class)
	                 .setParameter("idReport", updatedRep.getIdReport().intValue())
	                 .getSingleResult().toString();

	         rep.setNamerep(namerep);

	         String namerule = em.createQuery("select u.nom from Rarule u where u.id = :idRule", String.class)
	                 .setParameter("idRule", updatedRep.getIdRule())
	                 .getSingleResult().toString();

	         rep.setNamerul(namerule);

	    	
	        // Update other properties as needed
	    	
	        // Save the changes to the database
	        em.merge(rep);
	    }
		
	}

	@Override
	public void assignAlertUser(Alertuser u) {
		// TODO Auto-generated method stub
	
	
	String username="";
	username= em.createQuery("select u.name from UserLogin u where u.id=" + u.getIdUser())
			.getSingleResult().toString();
	
	u.setUsername(username);
	em.persist(u);
	
	 AlerteRa alert = em.find(AlerteRa.class, u.getIdAlerte());
	    if (alert != null) {
	        alert.setStatus("assigned");
	        em.merge(alert);
	    }
		
	}

	@Override
	public void closeAlert(Long id, AlerteRa updatedAlert) {
		// TODO Auto-generated method stub
		AlerteRa alert = em.find(AlerteRa.class, id);
		 if (alert != null) {
		        alert.setStatus("close");
		        em.merge(alert);
		    }
		
		
	}

}
