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
	private String teamLead;
	
	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	public Team(String teamName, int teamActive, String teamLead) {
		this.teamName = teamName;
		this.teamActive = teamActive;
		this.teamLead = teamLead;
	}
	
	public Team(int teamID, String teamName, int teamActive, String teamLead) {
		this.teamID = teamID;
		this.teamName = teamName;
		this.teamActive = teamActive;
		this.teamLead = teamLead;
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
	
	public String getTeamLead() {
		return teamLead;
	}

	public void setTeamLead(String teamLead) {
		this.teamLead = teamLead;
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
				teams.add(new Team(rs.getInt("team_id"), rs.getString("team_name"), 
						rs.getInt("team_active"), rs.getString("team_lead")));
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
	
	public static Team getTeamById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Team team = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbteam WHERE team_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next())
				team = new Team(rs.getInt("team_id"), 
						rs.getString("team_name"), rs.getInt("team_active"),
						rs.getString("team_lead"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return team;
	}
	
	public static ArrayList<CustomerServiceRepresentative> getTeamLeads() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT DISTINCT tbcsr.csr_id, tbcsr.csr_name FROM tbteam "
					+ "INNER JOIN tbcsr ON tbteam.team_lead = tbcsr.csr_id WHERE " + "tbteam.team_lead IS NOT NULL");
			rs = ps.executeQuery();
			while (rs.next())
				csrs.add(new CustomerServiceRepresentative(rs.getString(1), rs.getString(2)));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBConnect.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return csrs;
	}
	
	public void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("INSERT INTO tbteam (team_name, team_active, team_lead) " +
					"VALUES (?, ?, ?)");
			ps.setString(1, this.getTeamName());
			ps.setInt(2, this.getTeamActive());
			ps.setString(3, this.getTeamLead());
			ps.executeUpdate();
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
	}


}
