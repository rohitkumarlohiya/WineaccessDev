package com.wineaccess.orchestration.data.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.globallogic.orch.ProcessDefinitionDocument;
import com.wineaccess.orchestration.workflow.model.ProcessDefinitionModel;

public class TestClass {
	
	public static void main(String [] args) throws Exception {
		
	/*	HttpURLConnection urlConnection = null;
		URL url = new URL("http://localhost:8181/kohls/command/v1/catalog/category?channel=mobile");

			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream urlInputStream = urlConnection.getInputStream();
			if (urlInputStream != null) {
				System.out.println(readStream(urlInputStream));
			}*/
		
		/*ProcessDefinitionDocument process = ProcessDefinitionDocument.Factory.newInstance();
		
		process.addNewProcessDefinition();
		
		process.getProcessDefinition().setName("ProcessDefinition");
		process.getProcessDefinition().setVersion("1.0");
		
		process.getProcessDefinition().addNewStartState();
		process.getProcessDefinition().getStartState().setName("Start Task");
		
		process.getProcessDefinition().getStartState().addNewTransition();
		process.getProcessDefinition().getStartState().getTransitionArray()[0].setName("TTA");
		process.getProcessDefinition().getStartState().getTransitionArray()[0].setTo("A");
		
		process.getProcessDefinition().addNewTask();
		
		process.getProcessDefinition().getTaskArray()[0].addNewName().set("A");
		process.getProcessDefinition().getTaskArray()[0].addNewTransition().setName("EDT");
		process.getProcessDefinition().getTaskArray()[0].getTransitionArray()[0].setTo("END-STATE");
		process.getProcessDefinition().getTaskArray()[0].addNewAction().setClass1("com.globallogic.TestTask");
		process.getProcessDefinition().getTaskArray()[0].addNewConfiguration();
		
		process.getProcessDefinition().getTaskArray()[0].getConfiguration().addNewProperty();
		process.getProcessDefinition().getTaskArray()[0].getConfiguration().getPropertyArray()[0].setName("P");
		process.getProcessDefinition().getTaskArray()[0].getConfiguration().getPropertyArray()[0].setValue("V");
		
		process.getProcessDefinition().getTaskArray()[0].getConfiguration().addNewOnError();
		process.getProcessDefinition().getTaskArray()[0].getConfiguration().getOnError().setAction("AAAA");
		
		process.getProcessDefinition().addNewEndState();
		process.getProcessDefinition().getEndState().setName("END-STATE")*/;
		
		ApplicationContext classpath = new ClassPathXmlApplicationContext(new String[]{"classpath*:/META-INF/spring/*.xml"});
		
		/*ProcessInstance process = new ProcessInstance("CategoryById", "v1");
		process.getProcessDefinition().getTaskDefinitions().get(process.getProcessDefinition().getStartTaskName());
		System.out.println(process);*/
		File f = new File("C:\\Data\\Projects\\WineAccess\\WineAccess\\wine-access-sample-xml\\Sample.xml");
		String processDefAsString = ProcessDefinitionDocument.Factory.parse(f).toString();
		ProcessDefinitionModel pDefinitionModel = new ProcessDefinitionModel("Test", "v1", processDefAsString);
		
		//process.getProcessDefinition().save(f);
	}
	
	private static String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while (reader != null && (line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return builder.toString();
	}

}


