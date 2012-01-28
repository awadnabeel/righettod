package com.drighetto.webdav;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Sample runner
 * @author Dominique Righetto<br>
 * 02-mai-07
 *
 */
public class RunSample {

	/**
	 * Entry point
	 * @param args
	 */
	public static void main(String[] args) {
		WebDavProcessing wdp = new WebDavProcessing();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:S");		
		int x = 1;
		
		for(int i = 0 ; i < x ; i++){
			System.out.printf("\n\n---ITERATION : %s\n",i);
			System.out.printf("Before Draft mail : %s\n",dateFormat.format(new Date()));
			wdp.addDraftMail();			
			System.out.printf("\nBefore Task       : %s\n",dateFormat.format(new Date()));
			wdp.addTask();
		}
		wdp = null;
	}

}
