package view;

import controller.ProcessosController;

public class Main {
	public static void main(String[] args) {
		ProcessosController pc = new ProcessosController();
		
		//System.out.println(pc.getOs());
		
		//pc.callProcess("C:\\Windows\\regedi.exe");
		
		pc.readProcess("TASKLIST /FO TABLE");
		
		pc.killProcess("cmd.exe");
	}
}
