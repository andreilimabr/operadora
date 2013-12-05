package br.com.andreilima.operadora.controllers;

import java.util.logging.Logger;

import org.hibernate.Session;

import br.com.andreilima.operadora.dao.VendasDAO;
import br.com.andreilima.operadora.modelo.Venda;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

import com.google.gson.Gson;

@Resource
public class VendasController {
	
	private Session session;
	
	public VendasController(Session session) {
		this.session = session;
	}
	
	@Post("/realizavenda")
	@Consumes("application/json")
	public void realizaVenda(Venda venda) {
		VendasDAO dao = new VendasDAO(this.session);
		dao.persiste(venda);
		Gson gson = new Gson();
		System.out.println(gson.toJson(venda));
		Logger logger = Logger.getLogger("catalina.out");
		logger.fine(gson.toJson(venda));
	}
}
