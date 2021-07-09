package appServlets;


import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import random.entity_bean;
import random.entity_dao;

//import DB_Crud.entity_bean;
//import DB_Crud.entity_dao;

@RestController
@RequestMapping("/person")
public class MainServlet {
	
	
    entity_bean entity = new entity_bean();
    JSONObject json = new JSONObject();
    entity_dao dao_object = new entity_dao();
    
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> doGet(){
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: get ").append(request.getContextPath());
		 
    	entity_dao obj = new entity_dao();
        List<entity_bean> listall = null;
		try {
			listall = obj.listAll();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index= 0;
		while(listall.size()>index) {
			
			JSONArray array = new JSONArray();	
		    array.add(listall.get(index).getFirstName());
		    array.add(listall.get(index).getLastName());
		    
			json.put(listall.get(index).getId(), array);
			index++;
		}
	
		return new ResponseEntity<String>(json.toString(),HttpStatus.OK);
		
	}
	
	
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> doPost(@RequestParam("Id") String Id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: post ").append(request.getContextPath());
		
		
        entity.setId(Integer.parseInt(Id));
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        boolean res = false;
        try {
			res = dao_object.insert(entity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res){
			return new ResponseEntity<Void>(HttpStatus.OK);
		
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
	}
    
    
    @RequestMapping(name = "{Id}/{firstName}/{lastName}", method = RequestMethod.PUT)
	public ResponseEntity<Void> doPut(@RequestParam(name = "Id") int Id, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: put").append(request.getContextPath());
		 
    //@RequestMapping(value = "{Id}", method = RequestMethod.PUT)
	//public ResponseEntity<Void> doPut(@PathVariable("Id") int Id, @RequestBody entity_bean json) {

        entity.setId(Id);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
      //  System.out.println(json);
		
        System.out.println(entity);
        
        boolean res = false;
        try {
			res = dao_object.update(entity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res){
			return new ResponseEntity<Void>(HttpStatus.OK);
		
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
    
    @RequestMapping(name = "{Id}", method = RequestMethod.DELETE)
	protected ResponseEntity<Void> doDelete(@RequestParam(name = "Id") int Id) {
		// TODO Auto-generated method stub
		//resp.getWriter().append("Served at: delete").append(req.getContextPath());
		
    	
        
        boolean res = false;
        
        try {
			res = dao_object.delete(Id);
			System.out.println(" deletec = "+Id+" =  "+res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		if(res){
			return new ResponseEntity<Void>(HttpStatus.OK);
		
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
        
    
}