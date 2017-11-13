package com.barrysboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Team {

	private int teamID;
	private String teamName;
	private int teamActive;
	
	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	public Team(int teamID, String teamName, int teamActive) {
		this.teamID = teamID;
		this.teamName = teamName;
		this.teamActive = teamActive;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamID() {
		return teamID;
	}
	
	public int getTeamActive() {
		return teamActive;
	}

	public void setTeamActive(int teamActive) {
		this.teamActive = teamActive;
	}

	public static ArrayList<Team> getTeams(int active) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Team> teams = new ArrayList<>();
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbteam WHERE team_active = ?");
			ps.setInt(1, active);
			rs = ps.executeQuery();
			while(rs.next()) {
				teams.add(new Team(rs.getInt("team_id"), rs.getString("team_name"), rs.getInt("team_active")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBConnect.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return teams;
	}
}
