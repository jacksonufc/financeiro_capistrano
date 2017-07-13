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
import dao.ReceitaFetamceDAO;
import dao.ReceitaFetamceJPADAO;
import modelo.Secretaria;
import modelo.Mes;
import modelo.ReceitaFetamce;

@ManagedBean
@ViewScoped
public class ReceitaFetamceBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private ReceitaFetamce receitaFetamce;
	private List<ReceitaFetamce> receitasFetamces;
	private List<Mes> meses;
	private Mes mes;
	private int ano;
	private float receitaTotal;
	
	public ReceitaFetamceBean(){
		this.receitaFetamce = new ReceitaFetamce();
		this.receitasFetamces = new ArrayList<ReceitaFetamce>();
		this.meses = Arrays.asList(Mes.values());
	}
	
	public void cadastrar(){
		try {
			SecretariaDAO sDAO = new SecretariaJPADAO();
			Secretaria s = sDAO.buscarPorNome(this.receitaFetamce.getSecretaria().getNome());
			this.receitaFetamce.setSecretaria(s);
			ReceitaFetamceDAO rfeDAO = new ReceitaFetamceJPADAO();
			rfeDAO.save(this.receitaFetamce);
			displayInfoMessageToUser("Cadastrado com sucesso");
			this.receitaFetamce = new ReceitaFetamce();
		} catch (Exception e) {
			displayInfoMessageToUser("Aluno ainda não cadastrado!");
		}
		
	}
	
	public void selecionarParaAtualizar(ReceitaFetamce r){
		this.receitaFetamce = r;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}
	
	public void atualizar(){
		ReceitaFetamceDAO rfeDAO = new ReceitaFetamceJPADAO();
		rfeDAO.save(this.receitaFetamce);
		displayInfoMessageToUser("Atualizado com sucesso");
		this.receitaFetamce = new ReceitaFetamce();
	}
	
	public void excluir(ReceitaFetamce r){
		ReceitaFetamceDAO rfeDAO = new ReceitaFetamceJPADAO();
		rfeDAO.delete(r);
		displayInfoMessageToUser("Excluido com sucesso");
		this.receitasFetamces.remove(r);
	}
	
	public void buscarPorAno(){
		this.receitasFetamces = new ArrayList<ReceitaFetamce>();
		ReceitaFetamceDAO rfeDAO = new ReceitaFetamceJPADAO();
		this.receitasFetamces = rfeDAO.buscarPorAno(this.ano);
		this.receitaTotal = somaReceitaTotal(this.receitasFetamces);
	}
	
	public void buscarPorMesAno(){
		this.receitasFetamces = new ArrayList<ReceitaFetamce>();
		ReceitaFetamceDAO rfeDAO = new ReceitaFetamceJPADAO();
		this.receitasFetamces = rfeDAO.buscarPorMesAno(this.mes, this.ano);
		this.receitaTotal = somaReceitaTotal(this.receitasFetamces);
	}
	
	public List<String> buscarTodosAlunos(String query){
		SecretariaDAO SecretariaDAO = new SecretariaJPADAO();
		List<String> nomes =  SecretariaDAO.buscarPorNomeInicial(query.toUpperCase());
		return nomes;
	}
	
	public float somaReceitaTotal(List<ReceitaFetamce> rfe){
		float total = 0;
		for(ReceitaFetamce r : rfe){
			total += r.getValor();
		}
		return total;
	}
	
	
	public ReceitaFetamce getReceitaFetamce() {
		return receitaFetamce;
	}
	public void setReceitaFetamce(ReceitaFetamce receitaFetamce) {
		this.receitaFetamce = receitaFetamce;
	}
	public List<ReceitaFetamce> getReceitasFetamces() {
		return receitasFetamces;
	}
	public void setReceitasFetamces(List<ReceitaFetamce> receitasFetamces) {
		this.receitasFetamces = receitasFetamces;
	}
	public List<Mes> getMeses() {
		return meses;
	}
	public void setMeses(List<Mes> meses) {
		this.meses = meses;
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
	public float getReceitaTotal() {
		return receitaTotal;
	}
	public void setReceitaTotal(float receitaTotal) {
		this.receitaTotal = receitaTotal;
	}
	
	

}
