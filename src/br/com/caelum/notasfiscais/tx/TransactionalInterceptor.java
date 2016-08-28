package br.com.caelum.notasfiscais.tx;


import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionalInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception{
		System.out.println("Antes de chamar o método!");
		this.manager.getTransaction().begin();
		Object retorno = ctx.proceed();
		this.manager.getTransaction().commit();
		System.out.println("Depois de chamar o método!");
		return retorno;
	}
}
