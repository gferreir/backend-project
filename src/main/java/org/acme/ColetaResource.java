package org.acme;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
		Coleta c = new Coleta();
		c.codigoColeta = dto.codigoColeta;
		c.horaInicio = dto.horaInicio;
		c.diasSemana = dto.diasSemana;
		c.tipoColeta = dto.tipoColeta;
		c.rota = dto.rota;
		c.persist();
	}

	@PUT
	@Transactional
	@Path("{id}")
	public void atualizaColeta(@PathParam("id") Long id, CadastrarColetaDTO dto){
		Optional<Coleta> coletaOp = Coleta.findByIdOptional(id);

		if(coletaOp.isPresent()){
			Coleta coleta = coletaOp.get();
			coleta.codigoColeta = dto.codigoColeta;
			coleta.horaInicio = dto.horaInicio;
			coleta.diasSemana = dto.diasSemana;
			coleta.tipoColeta = dto.tipoColeta;
			coleta.rota = dto.rota;
			coleta.persist();
		}else{
			throw new NotFoundException();
		}
	}

	@DELETE
	@Transactional
	@Path("{id}")
	public void deletaColeta(@PathParam("id") Long id){
		Optional<Coleta> coletaOp = Coleta.findByIdOptional(id);

		coletaOp.ifPresentOrElse(Coleta::delete, ()->{
			throw new NotFoundException();
		});
	}

}
