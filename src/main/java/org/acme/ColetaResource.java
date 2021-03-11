package org.acme;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("coleta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColetaResource {

	@Inject
	ColetaRepository coletaRepository;

	@GET
	public List<Coleta>buscaTodasColetas(){
		return coletaRepository.buscaTodasColetas();
	}

	@GET
	@Path("{codigo}")
	public Coleta buscaColetaPorCodigo(@PathParam("codigo") String codigo){
		return coletaRepository.buscaColetaPorCodigo(codigo);
	}

	@POST
	@Transactional
	public void cadastraColeta(CadastrarColetaDTO dto){
		coletaRepository.cadastraColeta(dto);
	}

	@PUT
	@Transactional
	@Path("{codigo}")
	public void atualizaColeta1(@PathParam("codigo") String codigo, CadastrarColetaDTO dto){
		coletaRepository.atualizaColeta1(codigo, dto);
	}

	@DELETE
	@Transactional
	@Path("{codigo}")
	public void deletaColeta(@PathParam("codigo") String codigo){
		coletaRepository.deletaColeta(codigo);
	}

}
