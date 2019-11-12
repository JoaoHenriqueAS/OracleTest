import java.sql.*;

public class Example {

	//JDBC driver e database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
	static final String DB_URL = "jdbc:oracle:thin:@//[host]:[port]/DB";

	//Usuario e senha
	static final String USER = "user";
	static final String PASS = "pass";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//Driver JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Abrindo conexão
			System.out.println("Conectando ao banco...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Conexão realizada com sucesso!!!");

			//Executar a query
			System.out.println("Creating statement...\n");
			stmt = conn.createStatement();
			String sql;
			sql = "Select From Clc.Tbclcr_Crsa_Crto_Fsco_Dnmc Where Dt_Locl_Trns = '25/05/19' AND cd_atzc = '88888'";
			ResultSet rs = stmt.executeQuery(sql);

			//Extração de dados
			while(rs.next()){

				String CD_ATZC = rs.getString(1);
				int CD_BNDR = rs.getInt(2);

				System.out.print("Codigo Autorização: " + CD_ATZC);
				System.out.println(", Bandeira: " + CD_BNDR);
			}
			//Limpar dados
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//Fechar conexão
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		//System.out.println("Goodbye!");
	}
}

