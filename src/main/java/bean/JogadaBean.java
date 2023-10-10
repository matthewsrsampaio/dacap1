package bean;

import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import dao.JogadaDao;
import entity.Jogada;

@ManagedBean
@ViewScoped
public class JogadaBean {
	
	private Jogada jogada = new Jogada();
	private List<Jogada> listaJogador;
	
//	public void onCellEdit(CellEditEvent<Jogada> event) throws NumberFormatException, Exception {
//        Object oldValue = event.getOldValue();
//        Object newValue = event.getNewValue();
//
//        String player1Velho = String.valueOf(event.getOldValue().getJogador1().toString());
//        String player1Novo = String.valueOf(event.getNewValue().getJogador1().toString());
//        String player2Velho = String.valueOf(event.getOldValue().getJogador2().toString());
//        String player2Novo = String.valueOf(event.getNewValue().getJogador2().toString());
//        Integer id = event.getOldValue().getId();
//        
//        Jogada j = JogadaDao.buscarPorId(id);
//        
//        System.out.println(player1Novo);
//        System.out.println(player2Novo);
//        
//        if(player1Velho.toString().equals(j.getJogador1().toString())) {
//        	j.setJogador1(player1Novo.toString());
//        } else if(player2Velho.toString().equals(j.getJogador2().toString())) {
//        	j.setJogador2(player2Novo.toString());
//        }
//        
//        System.out.println(j.getJogador1());
//        System.out.println(j.getJogador2());
//                
//        JogadaDao.editar(j);
//
//        if (newValue != null && !newValue.equals(oldValue)) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, ". Célula editada -> Valor antigo: " + oldValue + ", Valor novo:" + newValue));
//        }
//    }
	
	public void onRowEdit(RowEditEvent<Jogada> event) throws Exception {
//		Jogada jogada = JogadaDao.buscarPorId(event.getObject().getId());
//		
//		if(jogada.getResultado().equals(jogada.getJogador1())) {
//			jogada.setResultado(String.valueOf(event.getObject().getJogador1()));
//		} else if(jogada.getResultado().equals(jogada.getJogador2())) {
//			jogada.setResultado(String.valueOf(event.getObject().getJogador2()));
//		}
//		
//		jogada.setJogador1(String.valueOf(event.getObject().getJogador1()));
//		jogada.setJogador2(String.valueOf(event.getObject().getJogador2()));
		
		JogadaDao.editar(event.getObject());
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ID: " + event.getObject().getId()+"   ->   Jogadores Editados: ", String.valueOf(event.getObject().getJogador1() + " e " + event.getObject().getJogador2())));
    }
	
    public void onRowCancel(RowEditEvent<Jogada> event) throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ID: " + event.getObject().getId()+"   ->   Edição cancelada: ", String.valueOf(event.getObject().getJogador1() + " e " + event.getObject().getJogador2())));
    }	
	
	public void clear() throws Exception{
		jogada.setJogador1(null);
		jogada.setJogador2(null);
    }
    
	public String salvar() {
		//papel = 0   
		//pedra = 1
		//tesoura = 2		
		try {
			Integer pedra=0;
			Integer papel=0;
			Integer tesoura=0;
			
			Random random = new Random();
			int jogadaEspelho1 = random.nextInt(3);
			int jogadaEspelho2 = random.nextInt(3);
			
			if(jogadaEspelho1==0&&jogadaEspelho2==0) {
				jogada.setResultado("Empate");
				jogada.setJogada1("Papel");
				jogada.setJogada2("Papel");
				papel++;
				papel++;
			}else if(jogadaEspelho1==1&&jogadaEspelho2==1) {
				jogada.setResultado("Empate");
				jogada.setJogada1("Pedra");
				jogada.setJogada2("Pedra");
				pedra++;
				pedra++;
			} else if(jogadaEspelho1==2&&jogadaEspelho2==2) {
				jogada.setResultado("Empate");
				jogada.setJogada1("Tesoura");
				jogada.setJogada2("Tesoura");
				tesoura++;
				tesoura++;
			} else if(jogadaEspelho1==0&&jogadaEspelho2==1) {
				jogada.setResultado("Jogador 1");
//				jogada.setResultado(jogada.getJogador1());
				jogada.setJogada1("Papel");
				jogada.setJogada2("Pedra");
				papel++;
				pedra++;
			} else if(jogadaEspelho1==0&&jogadaEspelho2==2) {
				jogada.setResultado("Jogador 2");
//				jogada.setResultado(jogada.getJogador2());
				jogada.setJogada1("Papel");
				jogada.setJogada2("Tesoura");
				tesoura++;
				papel++;
			} else if(jogadaEspelho1==1&&jogadaEspelho2==0) {
				jogada.setResultado("Jogador 2");
//				jogada.setResultado(jogada.getJogador2());
				jogada.setJogada1("Pedra");
				jogada.setJogada2("Papel");
				papel++;
				pedra++;
			} else if(jogadaEspelho1==1&&jogadaEspelho2==2) {
				jogada.setResultado("Jogador 1");
//				jogada.setResultado(jogada.getJogador1());
				jogada.setJogada1("Pedra");
				jogada.setJogada2("Tesoura");
				pedra++;
				tesoura++;
			} else if(jogadaEspelho1==2&&jogadaEspelho2==0) {
				jogada.setResultado("Jogador 1");
//				jogada.setResultado(jogada.getJogador1());
				jogada.setJogada1("Tesoura");
				jogada.setJogada2("Papel");
				tesoura++;
				papel++;
			} else if(jogadaEspelho1==2&&jogadaEspelho2==1) {
				jogada.setResultado("Jogador 2");
//				jogada.setResultado(jogada.getJogador2());
				jogada.setJogada1("Tesoura");
				jogada.setJogada2("Pedra");
				pedra++;
				tesoura++;
			}
			
			jogada.setPapel(papel);
			jogada.setPedra(pedra);
			jogada.setTesoura(tesoura);
			
			JogadaDao.salvar(jogada);
			
			clear();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Objeto salvo com sucesso!", ""));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Resultado do jogo: "+jogada.getResultado(), null));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Jogada> deletar() throws Exception {
		try {
			JogadaDao.deletar(jogada);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, jogada.getId().toString(), "Objeto excluído com sucesso!"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaJogador = JogadaDao.buscarTodos();
	}
	
	public Long buscarSomaPapel() throws Exception {
		return JogadaDao.buscarSomaPapel();
	}
	
	public Long buscarSomaPedra() throws Exception {
		return JogadaDao.buscarSomaPedra();
	}
	
	public Long buscarSomaTesoura() throws Exception {
		return JogadaDao.buscarSomaTesoura();
	}
	
	public void mostrarSomatorio() throws Exception {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Somatório das saídas -> ", "Papel: "+buscarSomaPapel().toString()+", Pedra: "+buscarSomaPedra().toString()+", Tesoura: "+buscarSomaTesoura().toString()+"."));
	}
	
	//GETTERS AND SETTERS
	public Jogada getJogada() {
		return jogada;
	}
	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	public List<Jogada> getListaJogador() throws Exception {
		listaJogador = (listaJogador==null) ? listaJogador = JogadaDao.buscarTodos():listaJogador;
		return listaJogador;
	}
	public void setListaJogador(List<Jogada> listaJogador) {
		this.listaJogador = listaJogador;
	}
}