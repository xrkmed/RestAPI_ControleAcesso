package com.xrkmed.accesspoint.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xrkmed.accesspoint.exceptions.InvalidArray;
import com.xrkmed.accesspoint.exceptions.WorkerNotFound;
import com.xrkmed.accesspoint.generals.HierarchyDAO;
import com.xrkmed.accesspoint.models.WorkerEntity;
import com.xrkmed.accesspoint.models.WorkerNotFoundResponse;

@RestController
public class AccessResource {
	
	private final HierarchyDAO hierarchyDAO = new HierarchyDAO().getInstance();

	@RequestMapping("/worker/{workerIdentification}")
	public ResponseEntity<WorkerEntity> parseLogin(@PathVariable("workerIdentification") String param){
		
		try{
			String[] findTerms = hierarchyDAO.parseWorkerIdentification(param);
			WorkerEntity worker = hierarchyDAO.getWorkerById(Long.parseLong(findTerms[1]));
			return ResponseEntity.ok().body(worker);
		}catch(InvalidArray e){
			return ResponseEntity.ok().body(new WorkerNotFoundResponse("O estilo de cracha não é válido."));
		}catch(WorkerNotFound ex){
			return ResponseEntity.ok().body(new WorkerNotFoundResponse("Nao foi encontrado nenhum trabalhador com as identificacoes "+param + ", tente novamente com outro estilo de cracha."));
		}
	}

}
