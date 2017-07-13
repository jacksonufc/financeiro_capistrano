package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import dao.DespesaFuncionarioDAO;
import dao.DespesaFuncionarioJPADAO;
import dao.FuncionarioDAO;
import dao.FuncionarioJPADAO;
import modelo.DespesaFuncionario;
import modelo.Funcionario;
import modelo.Mes;
import modelo.TipoFuncionario;

@ManagedBean
@ViewScoped
public class DespesaFuncionarioBean extends AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DespesaFuncionario despesaFuncionario;
	private List<DespesaFuncionario> despesaFuncionarios;
	private List<Funcionario> funcionarios;
	private List<Funcionario> funcSelecionados;
	private List<TipoFuncionario> tiposFuncionarios;
	private TipoFuncionario tipoFuncionario;
	private List<Mes> meses;
	private float despesaTotal;
	private Mes mes;
	private int ano;
	
	public DespesaFuncionarioBean(){
		this.tiposFuncionarios = Arrays.asList(TipoFuncionario.values());
		this.meses = Arrays.asList(Mes.values());
		this.funcSelecionados = new ArrayList<Funcionario>();
		this.funcionarios = new ArrayList<Funcionario>();
		this.despesaFuncionario = new DespesaFuncionario();
		this.despesaFuncionarios = new ArrayList<DespesaFuncionario>();
	}
	
	public void cadastrar(){
		int ano = this.despesaFuncionario.getAno();
		Mes mes = this.despesaFuncionario.getMes();
		for(int i = 0; i < this.funcSelecionados.size(); i++){
			DespesaFuncionarioDAO dDAO = new DespesaFuncionarioJPADAO();
			this.despesaFuncionario.setValor(this.funcSelecionados.get(i).getSalario());
			this.despesaFuncionario.setFuncionario(this.funcSelecionados.get(i));
			dDAO.save(this.despesaFuncionario);
			this.despesaFuncionario = new DespesaFuncionario();
			this.despesaFuncionario.setAno(ano);
			this.despesaFuncionario.setMes(mes);
		}
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.despesaFuncionario = new DespesaFuncionario();
		this.funcionarios = new ArrayList<Funcionario>();
		this.funcSelecionados = new ArrayList<Funcionario>();
	}
	
	public void selecionarParaAtualizar(DespesaFuncionario df){
		this.despesaFuncionario = df;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}
	
	public void atualizar(){
		DespesaFuncionarioDAO dfDAO = new DespesaFuncionarioJPADAO();
		dfDAO.save(this.despesaFuncionario);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.despesaFuncionario = new DespesaFuncionario();
	}
	
	public void excluir(DespesaFuncionario df){
		DespesaFuncionarioDAO dDAO = new DespesaFuncionarioJPADAO();
		dDAO.delete(df);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.despesaFuncionarios.remove(df);
	}
	
	public void buscarTodos(){
		this.despesaFuncionarios = new ArrayList<DespesaFuncionario>();
		DespesaFuncionarioDAO dDAO = new DespesaFuncionarioJPADAO();
		this.despesaFuncionarios = dDAO.find();
		this.despesaTotal = somaDespesaTotal(this.despesaFuncionarios);
	}
	
	public void buscarPorMesAno(){
		this.despesaFuncionarios = new ArrayList<DespesaFuncionario>();
		DespesaFuncionarioDAO dDAO = new DespesaFuncionarioJPADAO();
		this.despesaFuncionarios = dDAO.buscarPorMesAno(this.mes, this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesaFuncionarios);
	}
	
	public void buscarPorAno(){
		this.despesaFuncionarios = new ArrayList<DespesaFuncionario>();
		DespesaFuncionarioDAO dDAO = new DespesaFuncionarioJPADAO();
		this.despesaFuncionarios = dDAO.buscarPorAno(this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesaFuncionarios);
	}
	
	public void buscarTodosFuncionarios(){
		this.funcionarios = new ArrayList<Funcionario>();
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		this.funcionarios = fDAO.find();
	}
	
	public void buscarFuncionariosAtivos(){
		this.funcionarios = new ArrayList<Funcionario>();
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		this.funcionarios = fDAO.buscarAtivo();
	}
	
	public void buscarFuncionariosPorTipo(){
		this.funcionarios = new ArrayList<Funcionario>();
		FuncionarioDAO fDAO = new FuncionarioJPADAO();
		this.funcionarios = fDAO.buscarPorTipo(this.tipoFuncionario);
	}
	
	public float somaDespesaTotal(List<DespesaFuncionario> d){
		float total = 0;
		for(int i = 0; i < d.size(); i++){
			total += d.get(i).getValor();
		}
		return total;
	}
	
	public DespesaFuncionario getDespesaFuncionario() {
		return despesaFuncionario;
	}
	public void setDespesaFuncionario(DespesaFuncionario despesaFuncionario) {
		this.despesaFuncionario = despesaFuncionario;
	}
	public List<DespesaFuncionario> getDespesaFuncionarios() {
		return despesaFuncionarios;
	}
	public void setDespesaFuncionarios(List<DespesaFuncionario> despesaFuncionarios) {
		this.despesaFuncionarios = despesaFuncionarios;
	}
	public float getDespesaTotal() {
		return despesaTotal;
	}
	public void setDespesaTotal(float despesaTotal) {
		this.despesaTotal = despesaTotal;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncSelecionados() {
		return funcSelecionados;
	}

	public void setFuncSelecionados(List<Funcionario> funcSelecionados) {
		this.funcSelecionados = funcSelecionados;
	}

	public List<Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}

	public List<TipoFuncionario> getTiposFuncionarios() {
		return tiposFuncionarios;
	}

	public void setTiposFuncionarios(List<TipoFuncionario> tiposFuncionarios) {
		this.tiposFuncionarios = tiposFuncionarios;
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
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
	
	
	
}
