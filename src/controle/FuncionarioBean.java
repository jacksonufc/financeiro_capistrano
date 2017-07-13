package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import dao.FuncionarioDAO;
import dao.FuncionarioJPADAO;
import modelo.Funcionario;
import modelo.TipoFuncionario;

@ManagedBean
@ViewScoped
public class FuncionarioBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<TipoFuncionario> tipos;
	private TipoFuncionario tipo;
	private int salarioTotal;

	public FuncionarioBean() {
		this.funcionario = new Funcionario();
		this.funcionarios = new ArrayList<Funcionario>();
		this.tipos = Arrays.asList(TipoFuncionario.values());
	}

	public void cadastrar() {
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		fDAO.save(this.funcionario);
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.funcionario = new Funcionario();
	}

	public void selecionarParaAtualizar(Funcionario f) {
		this.funcionario = f;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}

	public void atualizar() {
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		fDAO.save(this.funcionario);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.funcionario = new Funcionario();
	}

	public void excluir(Funcionario f) {
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		fDAO.delete(f);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.funcionarios.remove(f);
	}

	public void buscarTodos() {
		this.funcionarios = new ArrayList<Funcionario>();
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		this.funcionarios = fDAO.find();
		this.salarioTotal = somaSalario(this.funcionarios);
	}

	public void buscarPorTipo() {
		this.funcionarios = new ArrayList<Funcionario>();
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		this.funcionarios = fDAO.buscarPorTipo(this.tipo);
		this.salarioTotal = somaSalario(this.funcionarios);
	}
	
	public void buscarAtivo(){
		this.funcionarios = new ArrayList<Funcionario>();
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		this.funcionarios = fDAO.buscarAtivo();
		this.salarioTotal = somaSalario(this.funcionarios);
	}
	
	public void buscarInativo(){
		this.funcionarios = new ArrayList<Funcionario>();
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		this.funcionarios = fDAO.buscarInativo();
		this.salarioTotal = somaSalario(this.funcionarios);
	}

	public int somaSalario(List<Funcionario> f) {
		int total = 0;
		for (int i = 0; i < f.size(); i++) {
			total += f.get(i).getSalario();
		}
		return total;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<TipoFuncionario> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoFuncionario> tipos) {
		this.tipos = tipos;
	}

	public int getSalarioTotal() {
		return salarioTotal;
	}

	public void setSalarioTotal(int salarioTotal) {
		this.salarioTotal = salarioTotal;
	}

	public TipoFuncionario getTipo() {
		return tipo;
	}

	public void setTipo(TipoFuncionario tipo) {
		this.tipo = tipo;
	}

}
