/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   Log
 * 
 * Summary:
 *   
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   
 * 
 * Ideas:
 *   
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.core.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class Log {
	
	/** formatter to output the timestamp */
	private static SimpleDateFormat sdf
		= new SimpleDateFormat("yyyy:MMM:dd:HH:mm:ss");
	
	/** Available log modes. If set, log output of the 
	 *  same level will be printed. */
	public enum Mode {
		TRACE, DEBUG, INFO, WARN, ERROR;
	}
	
	/* log levels available */
	private static boolean trace_active;
	private static boolean debug_active;
	private static boolean info_active;
	private static boolean warn_active;
	private static boolean error_active;
	
	/** Set given modes to be visible. */
	public static void SetMode(Mode... modes) {
		for (Mode mode : modes) {
			if(mode == Mode.TRACE)
				trace_active = true;
			else if(mode == Mode.DEBUG)
				debug_active = true;
			else if(mode == Mode.INFO)
				info_active = true;
			else if(mode == Mode.WARN)
				warn_active = true;
			else if(mode == Mode.ERROR)
				error_active = true;
		}
	}

	/** Log output TRACE level */
	public static void TRACE(String output) {
		if(trace_active)
			System.out.println(TIMESTAMP() + ", TRACE:" + output);
	}
	
	/** Log output DEBUG level */
	public static void DEBUG(String output) {
		if(debug_active)
			System.out.println(TIMESTAMP() + ", DEBUG:" + output);
	}

	/** Log output INFO level */
	public static void INFO(String output) {
		if(info_active)
			System.out.println(TIMESTAMP() + ", INFO:" + output);
	}

	/** Log output WARN level */
	public static void WARN(String output) {
		if(warn_active)
			System.out.println(TIMESTAMP() + ", WARN:" + output);
	}
	
	/** Log output ERROR level */
	public static void ERROR(String output) {
		if(error_active)
			System.out.println(TIMESTAMP() + ", ERROR:" + output);
	}
	
	/* ################################# */
	
	private static String TIMESTAMP() {
		Date resultdate = new Date(System.currentTimeMillis());
		return sdf.format(resultdate);
	}

}
