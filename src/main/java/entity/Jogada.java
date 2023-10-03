package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "jogada")
public class Jogada {

	@Id
	@GeneratedValue
	private Integer id = 0;
	private String jogador1;
	private String jogador2;
	private String jogada1;
	private String jogada2;
	private String resultado;
	private Integer papel = 0;
	private Integer pedra = 0;
	private Integer tesoura = 0;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "DATE")
	private Date data = new Date();
	
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
	public String getJogada1() {
		return jogada1;
	}
	public void setJogada1(String jogada1) {
		this.jogada1 = jogada1;
	}
	public String getJogada2() {
		return jogada2;
	}
	public void setJogada2(String jogada2) {
		this.jogada2 = jogada2;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getPapel() {
		return papel;
	}
	public void setPapel(Integer papel) {
		this.papel = papel;
	}
	public Integer getPedra() {
		return pedra;
	}
	public void setPedra(Integer pedra) {
		this.pedra = pedra;
	}
	public Integer getTesoura() {
		return tesoura;
	}
	public void setTesoura(Integer tesoura) {
		this.tesoura = tesoura;
	}	
	
}
