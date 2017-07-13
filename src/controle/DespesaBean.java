package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.DespesaAguaDAO;
import dao.DespesaAguaJPADAO;
import dao.DespesaCartaoDAO;
import dao.DespesaCartaoJPADAO;
import dao.DespesaExtraDAO;
import dao.DespesaExtraJPADAO;
import dao.DespesaFuncionarioDAO;
import dao.DespesaFuncionarioJPADAO;
import dao.DespesaLuzDAO;
import dao.DespesaLuzJPADAO;
import modelo.DespesaUnica;
import modelo.Mes;

@ManagedBean
@ViewScoped
public class DespesaBean extends AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<DespesaUnica> despesasUnicas;
	private Mes mes;
	private List<Mes> meses;
	private int ano;
	private double despesaTotal;
	
	public DespesaBean(){
		this.setDespesasUnicas(new ArrayList<DespesaUnica>());
		this.meses = Arrays.asList(Mes.values());
	}
	
	public void somaDespesa(){
		DespesaUnica du = new DespesaUnica();
		this.despesasUnicas = new ArrayList<DespesaUnica>();
		
		DespesaFuncionarioDAO dfDAO = new DespesaFuncionarioJPADAO();
		Double somaFunc = dfDAO.soma(this.mes, this.ano);
		if(somaFunc == null){somaFunc = new Double(0);}
		du.setNome("Funcionário");
		du.setTotal(somaFunc);
		this.despesasUnicas.add(du);
		du = new DespesaUnica();
		
		DespesaAguaDAO daDAO = new DespesaAguaJPADAO();
		Double somaAgua = daDAO.soma(this.mes, this.ano);
		if(somaAgua == null){somaAgua = new Double(0);}
		du.setNome("Água");
		du.setTotal(somaAgua);
		this.despesasUnicas.add(du);
		du = new DespesaUnica();
		
		DespesaLuzDAO dlDAO = new DespesaLuzJPADAO();
		Double somaLuz = dlDAO.soma(this.mes, this.ano);
		if(somaLuz == null){somaLuz = new Double(0);}
		du.setNome("Luz");
		du.setTotal(somaLuz);
		this.despesasUnicas.add(du);
		du = new DespesaUnica();
		
		DespesaCartaoDAO dcDAO = new DespesaCartaoJPADAO();
		Double somaCartao = dcDAO.soma(this.mes, this.ano);
		if(somaCartao == null){somaCartao = new Double(0);}
		du.setNome("Cartão");
		du.setTotal(somaCartao);
		this.despesasUnicas.add(du);
		du = new DespesaUnica();
		
		DespesaExtraDAO deDAO = new DespesaExtraJPADAO();
		Double somaExtra = deDAO.soma(this.mes, this.ano);
		if(somaExtra == null){somaExtra = new Double(0);}
		du.setNome("Extra");
		du.setTotal(somaExtra);
		this.despesasUnicas.add(du);
		du = new DespesaUnica();
		
		this.despesaTotal = somaFunc.doubleValue() + somaAgua.doubleValue() + somaCartao.doubleValue() + somaExtra.doubleValue() + somaLuz.doubleValue();
	}
	
	public double somaReceitaTotal(List<DespesaUnica> d){
		double total = 0;
		for(DespesaUnica du : d){
			total = total + du.getTotal().doubleValue();
		}
		return total;
	}
	
	public Mes getMes() {
		return mes;
	}
	
	public void setMes(Mes mes) {
		this.mes = mes;
	}
	
	public List<Mes> getMeses() {
		return meses;
	}
	
	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public double getDespesaTotal() {
		return despesaTotal;
	}
	
	public void setDespesaTotal(double despesaTotal) {
		this.despesaTotal = despesaTotal;
	}

	public List<DespesaUnica> getDespesasUnicas() {
		return despesasUnicas;
	}

	public void setDespesasUnicas(List<DespesaUnica> despesasUnicas) {
		this.despesasUnicas = despesasUnicas;
	}

}
