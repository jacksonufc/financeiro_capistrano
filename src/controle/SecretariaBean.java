package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import dao.SecretariaDAO;
import dao.SecretariaJPADAO;
import modelo.Secretaria;
import modelo.Mes;

@ManagedBean
@ViewScoped
public class SecretariaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Secretaria secretaria;
	private List<Secretaria> secretarias;
	private Mes mes;
	private int ano;
	private List<Mes> meses;
	private String nomeParcial;

	public SecretariaBean() {
		this.meses = Arrays.asList(Mes.values());
		this.secretaria = new Secretaria();
		this.secretarias = new ArrayList<Secretaria>();
	}

	public void buscarAtivos() {
		this.secretarias = new ArrayList<Secretaria>();
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		this.secretarias = secretariaDAO.buscarPorAtivo();
	}

	public void buscarInativos() {
		this.secretarias = new ArrayList<Secretaria>();
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		this.secretarias = secretariaDAO.buscarPorInativo();
	}

	public void buscarPorNomeParcial() {
		this.secretarias = new ArrayList<Secretaria>();
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		this.secretarias = secretariaDAO.buscarPorNomeParcial(this.nomeParcial.toUpperCase());
	}

	public void cadastrar() {
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		secretariaDAO.save(this.secretaria);
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.secretaria = new Secretaria();
	}

	public void selecionarParaAtualizar(Secretaria s) {
		this.secretaria = s;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}

	public void atualizar() {
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		secretariaDAO.save(this.secretaria);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.secretaria = new Secretaria();
	}

	public void excluir(Secretaria s) {
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		secretariaDAO.delete(s);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.secretarias.remove(s);
	}

	public int sortByNome(String n1, String n2) {
		return n1.compareTo(n2);
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public List<Secretaria> getSecretarias() {
		return secretarias;
	}

	public void setSecretarias(List<Secretaria> secretarias) {
		this.secretarias = secretarias;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}

	public String getNomeParcial() {
		return nomeParcial;
	}

	public void setNomeParcial(String nomeParcial) {
		this.nomeParcial = nomeParcial;
	}

}
