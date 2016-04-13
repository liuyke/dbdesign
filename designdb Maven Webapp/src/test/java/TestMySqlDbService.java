import java.sql.Connection;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.liuyk.desgin.model.Column;
import org.liuyk.desgin.model.DBConnInfo;
import org.liuyk.desgin.model.TableInfo;
import org.liuyk.desgin.service.IDBService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class TestMySqlDbService {

	@Inject
	private IDBService service;
	
	@Test
	public void testGetConn() throws Exception {
		DBConnInfo connInfo = new DBConnInfo("127.0.0.1", 3306, "root", "", "mysql");
		Connection conn = service.openConnection(connInfo);
		System.out.println(conn);
	}
	
	@Test
	public void testShowDatabases() throws Exception {
		DBConnInfo connInfo = new DBConnInfo("127.0.0.1", 3306, "root", "", "mysql");
//		Connection conn = service.openConnectioin(connInfo);
		List<String> databases = service.showDatabases(connInfo);
		for (String string : databases) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testShowTableNames() throws Exception {
		DBConnInfo connInfo = new DBConnInfo("127.0.0.1", 3306, "root", "", "mysql");
//		Connection conn = service.openConnectioin(connInfo);
		List<String> databases = service.showTableNames(connInfo, "mybatis");
		for (String string : databases) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testShowTable() throws Exception {
		DBConnInfo connInfo = new DBConnInfo("127.0.0.1", 3306, "root", "", "mysql");
//		Connection conn = service.openConnectioin(connInfo);
		TableInfo info = service.showTable(connInfo, "mybatis", "t_2");
		System.out.println(info);
	}
	
	@Test
	public void testShowColumns() throws Exception {
		DBConnInfo connInfo = new DBConnInfo("127.0.0.1", 3306, "root", "", "mysql");
//		Connection conn = service.openConnectioin(connInfo);
		List<Column> cs = service.showColumns(connInfo,  "mybatis", "t_2");
		for (Column column : cs) {
			System.out.println(column);
		}
	}
	
}
