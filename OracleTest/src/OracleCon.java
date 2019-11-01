import java.sql.*;


public class OracleCon {

	public static void main(String[] args) {
		
		try {
			//step 1 - carregar driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step 2 - Criar objeto connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//10.32.4.51:1521/BDTPPRC","ELO000500","joaoh");
			
			//step 3 - Criar objeto statement
			Statement stmt = con.createStatement();
			
			//step 4 - executar query
			ResultSet rs = stmt.executeQuery("Select Count(*) "
					+ "From Clc.Tbclcr_Crsa_Crto_Fsco_Dnmc "
					+ "Where Dt_Locl_Trns = '25/05/19';");
			while(rs.next()) {
				System.out.println(rs.getInt("COUNT(*)"));
			}
			
			//step 5 - close session 
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
