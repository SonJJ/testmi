package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {
    private DaoFactory daoFactory;
    private ProductDao jejuProductDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        jejuProductDao = applicationContext.getBean("getProductDao", ProductDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = jejuProductDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }
    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = testInsertUser(product);

        Product insertProduct = jejuProductDao.get(id);
        assertEquals(id, insertProduct.getId());
        assertEquals(product.getTitle(), insertProduct.getTitle());
        assertEquals(product.getPrice(), insertProduct.getPrice());
    }

    private Long testInsertUser(Product product) throws ClassNotFoundException, SQLException {
        product.setTitle("aaaa");
        product.setPrice(1234);
        return jejuProductDao.insert(product);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = testInsertUser(product);

        product.setId(id);
        product.setTitle("ssss");
        product.setPrice(1231);
        jejuProductDao.update(product);

        Product updateProduct = jejuProductDao.get(id);
        assertEquals(product.getId(), updateProduct.getId());
        assertEquals(product.getTitle(), updateProduct.getTitle());
        assertEquals(product.getPrice(), updateProduct.getPrice());
    }
    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = testInsertUser(product);

        jejuProductDao.delete(id);

        Product deleteProduct = jejuProductDao.get(id);
        assertEquals(null, deleteProduct);
    }
}
