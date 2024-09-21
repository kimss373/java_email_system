package kr.ac.kopo.main;

import kr.ac.kopo.ui.MainUI;

public class EmailMain {

	public static void main(String[] args) {
		
		MainUI ui = new MainUI();
		try {
			ui.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
