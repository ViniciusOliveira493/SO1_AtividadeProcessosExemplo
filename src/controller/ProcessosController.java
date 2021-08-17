package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	public ProcessosController() {

	}
	
	public String getOs() {
		return System.getProperty("os.name");
	}
	
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (IOException e) {
			if(e.getMessage().contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}else {
				e.printStackTrace();
			}
		}
	}
	
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(reader);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();				
			}			
			buffer.close();
			reader.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void killProcess(String param) {
		String killPID = "TASKKILL /PID ";
		String killName = "TASKKILL /IM ";
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			pid = Integer.parseInt(param);
			buffer.append(killPID);
			buffer.append(pid);
		} catch (Exception e) {
			buffer.append(killName);
			buffer.append(param);
		}
		callProcess(buffer.toString());
	}
}
