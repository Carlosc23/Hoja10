import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Conexion {
	//Atributos
		private Connection con;
		private Statement stmt;
		private final String USER="neo4j";
		private final String PASSWORD= "4jneo";
		static final String  _URL = "jdbc:neo4j:bolt://localhost";

		//Metodo constructor
		public Conexion(){
			try{
				con = DriverManager.getConnection(_URL, USER,PASSWORD);	
				stmt = con.createStatement();
			}
			catch(Exception e){
				System.out.println("Error");
			}
			

		}
	
		
		//Metodo para ejecutar una consulta
		public ResultSet ejecutarConsulta(String consulta){
			ResultSet resultado = null;
			try{
				resultado = stmt.executeQuery(consulta);
			}catch(SQLException e){
				System.out.println("eror");
			}
			return resultado;
		
		}
		//Para consultar en alguna tabla
		public ResultSet getQuery(String _query){
			Statement state = null;
			ResultSet resultado = null;
			try{
				state = (Statement) con.createStatement();
				resultado = state.executeQuery(_query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			
			}
			return resultado;
		}
	
		//metodo para la muestra de las preguntas realizadas
		public String consulta(ResultSet resultado){
			String cadena ="";

			try {
				//String consulta = "SELECT FROM tutorias WHERE curso = '"+Materia+"'" ;
				//resultado = Conexion.getInstancia().ejecutarConsulta(consulta);
				while(resultado.next()){
					cadena += resultado.getString("n.name")+"\n";
					
				}	
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
			catch(NullPointerException  e1){
				
			}
			return cadena;
			
		}
		public void insertar(String nodo,String id){
			 try {
				stmt.executeUpdate("CREATE ("+nodo+": User{name:"+ id+"})");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void relacionar(String query){
			 try {
					stmt.executeUpdate(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
}
