package com.kinvey.sample.tictac;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

public class GameEntity extends GenericJson{
	
	@Key
	private int totalWins;
	@Key
	private int totalLoses;
	@Key
	private int totalTies;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;
	
	
	public GameEntity(){}

	public int getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(int totalWins) {
		this.totalWins = totalWins;
	}

	public int getTotalLoses() {
		return totalLoses;
	}

	public void setTotalLoses(int totalLoses) {
		this.totalLoses = totalLoses;
	}

	public int getTotalTies() {
		return totalTies;
	}

	public void setTotalTies(int totalTies) {
		this.totalTies = totalTies;
	}
	
	public void addWin(){
		this.totalWins++;
	}
	public void addLose(){
		this.totalLoses++;
		
	}
	public void addTie(){
		this.totalTies++;
		
	}

	public KinveyMetaData.AccessControlList getAcl() {
		return acl;
	}

	public void setAcl(KinveyMetaData.AccessControlList acl) {
		this.acl = acl;
	}
	

}
