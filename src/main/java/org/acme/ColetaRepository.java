package org.acme;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ColetaRepository implements PanacheRepository<Coleta>{

	public List<Coleta>buscaTodasColetas(){
		return Coleta.listAll();
	}

	public Coleta buscaColetaPorCodigo(String codigo){
		Coleta coleta = Coleta.find("codigoColeta", codigo).firstResult();

		if(coleta == null){
			throw new NotFoundException();
		}
		return coleta;
	}

	public void cadastraColeta(CadastrarColetaDTO dto){
		try {
			Coleta c = new Coleta();
			c.codigoColeta = dto.codigoColeta;
			c.horaInicio = dto.horaInicio;
			c.diasSemana = dto.diasSemana;
			c.tipoColeta = dto.tipoColeta;
			c.rota = dto.rota;
			c.persist();
		} catch (Exception e) {
			throw new NotAcceptableException();
		}
	}

	public void atualizaColeta1(String codigo, CadastrarColetaDTO dto){
		try{
			Coleta c = Coleta.find("codigoColeta", codigo).firstResult();
			Optional<Coleta> coletaOp = Coleta.findByIdOptional(c.id);
			
			Coleta coleta = coletaOp.get();
			coleta.codigoColeta = dto.codigoColeta;
			coleta.horaInicio = dto.horaInicio;
			coleta.diasSemana = dto.diasSemana;
			coleta.tipoColeta = dto.tipoColeta;
			coleta.rota = dto.rota;
			coleta.persist();
		}catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public void deletaColeta(String codigo){
		Coleta c = Coleta.find("codigoColeta", codigo).firstResult();

		try {
			c.delete();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

}
