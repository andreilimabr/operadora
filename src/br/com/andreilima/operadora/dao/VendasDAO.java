package br.com.andreilima.operadora.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.com.andreilima.operadora.modelo.Venda;

public class VendasDAO {
	private Session session;

	public VendasDAO(Session session) {
		this.session = session;
	}
	
	public void persiste(Venda tabela) {
		Transaction tx = this.session.beginTransaction();
		this.session.save(tabela);
		tx.commit();
	}
	
	public void atualiza(Venda tabela) {
		Transaction tx = session.beginTransaction();
		this.session.update(tabela);
		tx.commit();
	}

	public void remove(Venda tabela) {
		Transaction tx = session.beginTransaction();
		this.session.delete(tabela);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listaTudo() {
		return this.session.createCriteria(Venda.class).list();
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> listaPorClausulaWhere(String pWhere, Object pValor) {
		
		Criteria consulta = session.createCriteria(Venda.class);  
		consulta.add(Restrictions.like(pWhere, pValor));
		return consulta.list();
		
	}		
	

	public List<Venda> listaPorClausulaWhere(SimpleExpression simpleExpression) {
		
		Criteria consulta = session.createCriteria(Venda.class);  
		consulta.add((Criterion) simpleExpression);
		return consulta.list();
		
	}		

	
	@SuppressWarnings("unchecked")
	public Venda buscaPorId(Long id) {
		return (Venda) this.session.load(Venda.class, id);
	}
}
