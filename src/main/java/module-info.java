module vxt.dbconnect_sqlserver_crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens dbconnect to javafx.base;
    requires com.microsoft.sqlserver.jdbc;
    requires jasperreports;
    requires java.sql;
    opens vxt.dbconnect_sqlserver_crud to javafx.fxml;
    exports dbconnect;
    exports vxt.dbconnect_sqlserver_crud;    
}
