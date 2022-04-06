package com.util;

import com.entities.Participation;
import com.entities.User;

public class UserPart implements Comparable<UserPart>{
	private User user;
	private Participation participation;
	private int rank;
	public UserPart(User user, Participation participation) {
		super();
		this.user = user;
		this.participation = participation;
	}
	@Override
	public String toString() {
		return "UserPart [user=" + user + ", participation=" + participation + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Participation getParticipation() {
		return participation;
	}
	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	@Override
	public int compareTo(UserPart o) {
		return this.getParticipation().getScore() > o.getParticipation().getScore() ? 1:-1;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
