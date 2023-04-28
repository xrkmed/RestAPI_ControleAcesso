package com.xrkmed.accesspoint.controllers;

import java.text.SimpleDateFormat;

import javax.naming.InsufficientResourcesException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xrkmed.accesspoint.exceptions.ExtraTimeDescanso;
import com.xrkmed.accesspoint.exceptions.InsuffTimeWorked;
import com.xrkmed.accesspoint.exceptions.InvalidArray;
import com.xrkmed.accesspoint.exceptions.WorkerNotFound;
import com.xrkmed.accesspoint.generals.HierarchyDAO;
import com.xrkmed.accesspoint.models.AccessPointCad;
import com.xrkmed.accesspoint.models.AccessPointCadError;
import com.xrkmed.accesspoint.models.WorkerAdvertency;
import com.xrkmed.accesspoint.models.WorkerEntity;

@RestController
public class ParseRequest {

    private final HierarchyDAO hierarchyDAO = new HierarchyDAO().getInstance();

    @RequestMapping("/login/{workerIdentification}")
    public ResponseEntity<AccessPointCad> read(@PathVariable("workerIdentification") String param){
        try{
            String[] findTerms = hierarchyDAO.parseWorkerIdentification(param);
            WorkerEntity worker = hierarchyDAO.getWorkerById(Long.parseLong(findTerms[1]));
            if(hierarchyDAO.working(worker)){
                AccessPointCad cad = hierarchyDAO.getCad(worker);
                cad.readCracha();

                return ResponseEntity.ok().body(cad);
            }

            AccessPointCad cad = new AccessPointCad();
            hierarchyDAO.startWorking(worker, cad);

            return ResponseEntity.ok().body(cad); 
        }catch(ExtraTimeDescanso ex){
            try{
                String[] findTerms = hierarchyDAO.parseWorkerIdentification(param);
                WorkerEntity worker = hierarchyDAO.getWorkerById(Long.parseLong(findTerms[1]));

                String timeString = new SimpleDateFormat("HH:mm:ss.SSSS dd/MM/yyyy").format(new java.util.Date());
                hierarchyDAO.addWorkerAdvertency(worker, new WorkerAdvertency("Tempo de descanso excedido.", "System", timeString, 1));
            }catch(Exception exception){
                return ResponseEntity.ok().body(new AccessPointCadError(exception.getMessage()));
            }
        }catch(InsuffTimeWorked ex){
            try{
                String[] findTerms = hierarchyDAO.parseWorkerIdentification(param);
                WorkerEntity worker = hierarchyDAO.getWorkerById(Long.parseLong(findTerms[1]));
                String timeString = new SimpleDateFormat("HH:mm:ss.SSSS dd/MM/yyyy").format(new java.util.Date());
                hierarchyDAO.addWorkerAdvertency(worker, new WorkerAdvertency("Nao cumpriu as oito horas de servico.", "System", timeString, 2));
            }catch(Exception exception){
                return ResponseEntity.ok().body(new AccessPointCadError(ex.getMessage()));
            }
        }catch(Exception ex){
            return ResponseEntity.ok().body(new AccessPointCadError(ex.getMessage()));
        }

        return ResponseEntity.badRequest().body(null);
    }
    
}
