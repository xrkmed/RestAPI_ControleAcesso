package com.xrkmed.accesspoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xrkmed.accesspoint.generals.BuilderConfig;
import com.xrkmed.accesspoint.generals.HierarchyClass;
import com.xrkmed.accesspoint.generals.HierarchyDAO;
import com.xrkmed.accesspoint.models.WorkerAdvertency;
import com.xrkmed.accesspoint.models.WorkerEntity;
import com.xrkmed.accesspoint.models.WorkerHistory;

@SpringBootApplication
public class AccesspointApplication {

	private final static HierarchyDAO hierarchyDAO = new HierarchyDAO().getInstance();

	public static void main(String[] args) {
		SpringApplication.run(AccesspointApplication.class, args);

		WorkerEntity worker = hierarchyDAO.registerWorker(1000001, "Joao Guilherme Milian Martins", new HierarchyClass("Desenvolvedor Back-end", 999), 8999.5);
		hierarchyDAO.addWorkerHistory(worker, new WorkerHistory("Desenvolveu um Endpoint para a aplicacao", "Responsavel por coordenar e desenvolver o endpoint da aplicacao de ponto de acesso.", 10));
		hierarchyDAO.addWorkerHistory(worker, new WorkerHistory("Supervisor do GitHub", "Usuario responsavel por aprovar e rejeitar requisicoes e pull requests do GitHub", 8));
		hierarchyDAO.addWorkerAdvertency(worker, new WorkerAdvertency("Desenvolveu um sistema que causou uma instabilidade no sistema", "CEO Krugs", "28/04/2023", 7));
		hierarchyDAO.registerWorker(worker);

		WorkerEntity workerKrugs = hierarchyDAO.registerWorker(1000002, "Krugs Wilhelm Milan Martin", new HierarchyClass("Gerente da filial " + BuilderConfig.PACKAGE_FILIAL, 999), 14000);
		hierarchyDAO.addWorkerHistory(workerKrugs, new WorkerHistory("Fundacao de uma filial", "Fundou a filial de " + BuilderConfig.PACKAGE_FILIAL + " gerando emprego a mais de " + hierarchyDAO.getWorkersCount() + " pessoas!", 10));
		hierarchyDAO.registerWorker(workerKrugs);
	}

}
