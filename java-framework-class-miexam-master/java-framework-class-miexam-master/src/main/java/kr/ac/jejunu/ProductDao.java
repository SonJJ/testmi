package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        Product product = jdbcContext.jdbcContextForGet(statementStrategy);

        return product;
    }


    public Long insert(Product product) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(product);
        Long id = jdbcContext.jdbcContextForInset(statementStrategy);

        return id;
    }

    public void update(Product product) throws SQLException {
        StatementStrategy statementStrategy = new UpdateProductStatmentStrategy(product);
        jdbcContext.jdbcContextForUpdate(statementStrategy);

    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
