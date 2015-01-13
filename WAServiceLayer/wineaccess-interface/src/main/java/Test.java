import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import com.google.gson.Gson;


public class Test {
	
	public static void main(String[] args) {
		
		
	/*	
		Gson gson = new Gson();
		
		gson.
		*/
		
		
		
		
		
		
		
		
		
		
		
		/*//"(^(.*\\d)(.*[a-z])+$)|(^(.*[a-z])(.*\\d)+$)" accepting only alphanumeric with atleast on number and character
		//System.out.println(AddressTypeMasterData.WAREHOUSE_ADDRESS.getAddressTypeMasterData());
		
		System.out.println(String.format("%010d",1));
		
		System.out.println(ValidationUtil.validateContent("a", "(^(.*\\d)(.*[a-z])+$)|(^(.*[a-z])(.*\\d)+$)"));
		
		
		String str = "Cannot add or update a child row: a foreign key constraint fails (`wineaccess`.`po_master`, CONSTRAINT `fk_performance_center_address_id` FOREIGN KEY (`PERFORMANCE_CENTER_ADDRESS_ID`) REFERENCES `performance_centre_location` (`ID`) ON DELETE NO ACTION ON UPD)";
		
		System.out.println(str.indexOf("FOREIGN KEY (`") + ("FOREIGN KEY (`").length());
		
		System.out.println(getColumnName(str));
		*/
		
		
		/*List list = new ArrayList();
		list.add("abc");
		list.add("12");		
		list.add("12");
		list.add(1,"rachit");
		
		
		Iterator it = list.iterator();
		
		while(it.hasNext())
		{
			System.out.println(it.next());
			//it.remove();
		}*/
		
		/*for(String l : list)
		{
			System.out.println(l);
		}*/
		
		
		
		/*List<Person> persons = new ArrayList<Person>();//retrive from database
		
		Person p1= new Person(1, "Rohit", "123");
		Person p2= new Person(2, "Rachit", "456");
		
		persons.add(p1);
		persons.add(p2);
		
		List<String> personNames = new ArrayList<String>();
		
		
		for(Person p : persons)
		{
			personNames.add(p.getName());
		}
		
		
		System.out.println(personNames);*/
		
		
		
		
		
		
		
		
		
	/*	System.out.println(list);
		
		System.out.println(list.get(1));
		
		//list.remove(0);
		
		
		System.out.println("After removing");
		System.out.println(list);
		//list.clear();
		
		
		
		Set<String> set = new HashSet<String>();
		set.add("abc");
		set.add("12");		
		set.add("12");
		set.add("rachit");
		System.out.println(set);*/
		
		
	/*	Map map = new HashMap<Integer, String>();
		
		map.put(1, "Rachit");
		
		map.put(2, "Rohit");
		
		System.out.println(map);
		
		System.out.println(map.get(1));*/
		
		
		
	
		
		
		
	//	set.
		
		
		
		
	}

	private static String getColumnName(String msg) {
		return msg.substring(msg.indexOf("`wineaccess`.`") + ("`wineaccess`.`").length(), msg.indexOf("`) REFERENCES"));
	}

	
}
