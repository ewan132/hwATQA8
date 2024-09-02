package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper(){

    }

    private static Connection getCon() throws SQLException{
        return DriverManager.getConnection(System.getProperty("app.url"),"app","pass");
    }

    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode(){
        var codeSQL = "SELECT code from auth_codes order by created limit 1";
        var con = getCon();
        var code = QUERY_RUNNER.query(con,codeSQL, new ScalarHandler<String>());
        return new DataHelper.VerificationCode(code);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getCon();
        QUERY_RUNNER.execute(connection, "DELETE FROM auth_codes");
        QUERY_RUNNER.execute(connection, "DELETE FROM card_transactions");
        QUERY_RUNNER.execute(connection, "DELETE FROM cards");
        QUERY_RUNNER.execute(connection, "DELETE FROM users");
    }
    @SneakyThrows
    public static void cleanAUTHCOdes(){
        var connection = getCon();
        QUERY_RUNNER.execute(connection, "DELETE FROM auth_codes");}
}
