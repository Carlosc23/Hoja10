import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Prueba {
	public static void main (String args[]){
		try {
            Connection con = DriverManager.getConnection("jdbc:neo4j:bolt://localhost", "neo4j","4jneo");
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("MATCH (x)-[rel:CORREOS]->(y)\n" +
                        "RETURN rel.length, x.name, y.name");
                while (rs.next()) {
                	System.out.print(rs.getString("x.name")+"-->"+rs.getString("rel.length")+"-->"+rs.getString("y.name"));
                    System.out.println();
                }
            }
            con.close();

        }catch (Exception ex){
            System.out.println("AAA");
        }
	}
}
