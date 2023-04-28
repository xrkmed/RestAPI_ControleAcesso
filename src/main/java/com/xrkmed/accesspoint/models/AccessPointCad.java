package com.xrkmed.accesspoint.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.naming.InsufficientResourcesException;

import com.xrkmed.accesspoint.exceptions.ExtraTimeDescanso;
import com.xrkmed.accesspoint.exceptions.InsuffTimeWorked;

public class AccessPointCad implements Serializable {

    private static int geralCadId = 1;

    private int cadId;
    private boolean isWorking;
    private long startedWork;
    private long tempoDescansoInit;
    private long tempoDescansoEnd;
    private boolean startedDescanso;
    private String message;

    public AccessPointCad() {
        this.startedWork = System.currentTimeMillis();
        this.isWorking = true;
        this.tempoDescansoInit = 0;
        this.cadId = AccessPointCad.geralCadId++;
        AccessPointCad.geralCadId++;
        message = "Seja bem vindo ao servico.";
    }

    public void setReturnMessage(String message){
        this.message = message;
    }

    public String getReturnMessage(){
        return message;
    }

    public void readCracha() throws InsuffTimeWorked, InsufficientResourcesException, ExtraTimeDescanso{
        if(tempoDescansoInit == 0 && isWorking){
            tempoDescansoInit = System.currentTimeMillis();
            startedDescanso = true;

            String timeString = new SimpleDateFormat("HH:mm:ss").format(new Date(tempoDescansoInit+3600*1000));
            setReturnMessage("Voce iniciou o seu descanso, o descanso termina as " + timeString);
            return;
        }

        if(startedDescanso){
            if(System.currentTimeMillis() - tempoDescansoInit >= 3600*1000){
                startedDescanso = false;
                tempoDescansoEnd = System.currentTimeMillis();

                if(System.currentTimeMillis() - tempoDescansoInit >= (3600*1000+(10*60*1000))){
                    setReturnMessage("Voce descansou por mais que o tempo limite e foi adicionado uma advertencia no seu registro.");
                    throw new ExtraTimeDescanso("O funcionario descansou por mais de 1 hora e 10 minutos.");
                }

                setReturnMessage("Voce voltou do seu descanso, bom trabalho.");
                return;
            }else{
                String timeString = new SimpleDateFormat("HH:mm:ss").format(new Date(tempoDescansoInit+3600*1000));
                throw new InsufficientResourcesException("O seu descanso termina apenas as " + timeString + ".");
            }
        }
        
        isWorking = !isWorking;

        long actualTime = System.currentTimeMillis();
        if(actualTime-startedWork < 8*3600*1000){
            if(tempoDescansoEnd-tempoDescansoInit < 3600*1000){
                long difference = 3600*1000 - (tempoDescansoEnd-tempoDescansoInit);

                if(actualTime-startedWork+difference >= 8*3600*1000){
                    return;
                }
            }

            setReturnMessage("Voce trabalhou por menos que o tempo minimo (8 horas) e foi adicionado uma advertencia no seu registro.");
            throw new InsuffTimeWorked("O funcionario trabalhou menos que 8 horas.");
        }
        
        setReturnMessage("Voce finalizou sua jornada de trabalho com sucesso, ate o proximo dia.");
    }
    

    public boolean isWorking(){
        return isWorking;
    }

    public boolean isDescanso(){
        return tempoDescansoInit > 0 && startedDescanso;
    }

    public Long[] getDescansoTempo() {
        Long[] result = {tempoDescansoInit, tempoDescansoEnd, ((tempoDescansoEnd-tempoDescansoInit)/3600L)};
        return result;
    }

    public void onThink(){

    }

	@Override
	public int hashCode() {
		return Objects.hash(cadId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessPointCad other = (AccessPointCad) obj;
		return cadId == other.cadId;
	}
    
    
}
