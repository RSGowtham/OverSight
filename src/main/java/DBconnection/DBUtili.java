//$Id$
package DBconnection;
import POJOclasses.MasterClass;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


enum DatabaseOperation {
	INSERT("INSERT INTO %s (%s) VALUES (%s) WHERE %s"), SELECT("SELECT * FROM %s INNER JOIN %s ON %s = %s WHERE %s"), UPDATE("UPDATE %s SET %s WHERE %s"), DELETE("DELETE FROM %s WHERE %s");

	private String sqlTemplate;

	DatabaseOperation(String sqlTemplate) {
		this.sqlTemplate = sqlTemplate;
	}

	public String getSqlTemplate() {
		return sqlTemplate;
	}
}

public class DBUtili {

	public static <Child extends MasterClass> boolean insert(Child obj) {
		// LinkedList column
		LinkedList<String> colum = obj.getColumnNames();
		int size = colum.size();
		if (size < 1) {
			return false;
		}
		// ? mark
		String ques = questionMarksBuilder(size);
		// column names
		String columns = columnBuilder(colum);
		// Table name
		String table_name = obj.getTableName();
		String where = null;
		if (obj.getwhere().size() >= 1) {
			where = conditionBuilder(obj, true);
		}
		// Query
		String query;
		if (where != null) {
			query = String.format(DatabaseOperation.INSERT.getSqlTemplate(), table_name, columns, ques, where);
		} else {

			String fullquery = String.format(DatabaseOperation.INSERT.getSqlTemplate(), table_name, columns, ques, where);
			int e = fullquery.indexOf("WHERE");
			query = fullquery.substring(0, e);

		}
		return exe(obj, query);
	}

	public static <Child extends MasterClass> List<Map<String, Object>> select(Child obj) {
		// Table name
		String table1 = obj.getTableName();
		// Where Condition
		String where = null;
		if (obj.getwhere().size() >= 1) {
			where = conditionBuilder(obj, true);
		}
		// Query
		String query;
		if (obj.getJoin()) {
			String table2 = obj.getTableNameTwo();
			String commonfield = obj.getCommonField();
			query = String.format(DatabaseOperation.SELECT.getSqlTemplate(), table1, table2, table1 + commonfield, table2 + commonfield, table1 + where);
		} else {
			if (where != null) {
				String temp = String.format(DatabaseOperation.SELECT.getSqlTemplate(), table1, null, null, "end", where);
				query = temp.replace("INNER JOIN null ON null = end", "");
			} else {
				String fullquery = String.format(DatabaseOperation.SELECT.getSqlTemplate(), table1, null, null, "end", where);
				int e = fullquery.indexOf("INNER");
				query = fullquery.substring(0, e);
			}
		}
		String tempquery;
		if(obj.getCount())
		{
			tempquery=query.replace("*", "COUNT(*)");
			query=tempquery;
		}
		System.out.println(query+"::::::::");
		ResultSet rs;
		List<Map<String, Object>> resultMap = new ArrayList<>();
		LinkedHashMap<String, Object> values = obj.getwhere();
		int index=1;
		try (Connection con = JDBCconnect.connect(); PreparedStatement pst = con.prepareStatement(query);) {
			for (String key : values.keySet()) {
				pst.setObject(index++, values.get(key));
			}
			rs = pst.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				 LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();
			        for (int i = 1; i <= columnCount; i++) {
			            String columnName = metaData.getColumnName(i);
			            Object columnValue = rs.getObject(i);
			            if (columnValue == null) {
			                rowData.put(columnName, "empty");
			            } else {
			                rowData.put(columnName, columnValue);
			            }
			        }
			        resultMap.add(rowData);
			 
			}
			System.out.println(resultMap+">>>>>>>>");

		} catch (Exception e) {
			System.out.println(e);
		}

		return resultMap;
	}

	public static <Child extends MasterClass> boolean update(Child obj) {
		// LinkedList column
		LinkedList<String> colum = obj.getColumnNames();
		int size = colum.size();
		if (size < 1) {
			return false;
		}
		// Table name
		String table_name = obj.getTableName();
		// Where Condition
		String where = null;
		if (obj.getwhere().size() >= 1) {
			where = conditionBuilder(obj, true);
		}
		// Query
		String query;
		// SET operation
		String set = conditionBuilder(obj, false);
		if (where != null) {
			query = String.format(DatabaseOperation.UPDATE.getSqlTemplate(), table_name, set, where);
		} else {
			return false;
		}
		return exe(obj, query);
	}

	public static <Child extends MasterClass> boolean delete(Child obj) {
		String table_name = obj.getTableName();
		// Where Condition
		String where = null;
		if (obj.getwhere().size() >= 1) {
			where = conditionBuilder(obj, true);
		}
		// Query
		String query;
		if (where != null) {
			query = String.format(DatabaseOperation.DELETE.getSqlTemplate(), table_name, where);
		} else {
			return false;
		}	
		return exe(obj, query); 
	}
	// Where & Update String Builder
	public static <Child extends MasterClass> String conditionBuilder(Child obj, boolean updateOrWhere) {
		StringBuilder query = new StringBuilder();
		if (updateOrWhere) {
			LinkedHashMap<String, Object> values = obj.getwhere();
			int size = values.size() - 1;
			String condition = "";
			if (obj.getWhereCondition() == 1) {
				condition = "AND";
			} else if (obj.getWhereCondition() == 2) {
				condition = "OR";
			} else {
				condition = "";
			}
			// for(String key: values.get())
			for (String key : values.keySet()) {
				query.append(key + "= ?");
				if (size > 0) {
					query.append(" " + condition + " ");
					size--;
				}
			}
		} else {
			LinkedList<String> con = obj.getColumnNames();
			int size = con.size() - 1;
			for (String key : con) {
				query.append(key + "= ?");
				if (size > 0) {
					query.append(",");
					size--;
				}
			}
		}
		return new String(query);
	}
	// Column Builder
	public static String columnBuilder(LinkedList<String> col) {
		StringBuilder c = new StringBuilder(columnName(col.getFirst()));
		try {
			if (col.size() > 1) {
				for (int i = 1; i < col.size(); i++) {
					c.append("," + columnName(col.get(i)));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new String(c);
	}

	public static String columnName(String col)
	{
		char colu[]=col.toCharArray();
		StringBuilder column=new StringBuilder();
		for(int i=0;i<colu.length;i++)
		{
			if(colu[i]>='A' && colu[i]<='Z')
			{
				if(i==0)
				{
					column.append(Character.toLowerCase(colu[i]));
				}
				else
				{
					column.append("_"+Character.toLowerCase(colu[i]));
				}	
			}
			else
			{
				column.append(colu[i]);
			}
		}
		return new String(column);
	}
	// Column Question Mark Builder
	public static String questionMarksBuilder(int size) {
		StringBuilder v = new StringBuilder("?");
		if (size > 1) {
			for (int i = 1; i < size; i++) {
				v.append(",?");
			}
		}
		return new String(v);
	}

	public static boolean exe(MasterClass obj, String query) {
		boolean state = false;
		try (Connection con = JDBCconnect.connect(); PreparedStatement pst = con.prepareStatement(query);) {
			int index = 1;
			LinkedHashMap<String, Object> values = obj.getwhere();
			if (query.contains("INSERT") || query.contains("UPDATE")) {
				Method mth[] = obj.getClass().getDeclaredMethods();
				LinkedList<String> col = obj.getColumnNames();
				for (; index <= col.size(); index++) {
					String getMethodName = "get" + col.get(index - 1);
					Method method = obj.getClass().getDeclaredMethod(getMethodName);
					System.out.println(method.invoke(obj));
					if (method.getName().endsWith(getMethodName)) {
						pst.setObject(index, method.invoke(obj));
					}
				}
			}
			if (query.contains("WHERE") && values != null ) {
				
				for (String key : values.keySet()) {
					pst.setObject(index++, values.get(key));
				}
			}
			if (pst.executeUpdate() >= 0) {
				state = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return state;
	}
}
