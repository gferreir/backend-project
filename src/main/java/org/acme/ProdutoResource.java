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

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    
	@GET
	public List<Produto> buscaTodosProdutos(){
		return Produto.listAll();
	}

	@GET
	@Path("{nome}")
	public Produto buscaProdutoPorNome(@PathParam("nome") String nome){
		try {
			return Produto.find("nome", nome).firstResult();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	@POST
	@Transactional
	public void cadastraProduto(CadastrarProdutoDTO dto){
		Produto p = new Produto();
		p.nome = dto.nome;
		p.valor = dto.valor;
		p.persist();
	}

	@PUT
	@Transactional
	@Path("{id}")
	public void atualizaProduto(@PathParam("id") Long id, CadastrarProdutoDTO dto){
		Optional<Produto> produtoOp = Produto.findByIdOptional(id);
		
		if(produtoOp.isPresent()){
			Produto produto = produtoOp.get();
			produto.nome = dto.nome;
			produto.valor = dto.valor;
			produto.persist();
		}else{
			throw new NotFoundException();
		}
	}

	@DELETE
	@Transactional
	@Path("{id}")
	public void deletaProduto(@PathParam("id") Long id){
		Optional<Produto> produtoOp = Produto.findByIdOptional(id);
		
		produtoOp.ifPresentOrElse(Produto::delete, ()-> {
			throw new NotFoundException();
		});
	}
}
