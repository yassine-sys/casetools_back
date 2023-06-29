package com.alerte.remote;

import java.util.List;

import javax.ejb.Remote;

import com.alert.entities.*;

@Remote
public interface AlerteInterface {

	public List<RevenueStream> getAll();

	public void addRevenueStream(RevenueStream u);

	public void deleteRevenueStream(RevenueStream u);

	public void updateRevenueStream(RevenueStream u);
	
	public void updateRevenue(Long id, RevenueStream updatedRevenue);
	
	public RevenueStream getRevenueStreamById(Long id);
	
	public void removeRevenue(Long id);

	public List<DomainControl> getAllDomain();

	public void addDomain(DomainControl u);

	public void deleteDomain(DomainControl u);
	
	public void updateDomainControl(Long id, DomainControl updatedControl);
	
	public void removeDomain(Long id);

	public void updateDomain(DomainControl u);

	public List<Rarule> getAllRarules();

	public void addRarule(Rarule u);
	
	public void updateRarule(Long id, Rarule updatedRarule);

	public void deleteRarule(Rarule u);
	
	public void removeRarule(Long id);

	public void updateRarule(Rarule u);

	public List<ParametresRarule> getAllParametres();

	public void addParametre(ParametresRarule u);

	public void deleteParametre(ParametresRarule u);
	
	public void updateParametre(Long id, ParametresRarule updatedParam);
	
	public void removeParametre(Long id);

	public void updatePrametre(ParametresRarule u);

	public List<ReportRarule> getAllReport();

	public void addReport(ReportRarule u);

	public void deleteReport(ReportRarule u);
	
	public void removeReport(Long id);

	public void updateReport(ReportRarule u);
	
	public void updateRaport(Long id, ReportRarule updatedRep);

	public List<AlerteRa> getAllAlertes();

	public String descriptioRule(Long idRule);

	public void addAlerte(AlerteRa u);

	public void deleteAlerte(AlerteRa u);

	public void updatAlerte(AlerteRa u);

	public List<HistoriqueAlerte> getAllHist();

	public void addHist(HistoriqueAlerte u);

	public void deleteHist(HistoriqueAlerte u);

	public void updateHist(HistoriqueAlerte u);

	public List<RepRapport> getAllrapports();

	public List<Function> getAllFunction();

	public List<Flow> getAllFlow();
	
	public void assignAlertUser(Alertuser u);
	
	public void closeAlert(Long id,AlerteRa updatedAlert);
}
