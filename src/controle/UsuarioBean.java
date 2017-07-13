package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.UsuarioDAO;
import dao.UsuarioJPADAO;
import modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Usuario usuarioAtualiza;
	private List<Usuario> usuarios;
	private String senhaAtual;
	private String novaSenha;
	private String confirmacaoSenha;
	private String loginUsuario;
	private boolean isAdmin;
	private boolean isNaoAdmin;

	public UsuarioBean() {
		this.usuario = new Usuario();
		this.usuarios = new ArrayList<Usuario>();
		buscarUsuarioLogado();
		selecionarParaAtualizar();
	}

	public void buscarTodos() {
		this.usuarios = new ArrayList<Usuario>();
		UsuarioDAO usuarioDAO = new UsuarioJPADAO();
		this.usuarios = usuarioDAO.find();
	}

	public void buscarUsuarioLogado() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String login = (String) sessao.getAttribute("login");
		this.loginUsuario = login;
		if (login.equals("admin")) {
			this.isAdmin = true;
			this.isNaoAdmin = false;
		} else {
			this.isAdmin = false;
			this.isNaoAdmin = true;
		}
	}

	public void cadastrar() {
		if (isAdmin) {
			UsuarioDAO usuarioDAO = new UsuarioJPADAO();
			usuarioDAO.save(this.usuario);
			displayInfoMessageToUser("Cadastrado com sucesso!");
			this.usuario = new Usuario();
		} else {
			displayErrorMessageToUser("Usuário não autorizado para fazer cadastro de usuário!");
		}

	}

	public void atualizar() {
		UsuarioDAO usuarioDAO = new UsuarioJPADAO();
		usuarioDAO.save(this.usuarioAtualiza);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.usuarioAtualiza = new Usuario();
	}

	public void excluir(Usuario u) {
		if (isAdmin){
			if(!u.login.equals("admin")){
				UsuarioDAO usuarioDAO = new UsuarioJPADAO();
				usuarioDAO.delete(u);
				displayInfoMessageToUser("Excluido com sucesso!");
				this.usuarios.remove(u);
			}else{
				displayErrorMessageToUser("Usuário admin não pode ser excluido");
			}
		} else {
			displayErrorMessageToUser("Usuário não autorizado para fazer exclusão de usuário!");
		}
	}

	public void selecionarParaAtualizar() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String login = (String) sessao.getAttribute("login");
		UsuarioDAO uDAO = new UsuarioJPADAO();
		this.usuarioAtualiza = uDAO.find(login);
	}

	public void alterarSenha() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String login = (String) sessao.getAttribute("login");
		if (login != null) {
			UsuarioDAO uDAO = new UsuarioJPADAO();
			Usuario u = uDAO.find(login);
			if (u.getSenha().equals(this.senhaAtual)) {
				if (this.novaSenha.equals(this.confirmacaoSenha)) {
					u.setSenha(this.novaSenha);
					uDAO.save(u);
					displayInfoMessageToUser("Senha atualizada com sucesso!");
				} else {
					displayErrorMessageToUser("Senhas diferentes!");
				}

			} else {
				displayErrorMessageToUser("Senha atual errada!");
			}
		}

	}

	public int sortByNome(String n1, String n2) {
		return n1.compareTo(n2);
	}

	public int sortByEmail(String e1, String e2) {
		return e1.compareTo(e2);
	}

	public int sortByLogin(String l1, String l2) {
		return l1.compareTo(l2);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public boolean isNaoAdmin() {
		return isNaoAdmin;
	}

	public void setNaoAdmin(boolean isNaoAdmin) {
		this.isNaoAdmin = isNaoAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Usuario getUsuarioAtualiza() {
		return usuarioAtualiza;
	}

	public void setUsuarioAtualiza(Usuario usuarioAtualiza) {
		this.usuarioAtualiza = usuarioAtualiza;
	}

}