package MiniCodes;

import java.io.IOException;

import java.text.ParseException;

public class Start {
	public static void main(String[]args) throws IOException, InterruptedException, ParseException {
		Process p = new Process();
		p.setDriver();
		p.city();
		p.date();
		p.businessClass();
		p.search();
		p.validation();
		p.print();
		p.screenShots();
		p.driverClose();
	}

}
