package database;

import enums.Stands;
import org.javalite.activejdbc.DB;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PGBase extends DB {
    private static final Logger LOG = LoggerFactory.getLogger(PGBase.class);
    static {
        System.setProperty("activejdbc.get.inference", "false");
    }

    public PGBase connect(Stands stand) {
        if (!hasConnection()) {
            LOG.info("Устанавливаем соединение с базой данных...\n");
            open(getDataSource(stand));
        } else LOG.debug("Уже имеется открытое соединение с базой данных");
        return this;
    }

    public PGBase setAutoCommit(boolean autoCommit) {
        try {
            final Connection connection = connection();
            if (connection.getAutoCommit() != autoCommit) connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            disconnect();
            throw new RuntimeException(e);
        }
        return this;
    }


    public void disconnect() {
        if (hasConnection()) {
            LOG.info("Закрываем соединение с базой данных...");
            close();
        } else LOG.debug("Отсутствует открытое соединение с базой данных");
    }

    DataSource getDataSource(Stands stand) {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(stand.getDatabaseName());
        dataSource.setPortNumber(stand.getPort());
        dataSource.setServerName(stand.getDatabaseHost());
        dataSource.setUser(stand.getUserName());
        dataSource.setPassword(stand.getPassword());
        return dataSource;
    }
}
