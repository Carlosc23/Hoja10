import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Carlos
 *
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main (String [] args){
		//Conexion cn = new Conexion();
		//System.out.println(cn.consulta(cn.ejecutarConsulta("MATCH (n:User) RETURN n.name")));
		ClaseFrame miVentana = new ClaseFrame();
		miVentana.setVisible(true);

	}
}
