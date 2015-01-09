package br.com.stefani.ivan.amiltest.main;

import java.io.IOException;
import br.com.stefani.ivan.amiltest.dao.LogLoader;
import br.com.stefani.ivan.amiltest.util.ReportPrinter;

public class Main {
	public static void main(String[] args) {

		LogLoader instance = new LogLoader();
		try {

			ReportPrinter rp = new ReportPrinter(
					instance.loadLogFile("activity.log"));
			rp.printReport();

		} catch (IOException | CloneNotSupportedException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
