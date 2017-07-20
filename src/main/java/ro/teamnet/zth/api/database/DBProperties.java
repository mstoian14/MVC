package ro.teamnet.zth.api.database;

/**
 * Created by Raluca.Russindilar on 02.07.2016.
 */
public interface DBProperties {
    // de inlocuit cu IP-ul din Docker
    String IP = "192.168.99.100";
    String PORT = "1521";
    // de inlocuit cu utilizatorul vostru
    String USER = "sys as dba";
    // de inlocuit cu parola voastra
    String PASS = "admin";
    String SID = "xe";
    String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";

}
