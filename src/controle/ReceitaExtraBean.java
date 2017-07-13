package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import dao.ReceitaExtraDAO;
import dao.ReceitaExtraJPADAO;
import modelo.Mes;
import modelo.ReceitaExtra;

@ManagedBean
@ViewScoped
public class ReceitaExtraBean extends AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ReceitaExtra extra;
	private List<ReceitaExtra> extras;
	private List<Mes> meses;
	private Mes mes;
	private int ano;
	private float receitaTotal;
	
	public ReceitaExtraBean(){
		this.extra = new ReceitaExtra();
		this.extras = new ArrayList<ReceitaExtra>();
		this.meses = Arrays.asList(Mes.values());
	}
	
	public void cadastrar(){
		ReceitaExtraDAO reDAO = new ReceitaExtraJPADAO();
		reDAO.save(this.extra);
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.extra = new ReceitaExtra();
	}
	
	public void selecionarParaAtualizar(ReceitaExtra re){
		this.extra = re;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}
	
	public void atualizar(){
		ReceitaExtraDAO reDAO = new ReceitaExtraJPADAO();
		reDAO.save(this.extra);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.extra = new ReceitaExtra();
	}
	
	public void excluir(ReceitaExtra re){
		ReceitaExtraDAO reDAO = new ReceitaExtraJPADAO();
		reDAO.delete(re);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.extras.remove(re);
	}
	
	public void buscarTodos(){
		this.extras = new ArrayList<ReceitaExtra>();
		ReceitaExtraDAO reDAO = new ReceitaExtraJPADAO();
		this.extras = reDAO.find();
		this.receitaTotal = somaReceitaTotal(this.extras);
	}
	
	public void buscarPorAno(){
		this.extras = new ArrayList<ReceitaExtra>();
		ReceitaExtraDAO reDAO = new ReceitaExtraJPADAO();
		this.extras = reDAO.buscarPorAno(this.ano);
		this.receitaTotal = somaReceitaTotal(this.extras);	
	}
	
	public void buscarPorMesAno(){
		this.extras = new ArrayList<ReceitaExtra>();
		ReceitaExtraDAO reDAO = new ReceitaExtraJPADAO();
		this.extras = reDAO.buscarPorMesAno(this.mes, this.ano);
		this.receitaTotal = somaReceitaTotal(this.extras);
	}
	
	public float somaReceitaTotal(List<ReceitaExtra> re){
		float total = 0;
		for(ReceitaExtra r : re){
			total += r.getValor();
		}
		return total;
	}
	
	public ReceitaExtra getExtra() {
		return extra;
	}
	public void setExtra(ReceitaExtra extra) {
		this.extra = extra;
	}
	public List<ReceitaExtra> getExtras() {
		return extras;
	}
	public void setExtras(List<ReceitaExtra> extras) {
		this.extras = extras;
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
