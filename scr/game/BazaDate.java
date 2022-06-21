package game;

import java.sql.*;

public class BazaDate {

    private static BazaDate db = null;
    private Connection con = null;
    private static Statement stmt = null;

    private BazaDate(Connection con)
    {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:JOC.db");
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String s = "CREATE TABLE JOC" + "(NrCoin INT NOT NULL, " + "Nivel INT NOT NULL, " + "NrMaximCoin INT NOT NULL)";
            stmt.execute(s);
            stmt.close();
            con.commit();
        } catch ( DataBaseException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Records created successfully");
    }
    public static void afisare() throws SQLException{
        ResultSet rst = stmt.executeQuery( "SELECT * FROM JOC;" );
        while ( rst.next() ) {
            int nrCoin = rst.getInt("NrCoin");
            int nivel = rst.getInt("Nivel");
            int nrMaximCoin = rst.getInt("NrMaximCoin");
            System.out.print("NrCoin = " + nrCoin);
            System.out.print(", Nivel = " + nivel);
            System.out.print(", NrMaximCoin = " + nrMaximCoin + "\n");

        }
        rst.close();
    }

    public static BazaDate create(Connection c)
    {
        if(db == null)
            db = new BazaDate(c);
        return db;
    }

    public void close()
    {
        if(con != null)
        {
            try{
                stmt.close();
                con.close();
            }catch(DataBaseException e){
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void finalize()
    {
        close();
    }

    public static void addRecord(Connection c, int nrCoin, int level, int nrMaxCoin) throws SQLException {
        String sql = "INSERT INTO JOC " + "VALUES (?,?,?);";
        PreparedStatement pstmt = c.prepareStatement(sql);

        pstmt.setInt(1, nrCoin);
        pstmt.setInt(2, level);
        pstmt.setInt(3, nrMaxCoin);

        pstmt.executeUpdate();
    }

}