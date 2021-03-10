package org.acme;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;

@Path("coleta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColetaResource {

	@GET
	public List<Coleta>buscaTodasColetas(){
		return Coleta.listAll();
	}

	@GET
	@Path("{codigo}")
	public Coleta buscaColetaPorCodigo(@PathParam("codigo") String codigo){
		try {
			return Coleta.find("codigoColeta", codigo).firstResult();
		}catch (Exception e) {
			throw new NotFoundException();
		}
	}

	@POST
	@Transactional
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

	@PUT
	@Transactional
	@Path("{codigo}")
	public void atualizaColeta1(@PathParam("codigo") String codigo, CadastrarColetaDTO dto){
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

	@DELETE
	@Transactional
	@Path("{codigo}")
	public void deletaColeta(@PathParam("codigo") String codigo){
		Coleta c = Coleta.find("codigoColeta", codigo).firstResult();

		try {
			c.delete();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

}
