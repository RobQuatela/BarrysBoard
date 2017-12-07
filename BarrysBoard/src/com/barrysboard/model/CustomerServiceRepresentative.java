package com.barrysboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerServiceRepresentative {

	private String csrID;
	private String csrName;
	private String csrActive;
	private String empType;
	private String teamName;
	
	public CustomerServiceRepresentative(String id, String name, String active) {
		csrID = id;
		csrName = name;
		csrActive = active;
	}
	
	public CustomerServiceRepresentative(String id, String name) {
		csrID = id;
		csrName = name;
	}
	
	public CustomerServiceRepresentative(String id, String name, String active, String empType) {
		csrID = id;
		csrName = name;
		csrActive = active;
		this.empType = empType;
	}
	
	public CustomerServiceRepresentative(String id) {
		this.csrID = id;
	}

	public String getCsrID() {
		return csrID;
	}

	public void setCsrID(String csrID) {
		this.csrID = csrID;
	}

	public String getCsrName() {
		return csrName;
	}

	public void setCsrName(String csrName) {
		this.csrName = csrName;
	}
	
	public String getCsrActive() {
		return csrActive;
	}

	public void setCsrActive(String csrActive) {
		this.csrActive = csrActive;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public static ArrayList<CustomerServiceRepresentative> getCSRByTeam(int teamID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		if(teamID != 0) {
			try {
				con = DBConnect.connect();
				ps = con.prepareStatement(
						"SELECT tbcsr.csr_id, tbcsr.csr_name, tbteam.team_name FROM tbcsr INNER JOIN tbcsrteam ON tbcsr.csr_id = tbcsrteam.csr_id "
								+ "INNER JOIN tbteam ON tbcsrteam.team_id = tbteam.team_id WHERE tbcsrteam.team_id = ?");
				ps.setInt(1, teamID);
				rs = ps.executeQuery();
				while (rs.next()) {
					CustomerServiceRepresentative csr = new CustomerServiceRepresentative(rs.getString(1),
							rs.getString(2));
					csr.setTeamName(rs.getString(3));
					csrs.add(csr);
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
		} else {
			try {
				con = DBConnect.connect();
				ps = con.prepareStatement(
						"SELECT tbcsr.csr_id, tbcsr.csr_name, tbteam.team_name FROM tbcsr INNER JOIN tbcsrteam ON tbcsr.csr_id = tbcsrteam.csr_id "
								+ "INNER JOIN tbteam ON tbcsrteam.team_id = tbteam.team_id WHERE tbcsr.emptype_id = 'CSR'");
				rs = ps.executeQuery();
				while (rs.next()) {
					CustomerServiceRepresentative csr = new CustomerServiceRepresentative(rs.getString(1),
							rs.getString(2));
					if(rs.getString(3) == null)
						csr.setTeamName("No Current Team");
					else
						csr.setTeamName(rs.getString(3));
					csrs.add(csr);
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
		}
		
		return csrs;
	}
	
	public static ArrayList<CustomerServiceRepresentative> getCSRsElligble() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE csr_active = 'L'");
			rs = ps.executeQuery();
			while(rs.next()) {
				csrs.add(new CustomerServiceRepresentative(rs.getString("csr_id"), rs.getString("csr_name"),
						rs.getString("csr_active"), rs.getString("emptype_id")));
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
		
		return csrs;
	}
	
	public static ArrayList<CustomerServiceRepresentative> getCSRs() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbcsr");
			rs = ps.executeQuery();
			while(rs.next()) {
				csrs.add(new CustomerServiceRepresentative(rs.getString("csr_id"), rs.getString("csr_name"), 
						rs.getString("csr_active"), rs.getString("emptype_id")));
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
		
		return csrs;
	}
	
	public static ArrayList<CustomerServiceRepresentative> getCSRs(String nameNo, String status, String role) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		ArrayList<CustomerServiceRepresentative> csrs = new ArrayList<>();
		if(role.equalsIgnoreCase("other")) {
			sql = "emptype_id IS NULL";
			role = null;
		}
		else
			sql = "emptype_id = ?";
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE (csr_id LIKE ? OR csr_name LIKE ?) AND csr_active = ? AND " + sql + "");
			ps.setString(1, "%" + nameNo + "%");
			ps.setString(2, "%" + nameNo + "%");
			ps.setString(3, status);
			if(role != null)
				ps.setString(4, role);
			rs = ps.executeQuery();
			while(rs.next())
				csrs.add(new CustomerServiceRepresentative(rs.getString("csr_id"), rs.getString("csr_name"),
						rs.getString("csr_active"), rs.getString("emptype_id")));
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
		
		return csrs;
	}
	
	public String addToTeam(int teamId) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean isDup = this.checkForTeamDup(teamId);
		String result = "";
		
		if(!isDup) {
			try {
				con = DBConnect.connect();
				ps = con.prepareStatement("INSERT INTO tbcsrteam (csr_id, team_id) VALUES (?, ?)");
				ps.setString(1, this.getCsrID());
				ps.setInt(2, teamId);
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
			result = "You've successfully added " + this.getCsrName() + " to the team!";
		} else
			result = this.getCsrName() + " is already added to this team!";
		
		return result;
	}
	
	private boolean checkForTeamDup(int teamId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDup = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbcsrteam WHERE csr_id = ? AND team_id = ?");
			ps.setString(1, this.getCsrID());
			ps.setInt(2, teamId);
			rs = ps.executeQuery();
			if(rs.next())
				isDup = true;
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
		
		return isDup;
	}
	
	public void removeFromTeam(int teamNo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("DELETE FROM tbcsrteam WHERE csr_id = ? AND team_id = ?");
			ps.setString(1, this.getCsrID());
			ps.setInt(2, teamNo);
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

	private void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		String empType;
		try {
			empType = this.getEmpType();
		} catch(Exception e) {
			empType = null;
		}

		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("INSERT INTO tbcsr (csr_id, csr_name, csr_active, emptype_id) VALUES (?, ?, ?, ?)");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCsrName());
			ps.setString(3, this.getCsrActive());
			ps.setString(4, empType);
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
	
	public void update() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("UPDATE tbcsr SET csr_name = ?, csr_active = ?, emptype_id = ? WHERE csr_id = ?");
			ps.setString(1, this.getCsrName());
			ps.setString(2, this.getCsrActive());
			ps.setString(3, this.getEmpType());
			ps.setString(4, this.getCsrID());
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
	
	private boolean checkForDup() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE csr_id = ?");
			ps.setString(1, this.getCsrID());
			rs = ps.executeQuery();
			if(rs.next()) {
				check = true;
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
		
		return check;
	}
	
	public static String getName(String csrID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT csr_name FROM tbcsr WHERE csr_id = ?");
			ps.setString(1, csrID);
			rs = ps.executeQuery();
			if(rs.next())
				name = rs.getString(1);
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
		
		return name;
	}
	
	public static boolean isEstimator(String csrID) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isEstimator = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT emptype_id FROM tbcsr WHERE csr_id = ? AND (emptype_id != 'CSR' OR emptype_id IS NULL)");
			ps.setString(1, csrID);
			rs = ps.executeQuery();
			if(rs.next())
				isEstimator = true;
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
		
		return isEstimator;
	}
	
	private boolean checkForUpdate() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isUpdate = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE csr_id = ? AND (csr_name != ? OR csr_active != ?)");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCsrName());
			ps.setString(3, this.getCsrActive());
			rs = ps.executeQuery();
			if(rs.next()) {
				isUpdate = true;
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
		
		return isUpdate;
	}
	
	public void authenticate() {
		boolean isDup = this.checkForDup();
		
		if(!isDup) {
			this.insert();
		}
	}
}
