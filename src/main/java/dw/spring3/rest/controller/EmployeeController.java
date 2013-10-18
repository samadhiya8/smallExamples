package dw.spring3.rest.controller;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dw.spring3.rest.bean.Employee;
import dw.spring3.rest.bean.EmployeeList;
import dw.spring3.rest.ds.EmployeeDS;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {
	
private EmployeeDS employeeDS;
	
	public void setEmployeeDS(EmployeeDS ds) {
		this.employeeDS = ds;
	}
	
	private Jaxb2Marshaller jaxb2Mashaller;
	
	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
		this.jaxb2Mashaller = jaxb2Mashaller;
	}

	private static final String XML_VIEW_NAME = "employees";
	
	@RequestMapping(method=RequestMethod.GET, value="/employee/{id}")
	public @ResponseBody Employee getEmployee(@PathVariable String id) {
		System.out.println("The id is : "+id);
		Employee e = employeeDS.get(Long.parseLong(id));
                System.out.println("The return is  : "+e);
                return e;
		//return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
        

        @RequestMapping(method=RequestMethod.GET, value="/employee/{idFrom}/to/{idTill}")
	public @ResponseBody EmployeeList getEmployeeSet(@PathVariable("idFrom") String idFrom, @PathVariable("idTill") String idTill) {
            System.out.println("Getting List");    
            EmployeeList list = new EmployeeList(employeeDS.getList(Long.parseLong(idFrom),Long.parseLong(idTill)));
		return list;
                
		//return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
        
	@RequestMapping(method=RequestMethod.POST, value="/employee")
        //@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody Employee addEmployee(@RequestBody Employee body) {
		//Source source = new StreamSource(new StringReader(body));
		//Employee e = (Employee) jaxb2Mashaller.unmarshal(source);
		employeeDS.add(body);
                return body;
		//return new ModelAndView(XML_VIEW_NAME, "object", e);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/employee/{id}")
        //@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody Employee updateEmployee(@RequestBody Employee e) {
		employeeDS.update(e);
		return e;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/employee/{id}")
	public @ResponseBody EmployeeList removeEmployee(@PathVariable String id) {
		employeeDS.remove(Long.parseLong(id));
		List<Employee> employees = employeeDS.getAll();
		EmployeeList list = new EmployeeList(employees);
		return list;
	}
}
