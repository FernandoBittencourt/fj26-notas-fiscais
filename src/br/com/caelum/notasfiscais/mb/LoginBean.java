package br.com.caelum.notasfiscais.mb;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named @RequestScoped
public class LoginBean{

	@Inject
	private Event<Usuario> evento;
	
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public String efetuaLogin() {
		boolean loginValido = dao.existe(this.usuario);
		if (loginValido) {
			evento.fire(this.usuario);
			this.usuarioLogado.logar(this.usuario);
			return "produto?faces-redirect=true";
		} else {
			return "login";
		}
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public String logout() {
		this.usuarioLogado.deslogar();
		return "login?faces-redirect=true";
	}
}