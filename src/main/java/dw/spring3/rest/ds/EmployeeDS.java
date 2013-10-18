package dw.spring3.rest.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dw.spring3.rest.bean.Employee;

public class EmployeeDS {

	private static Map<Long, Employee> allEmployees;
	static {
		allEmployees = new HashMap<Long, Employee>();
		Employee e1 = new Employee(1L, "Saurabh Samadhiya", "samadhiya8@gmail.com");
		Employee e2 = new Employee(2L, "Wu Dong Fei", "wudongf@cn.ibm.com");
                Employee e3 = new Employee(3L, "Kamal Singh", "Ksingh@au.ibm.com");
                Employee e4 = new Employee(4L, "Narayan Joshi", "Narayan_Joshi@infosys.com");
                Employee e5 = new Employee(5L, "Irti Kampoor", "irti.x.kapoor@nab.com.au");
		allEmployees.put(e1.getId(), e1);
		allEmployees.put(e2.getId(), e2);allEmployees.put(e3.getId(), e3);allEmployees.put(e4.getId(), e4);
                allEmployees.put(e5.getId(), e5);
	}
	
	public void add(Employee e) {
		allEmployees.put(e.getId(), e);
	}

	public Employee get(long id) {
		System.out.println("The id is####### : "+id);
		return allEmployees.get(id);
	}
        
        public List<Employee> getList(long id1, long id2) {
		List<Employee> employees = new ArrayList<Employee>();
                while(id1<=id2){
                    employees.add(allEmployees.get(id1));
                    id1++;
                }
		return employees;
	}

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		for( Iterator<Employee> it = allEmployees.values().iterator(); it.hasNext(); ) {
			Employee e = it.next();
			employees.add(e);
		}
		return employees;
	}

	public void remove(long id) {
		allEmployees.remove(id);
	}

	public void update(Employee e) {
		allEmployees.put(e.getId(), e);
	}

}
