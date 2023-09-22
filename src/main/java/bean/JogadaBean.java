package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.JogadaDao;
import entity.Jogada;

@ManagedBean
public class JogadaBean {
	
	private Jogada jogada = new Jogada();
	private List<Jogada> listaJogador;
	
	public void clear() throws Exception{
		try {
			jogada.setJogador1(null);
			jogada.setJogador2(null);
		}catch(Exception e) {
			throw e;
		}
    }
	
	public String salvar() throws Exception{
		try {
			JogadaDao.salvar(jogada);
			//String texto = jogada.toString();
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Info =>", texto));
			clear();
			return null;
		}catch(Exception e) {
			throw e;
		}
	}
	
	//Essas desgraças de métodos precisam ter get e set pra poder serem chamados la no HTML
	public Jogada getJogada() {
		return jogada;
	}
	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	public List<Jogada> getListaJogador() throws Exception {
		return JogadaDao.buscarTodos();
	}
	public void setListaJogador(List<Jogada> listaJogador) {
		this.listaJogador = listaJogador;
	}

}
