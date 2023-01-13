package br.com.graphvs.ntrack;

import java.util.List;


public interface IRestApi <Entidade> {
	
	public List<Entidade> list() ;

	public Entidade save(Entidade entidade) ;
	
	public Entidade update(Entidade entidade) ;
	
	public void delete(Entidade entidade);
	
}
