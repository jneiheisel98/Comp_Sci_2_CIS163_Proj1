package proj1;

import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.lang.Integer;
import java.text.DecimalFormat;

/*****************************************************************
 ********   A class that has all of the facets of a stopwatch in
 * the sense that there are many methods to emulate function.
 * For instance, there are methods to set the time and various
 * time pieces on the stopwatch while also being able to set
 * the time. The methods also function like a stopwatch in that
 * the methods stop if the stopwatch is stopped. The stopwatch
 * can also be set up in many ways and multiple stopwatches
 * can be added together and subtracted. This comes to the
 * standard time prediction, incrementation, and decrementation
 * methods.
 *
 * @author Devin Elenbaas
 * @author Jacob Neiheisel
 * @version 2/11/2021
 */

public class StopWatch  {

	/**
	 * This holds minutes for the stopwatch
	 */
	private int minutes;


	/**
	 * This holds seconds for the stopwatch
	 */
	private int seconds;


	/**
	 * This holds milliseconds for the stopwatch
	 */
	private int milliseconds;


	/**
	 * This is a boolean for the variable suspend
	 */
	private static boolean suspend = false;


	/******************************************************************
	 *  The default constructor for StopWatch.java that initializes
	 *  the stop watch
	 */

	public StopWatch() {
		minutes = 0;
		seconds = 0;
		milliseconds = 0;
	}


	/******************************************************************
	 *  A constructor that accepts a string as a parameter with the
	 *  following format: "1:21:300"� where 1 indicates minutes, 21
	 *  indicates seconds,  and 300 indicates milliseconds.  OR
	 *  the format "15:200"� where the 15 indicates seconds, and
	 *  200 indicates milliseconds, OR the format “300” where
	 *  300 indicates milliseconds
	 *
	 * @param startTime is the input string that represents
	 * the starting time
	 * @throws IllegalArgumentException when the input string
	 * does not match the proper format (see description above)
	 */

	public StopWatch(String startTime) {
		if (startTime == null)
			throw new IllegalArgumentException();
		else if(startTime.length() == 0){
			minutes = 0;
			seconds = 0;
			milliseconds = 0;
		}

		else if(startTime.length() > 0) {
			String[] s = startTime.split(":");
			if (s.length == 3) {
				minutes = Integer.parseInt(s[0]);
				seconds = Integer.parseInt(s[1]);
				milliseconds = Integer.parseInt(s[2]);
			}
			else if (s.length == 2) {
				seconds = Integer.parseInt(s[0]);
				milliseconds = Integer.parseInt(s[1]);
				minutes = 0;
			}

			else if (s.length == 1) {
				milliseconds = Integer.parseInt(s[0]);
				seconds = 0;
				minutes = 0;
			}
			else if (s.length > 3) {
				throw new IllegalArgumentException();
			}
			if (seconds < 0 || seconds > 59 || milliseconds < 0
					|| milliseconds > 999 || minutes < 0) {
				throw new IllegalArgumentException();
			}
		}
	}


	/******************************************************************
	 *  A constructor for StopWatch that accepts minutes, seconds, and
	 *  milliseconds as parameters.
	 *
	 * @param minutes integer
	 * @param seconds integer
	 * @param milliseconds integer
	 * @throws IllegalArgumentException when minutes are less than 0
	 * @throws IllegalArgumentException when seconds are less than 0
	 * 									or greater than 59
	 * @throws IllegalArgumentException when milliseconds are less
	 * 									than 0 or greater than 999
	 */
	public StopWatch(int minutes, int seconds, int milliseconds) {
		if (minutes < 0)
			throw new IllegalArgumentException("constuctor with"+
					" 3 params");

		if (seconds < 0|| seconds > 59)
			throw new IllegalArgumentException("seconds"+
					" too low");

		if (milliseconds < 0 || milliseconds > 999)
			throw new IllegalArgumentException();

		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}


	/******************************************************************
	 *  A constructor that takes a previous stopwatch and initializes
	 *  it as the new one as parameter
	 *
	 * @param stopWatch object
	 * @throws IllegalArgumentException if the newly initialized
	 * stopwatch is null
	 */
	public StopWatch(StopWatch stopWatch) {
		if (stopWatch == null)
			throw new IllegalArgumentException();
		else if(stopWatch != null){
			this.minutes = stopWatch.minutes;
			this.seconds = stopWatch.seconds;
			this.milliseconds = stopWatch.milliseconds;
		}
	}


	/******************************************************************
	 * A constructor that takes seconds and milliseconds as paramater
	 *
	 * @param seconds integer
	 * @param milliseconds integer
	 * @throws IllegalArgumentException if seconds are less than
	 * 									0 or greater than 59
	 * @throws IllegalArgumentException if milliseconds are less than
	 * 									0 or greater than 999
	 */
	public StopWatch(int seconds, int milliseconds) {
		if (seconds < 0 || seconds > 59)
			throw new IllegalArgumentException("seconds" +
					" too low");
		if (milliseconds < 0 || milliseconds > 999)
			throw new IllegalArgumentException();
		else {
			this.minutes = 0;
			this.seconds = seconds;
			this.milliseconds = milliseconds;
		}
	}


	/******************************************************************
	 *  A constructor that accepts milliseconds as parameter
	 *
	 * @param milliseconds integer
	 * @throws IllegalArgumentException if milliseconds are less than
	 * 									0 or greater than 999
	 */
	public StopWatch(int milliseconds) {
		if (milliseconds < 0 || milliseconds > 999)
			throw new IllegalArgumentException();
		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = milliseconds;
	}


	/******************************************************************
	 *  A method that tells if an outside stopwatch object is equal
	 *  to a created stopwatch object and sets them equal to each
	 *  other if not
	 *
	 * @param stopWatch1 an outside stopwatch object
	 * @param stopWatch2 an outside stopwatch object
	 * @return boolean of true or false depending on if the
	 * 		   stopwatches are equal
	 * @throws IllegalArgumentException if stopwatch 1 or stopwatch 2
	 * 									are null value
	 */
	public static boolean equals(StopWatch stopWatch1,
								 StopWatch stopWatch2) {
		if(stopWatch1 == null || stopWatch2 == null){
			throw new IllegalArgumentException();
		}
		else {
			return stopWatch1.getMilliseconds() ==
					stopWatch2.getMilliseconds() &&
					stopWatch1.getSeconds() == stopWatch2.getSeconds()
					&& stopWatch1.getMinutes() ==
					stopWatch2.getMinutes();
		}
	}


	/******************************************************************
	 *  A method that tells if an outside stopwatch object is equal to
	 *  one that has been created
	 *
	 * @param object outside object that is compared to the created
	 *               one
	 * @return boolean of true or false depending on if the objects
	 * 		   are equal
	 * @throws IllegalArgumentException if the object is
	 * 									null or if the object is not
	 * 									an instance of stopwatch
	 */
	public boolean equals(Object object) {
		boolean verdict = false;
		if(object == null || !(object instanceof StopWatch)){
			throw new IllegalArgumentException();
		}
		else if(object instanceof StopWatch) {
			StopWatch comparer = (StopWatch) object;
			verdict =  equals(comparer, this);
		}
		return verdict;
	}


	/******************************************************************
	 * A method that compares a stopwatch to another stopwatch
	 *
	 * @param other an outside stopwatch object
	 * @return the comparison of the stopwatch objects
	 * @throws IllegalArgumentException if the object to compare is
	 * 									null
	 */
	public int compareTo(StopWatch other) {
		int comparison = 0;
		if (other == null)
			throw new IllegalArgumentException();
		if(this.milliseconds == other.getMilliseconds() &&
				this.seconds == other.getSeconds() &&
				this.minutes == other.getMinutes()){
			 comparison = 0;
		}
		else if((this.minutes < other.getMinutes()) ||
				(this.minutes == other.getMinutes() &&
						this.seconds< other.getSeconds())
				|| (this.minutes == other.getMinutes()
				&& this.seconds == other.getSeconds()
				&& this.milliseconds < other.getMilliseconds())){
			comparison =  -1;
		}
		else if((this.minutes > other.getMinutes()) ||
				(this.minutes == other.getMinutes() &&
						this.seconds > other.getSeconds()) ||
				(this.minutes == other.getMinutes() &&
						this.seconds == other.getSeconds() &&
						this.milliseconds > other.getMilliseconds())){
			 comparison = 1;
		}
		return comparison;
	}


	/******************************************************************
	 * A method that converts the time of a stopwatch into
	 * milliseconds
	 * @param stopWatch object to convert
	 * @return a stopwatch entirely in milliseconds
	 * @throws IllegalArgumentException if the stopWatch is null
	 */
	private static int convertToMilli (StopWatch stopWatch) {
		if (stopWatch == null)
			//this line below is not covered in testing
			//it is unreachable or hard to reach
			throw new IllegalArgumentException();
		else
			return stopWatch.getMinutes() * 60 * 1000 +
					stopWatch.getSeconds() * 1000 +
					stopWatch.getMilliseconds();
	}


	/******************************************************************
	 * A method that converts milliseconds into a stopwatch
	 * @param tempMilliseconds to be converted
	 * @return a stopwatch that was previously all milliseconds
	 * @throws IllegalArgumentException if the value of the paramater
	 * 									milliseconds is less than 0
	 */
	private void convertToStopWatch (int tempMilliseconds) {
		if(tempMilliseconds < 0){
			throw new IllegalArgumentException();
		}

		else {
			minutes = tempMilliseconds / 60000;
			tempMilliseconds %= 60000;

			seconds = tempMilliseconds / 1000;
			tempMilliseconds %= 1000;
			milliseconds = tempMilliseconds;
		}
	}


	/******************************************************************
	 * A method that adds time to the stopwatch by adding milliseconds
	 *
	 * @param milliseconds to be added
	 * @throws IllegalArgumentException if milliseconds are less than
	 * 									0
	 */
	public void add(int milliseconds) {
		if (!isSuspended() && milliseconds > 0) {
				for (int count = 0; count < milliseconds; count++) {
					inc();
				}
		}
		else if(milliseconds < 0){
			throw new IllegalArgumentException();
		}
	}


	/******************************************************************
	 * A method that subtracts time from the stopwatch by milliseconds
	 *
	 * @param milliseconds to be added
	 * @throws IllegalArgumentException if milliseconds are less than
	 * 									0
	 */
	public void sub(int milliseconds) {
		if (!isSuspended() && milliseconds>0) {
			for(int count = 0; count < milliseconds; count++){
				dec();
			}
		}
		else if(milliseconds < 0) {
			throw new IllegalArgumentException();
		}
	}


	/******************************************************************
	 * A method that adds two stopwatches together
	 *
	 * @param stopWatch object to be added
	 * @throws IllegalArgumentException if the stopWatch object
	 * 									is null
	 */
	public void add(StopWatch stopWatch) {
		if (!isSuspended() && stopWatch != null) {
			int time1 = convertToMilli(this);
			int time2 = convertToMilli(stopWatch);
			int totalTime = time1 + time2;
			convertToStopWatch(totalTime);

		}
		else if(stopWatch == null){
			throw new IllegalArgumentException();
		}
	}


	/******************************************************************
	 * A method that subtracts a stopwatch from another
	 *
	 * @param stopWatch object to be subtracted
	 * @throws IllegalArgumentException if the stopWatch object
	 * 									is null
	 */
	public void sub(StopWatch stopWatch) {
		if (!isSuspended() && stopWatch != null) {
			int time1 = convertToMilli(this);
			int time2 = convertToMilli(stopWatch);
			int totalTime = time1 - time2;
			convertToStopWatch(totalTime);

		}
		else if(stopWatch == null){
			throw new IllegalArgumentException();
		}
	}


	/******************************************************************
	 * A method that increments time on the stopwatch by a millisecond
	 */
	public void inc() {
		if (!isSuspended()) {
			milliseconds++;
			if (milliseconds > 999) {
				seconds++;
				milliseconds = 0;
			}
			if (seconds > 59) {
				minutes++;
				seconds = 0;
			}
		}
	}


	/******************************************************************
	 * A method that decrements time on the stopwatch by a millisecond
	 *
	 * @throws IllegalStateException if milliseconds are less than 0
	 */
	public void dec() {
		if (!isSuspended()) {
			milliseconds--;
			if (milliseconds < 0 && seconds > 0) {
				seconds--;
				milliseconds += 1000;
			} else if (milliseconds < 0 && seconds == 0
					&& minutes > 0) {
				minutes--;
				seconds += 59;
				milliseconds += 1000;
			} else if (milliseconds < 0) {
				throw new IllegalStateException();
			}
		}
	}


	/******************************************************************
	 * A method that turns a stopwatch into a string representation
	 */
	public String toString() {
		DecimalFormat secondFormat =
				new DecimalFormat("00");
		DecimalFormat millisecondFormat =
				new DecimalFormat("000");
		String minutes = String.valueOf(this.minutes);
		String seconds = secondFormat.format(this.seconds);
		String milliseconds =
				millisecondFormat.format(this.milliseconds);
		return minutes + ":" + seconds + ":" + milliseconds;
	}


	/******************************************************************
	 * A method for saving a stopwatch object
	 *
	 * @param filename name of the stopwatch being saved
	 * @throws IllegalArgumentException if the filename is null
	 * @throws IllegalArgumentException if an IOException is caught
	 */
	public void save(String filename) {
		if (filename == null)
			throw new IllegalArgumentException();

		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter
					(new FileWriter(filename)));

			out.println(this.minutes + " " +
					this.seconds + " " + this.milliseconds);

			out.close();

		} catch (IOException e) {

			throw new IllegalArgumentException();

		}
	}


	/******************************************************************
	 * A method that loads a previously saved stopwatch object
	 * or a new stopwatch object file
	 * @param filename stopwatch to be loaded
	 * @throws IllegalArgumentException if the filename is null
	 * @throws IllegalArgumentException if a FileNotFoundException
	 * 									is caught
	 */
	public void load(String filename)  {
		if (filename == null)
			throw new IllegalArgumentException();

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename));

			//Scanner lineReader;
			minutes = scanner.nextInt();
			seconds = scanner.nextInt();
			milliseconds = scanner.nextInt();

			StopWatch tester = new StopWatch(minutes, seconds, milliseconds);

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}


	/******************************************************************
	 * A method that sets the suspend to true or false using the
	 * boolean suspend
	 *
	 * @param suspend boolean for setting true or false
	 */
	public static void setSuspend(boolean suspend) {
		if(suspend == false){
			StopWatch.suspend = false;
		}
		else if(suspend == true){
			StopWatch.suspend = true;
		}
	}


	/******************************************************************
	 * A method that returns the boolean isSuspended
	 *
	 * @return true or false depending on the suspend method
	 */
	public static boolean isSuspended() {
		return suspend;
	}


	/******************************************************************
	 * A method that gets minutes from a created stopwatch
	 *
	 * @return minutes from the stopwatch
	 */
	public int getMinutes() {
		return minutes;
	}


	/******************************************************************
	 * A method that sets the minutes integer of the stopwatch
	 *
	 * @param minutes to be set to
	 * @throws IllegalArgumentException if minutes are less than 0
	 */
	public void setMinutes(int minutes) {
		if(minutes < 0){
			throw new IllegalArgumentException();
		}
		else if(!isSuspended()) {
			this.minutes = minutes;
		}
	}


	/******************************************************************
	 * A method that gets seconds from a created stopwatch
	 *
	 * @return the seconds of the stopwatch
	 */
	public int getSeconds() {
		return seconds;
	}


	/******************************************************************
	 * A method that sets the seconds integer of the stopwatch
	 *
	 * @param seconds to be set
	 * @throws IllegalArgumentException if seconds are less than 0
	 * 									or greater than 59
	 */
	public void setSeconds(int seconds) {
		if(seconds < 0 || seconds > 59){
			throw new IllegalArgumentException();
		}
		else if(!isSuspended()){
			this.seconds = seconds;
		}
	}


	/******************************************************************
	 * A method that gets milliseconds from a created stopwatch
	 *
	 * @return the milliseconds of the stopwatch
	 */
	public int getMilliseconds() {
		return milliseconds;
	}


	/******************************************************************
	 * A method that sets the milliseconds integer of the stopwatch
	 *
	 * @param milliseconds to be set to
	 * @throws IllegalArgumentException if milliseconds are less than
	 * 									0 or greater than 999
	 */
	public void setMilliseconds(int milliseconds) {
		if(milliseconds < 0 || milliseconds > 999){
			throw new IllegalArgumentException();
		}
		else if(!isSuspended()){
			this.milliseconds = milliseconds;
		}
	}

}