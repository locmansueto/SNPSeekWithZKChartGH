package org.irri.iric.portal.admin.zkui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CancelQueryThread implements Runnable {
	private org.hibernate.Session hsession = null;
	String longRunningSQL = "select * from all_objects";
	private int i = 0;

	public CancelQueryThread(org.hibernate.Session hibernateSession) {
		hsession = hibernateSession;
	}

	public void run() {
		ResultSet rset = null;
		Statement stmt = null;

		try {
			System.out.println("Executing statement....");
			rset = stmt.executeQuery(longRunningSQL);
			System.out.println("Processing resultset now..");
			while (rset.next()) {
				i++;
			}
			System.out.println("Finished with resultset and statement now..");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();

				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {

			}
		}

	}
}