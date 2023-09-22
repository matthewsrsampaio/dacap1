package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jogada {

	@Id
	@GeneratedValue
	private Integer id;
	private String jogador1;
	private String jogador2;
	private String resultado;
	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "Data")
	private Date data_jogo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJogador1() {
		return jogador1;
	}
	public void setJogador1(String jogador1) {
		this.jogador1 = jogador1;
	}
	public String getJogador2() {
		return jogador2;
	}
	public void setJogador2(String jogador2) {
		this.jogador2 = jogador2;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Date getData_jogo() {
		return data_jogo;
	}
	public void setData_jogo(Date data_jogo) {
		this.data_jogo = data_jogo;
	}
	
	
	
}
