package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.JogadaDao;
import entity.Jogada;

@ManagedBean
public class JogadaBean {
	
	private Jogada jogada = new Jogada();
	//private List<Jogada> listaJogada;
	
	public String salvar() throws Exception{
		try {
			JogadaDao.salvar(jogada);
			//String texto = jogada.toString();
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Info =>", texto));
			//clear();
			return null;
		}catch(Exception e) {
			throw e;
		}
	}

	public Jogada getJogada() {
		return jogada;
	}

	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	
	

}
