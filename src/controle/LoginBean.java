package controle;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.UsuarioDAO;
import dao.UsuarioJPADAO;
import modelo.Usuario;

@ManagedBean
public class LoginBean extends AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public LoginBean() {
		this.usuario = new Usuario();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
		addPadrao();
	}
	
	public String logar(){
		UsuarioDAO uDAO = new UsuarioJPADAO();
		Usuario u = uDAO.find(this.usuario.login);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		if(u == null){
			displayErrorMessageToUser("Usuário não existe!");
			return "/index.xhtml?faces-redirect=true";
		}
		else if(u != null && u.getSenha().equals(this.usuario.getSenha())){
			HttpSession sessao = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(true);
			sessao.setAttribute("login", u.getLogin());
			sessao.setAttribute("nome", u.getNome());
			return "/pages/home.xhtml?faces-redirect=true";
		}else{
			displayErrorMessageToUser("Senha inválida!");
			return "/index.xhtml?faces-redirect=true";
		}
	}
	
	public String sair(){
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    if(sessao != null){
	    	sessao.invalidate();
	    }
		return "/index.xhtml?faces-redirect=true";
	}
	
	public void addPadrao() {
		UsuarioDAO uDAO = new UsuarioJPADAO();
		List<Usuario> usuarios = uDAO.find();
		Usuario a = new Usuario();
		if (usuarios.isEmpty()) {
			a.setLogin("admin");
			a.setSenha("admin");
			a.setNome("Administrador");
			uDAO.save(a);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
