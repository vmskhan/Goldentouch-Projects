package com.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="Participation")
public class Participation  implements Serializable{
	
	@EmbeddedId
	private ParticipationPk pk;
	
	private int last_attempted;
	private int score;
	private int totalQn;
	private boolean paymentDone;
	
	public Participation() {
	
	}
	
	@Override
	public String toString() {
		return "Participation [last_attempted=" + last_attempted + ", score=" + score + ", totalQn=" + totalQn + "]";
	}
	public ParticipationPk getPk() {
		return pk;
	}
	public void setPk(ParticipationPk pk) {
		this.pk = pk;
	}
	public int getLast_attempted() {
		return last_attempted;
	}
	public void setLast_attempted(int last_attempted) {
		this.last_attempted = last_attempted;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTotalQn() {
		return totalQn;
	}
	public void setTotalQn(int totalQn) {
		this.totalQn = totalQn;
	}

	public boolean isPaymentDone() {
		return paymentDone;
	}

	public void setPaymentDone(boolean paymentDone) {
		this.paymentDone = paymentDone;
	}


}
