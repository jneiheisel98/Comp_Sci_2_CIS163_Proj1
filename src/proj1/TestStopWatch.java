package proj1;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;


public class TestStopWatch {

	/******************************************************************
	 * Tests of the default constructor
	 */

	//This test, testDefaultConstructor calls the default
	//constructor and makes sure there is 0 mins, secs, and
	//milliseconds.
		@Test
		public void testDefaultConstructor() {
			StopWatch s = new StopWatch();
			assertTrue(s.getMinutes() == 0);
			assertTrue(s.getSeconds() == 0);
			assertTrue(s.getMilliseconds() == 0);
		}


	/******************************************************************
	 * Tests of the default constructor
	 */


		@Test(expected = IllegalArgumentException.class)
		public void oneParemeterErrorStopwatchMilliseconds() {
			StopWatch s5 = new StopWatch(-300);
		}


		@Test
		public void oneParemeterStopwatchMilliseconds() {
			StopWatch s5 = new StopWatch(300);
			assertEquals(s5.toString(), "0:00:300");
		}


		@Test(expected = IllegalArgumentException.class)
		public void testNegSingleInput2() {
			new StopWatch(-2);

		}


	/******************************************************************
	 * Tests of the two parameter constructor
	 */

		@Test
		public void twoParemeterStopwatch() {
			StopWatch s2 = new StopWatch(30, 300);
			StopWatch s3 = new StopWatch(45, 305);
			assertEquals(s2.toString(), "0:30:300");
			assertEquals(s3.toString(), "0:45:305");
		}


		@Test(expected = IllegalArgumentException.class)
		public void twoParemeterErrorStopwatch() {
			StopWatch s2 = new StopWatch(-30, 300);
		}


		@Test(expected = IllegalArgumentException.class)
		public void twoParemeterErrorStopwatchMilliseconds() {
			StopWatch s2 = new StopWatch(30, -300);
		}


	/******************************************************************
	 * Tests of the three parameter constructor
	 */

		@Test(expected = IllegalArgumentException.class)
		public void testConstructor3ParametersX3() {
			StopWatch s2 = new StopWatch(2, 3, -4);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testConstructor3ParametersAllX() {
			StopWatch s2 = new StopWatch(-2, -3, -4);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testConstructor3ParametersOther() {
			StopWatch s2 = new StopWatch(-2, 3, -4);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testConstructor3Parameters2Neg() {
			StopWatch s2 = new StopWatch(2, -3, -4);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testConstructor3ParametersSecsNeg() {
			StopWatch s2 = new StopWatch(2, -3, 4);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testConstructor3ParametersMinsNeg() {
			StopWatch s2 = new StopWatch(-2, 3, 4);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testConstructor3e2Parameters() {
			StopWatch s = new StopWatch(12, 67, 14);
		}


		@Test
		public void testConstructor3ParametersGood() {
			StopWatch s = new StopWatch(12, 59, 14);
		}


		@Test
		public void testConstructor3Param() {
			StopWatch s = new StopWatch(5, 10, 300);
			assertEquals(s.toString(), "5:10:300");
		}


	/******************************************************************
	 * Tests of the string constructor
	 */

		@Test
		public void testConstructor() {
			StopWatch s;
			s = new StopWatch("20:10:8");
			assertEquals(s.toString(), "20:10:008");

			s = new StopWatch("20:8");
			assertEquals(s.toString(), "0:20:008");

			s = new StopWatch("8");
			assertEquals(s.toString(), "0:00:008");

		}


		@Test(expected = IllegalArgumentException.class)
		public void testNegConstructors() {
			StopWatch s;
			s = new StopWatch("-20:10:8");
		}


		@Test(expected = IllegalArgumentException.class)
		public void testNegDouble1Input() {
			new StopWatch("-59:-23");

		}


		@Test(expected = IllegalArgumentException.class)
		public void testNegDouble2aInput() {
			new StopWatch("2:-2");

		}


		@Test(expected = IllegalArgumentException.class)
		public void testLargeMillisecondInput() {
			new StopWatch("2:1001");

		}


		@Test(expected = IllegalArgumentException.class)
		public void testLargeSecondInput() {
			new StopWatch("82:1001");

		}


		@Test()
		public void testNegDoubleInput() {
			StopWatch s = new StopWatch("2:2");
			assertEquals(s.toString(), "0:02:002");
		}


		@Test(expected = IllegalArgumentException.class)
		public void testLetterDoubleInput() {
			StopWatch s = new StopWatch("2:2a");
		}


		@Test(expected = IllegalArgumentException.class)
		public void testLetterSingleInput() {
			StopWatch s = new StopWatch("2a");
		}


		@Test(expected = IllegalArgumentException.class)
		public void testLetterSingleInput2() {
			StopWatch s = new StopWatch("60:300:2a");
		}


		@Test(expected = IllegalArgumentException.class)
		public void testAlphaInput() {
			new StopWatch("a");
		}


		@Test()
		public void emptyInput() {
			StopWatch s1 = new StopWatch("");
			assertEquals(s1.getSeconds(), 0);
			assertEquals(s1.getMilliseconds(), 0);
			assertEquals(s1.getMinutes(), 0);
		}


		@Test(expected = IllegalArgumentException.class)
		public void nullInput() {
			String s = null;
			StopWatch s1 = new StopWatch(s);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testColonWrongPlace() {
			String s = ":30:050";
			StopWatch s1 = new StopWatch(s);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testTooManyFields() {
			String s = "20:10:30:050";
			StopWatch s1 = new StopWatch(s);
		}


	/******************************************************************
	 * Tests of the add method with stopwatch and milliseconds
	 */

		@Test
		public void testAddMethod() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			s1.add(2000);
			assertEquals(s1.toString(), "6:01:300");

			StopWatch.setSuspend(true);
			s1.add(2000);
			assertEquals(s1.toString(), "6:01:300");
			StopWatch.setSuspend(false);
			s1.add(2000);
			assertEquals(s1.toString(), "6:03:300");
			s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(2, 2, 300);
			s1.add(s2);
			assertEquals(s1.toString(), "8:01:600");

			for (int i = 0; i < 15000; i++)
				s1.inc();
			assertEquals(s1.toString(), "8:16:600");
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionError() {
			StopWatch s1 = null;
			StopWatch s2 = new StopWatch(500);
			s2.add(s1);
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNull() {
			StopWatch s2 = new StopWatch(500);
			s2.add(null);
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNeg() {
			StopWatch s2 = new StopWatch(500);
			s2.add(-500);
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNegativeStop() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(-500));
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNeg2Params() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(-50,-500));
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNeg3Params() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(-5,-50,-500));
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNeg3Params2Neg() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(-5,-50,500));

		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNeg3Params2NegPart2() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(-5,50,-500));
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchAdditionNeg3Params2NegPart3() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(5,-50,-500));
		}


		@Test
		public void stopwatchAdditionFromSkeletonWatch() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(500));
			assertEquals(s2.toString(), "0:01:000");
		}


		@Test
		public void stopwatchAdditionFromSkeletonWatch2Params() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(1,501));
			assertEquals(s2.toString(), "0:02:001");
		}


		@Test
		public void stopwatchAdditionFromSkeletonWatch3Params() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(2,1,501));
			assertEquals(s2.toString(), "2:02:001");
		}


		@Test
		public void stopwatchAdditionFromSkeletonWatch3ParamsSuspended() {
			StopWatch s2 = new StopWatch(500);
			s2.add(new StopWatch(2,1,501));
			assertEquals(s2.toString(), "2:02:001");
		}


		@Test
		public void stopwatchAdditionNotSuspended() {
			StopWatch s2 = new StopWatch(500);
			s2.add(500);
		}


		@Test
		public void testAddWithoutErrorSuspended() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = new StopWatch(1, 0, 0);
			StopWatch s3 = new StopWatch(3, 0, 0);
			s1.add(s2);
			assertTrue(s1.equals(s3));
		}


		@Test
		public void stopwatchAdditionSuspended() {
			StopWatch s2 = new StopWatch(500);
			StopWatch s3 = new StopWatch(500);
			StopWatch.setSuspend(true);
			s2.add(500);
			StopWatch.setSuspend(false);
			assertTrue(s2.equals(s3));
		}


		@Test
		public void testAddWithErrorSuspended() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = new StopWatch(1, 0, 0);
			StopWatch s3 = new StopWatch(2, 0, 0);
			StopWatch.setSuspend(true);
			s1.add(s2);
			StopWatch.setSuspend(false);
			assertTrue(s1.equals(s3));
		}


		@Test (expected = IllegalArgumentException.class)
		public void testAddWithNullWatch() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = null;
			s1.add(s2);
		}


		@Test (expected = IllegalArgumentException.class)
		public void testAddWithNull() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = null;
			s1.add(null);
		}


		@Test (expected = NullPointerException.class)
		public void testAddWithNullOrigWatch() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = null;
			s2.add(s1);
		}


	/******************************************************************
	 * Tests of the equals method
	 */

		@Test
		public void testEqual() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(6, 01, 200);
			StopWatch s3 = new StopWatch(5, 50, 200);
			StopWatch s4 = new StopWatch(6, 0, 200);
			StopWatch s5 = new StopWatch(5, 59, 200);
			StopWatch s6 = new StopWatch(6, 01, 201);
			StopWatch s7 = new StopWatch(6, 01, 200);
			StopWatch s8 = new StopWatch(5, 59, 300);
			StopWatch s9;
			assertFalse(s1.equals(s2));
			assertTrue(s1.equals(s8));
			s9 = new StopWatch(s4);
			assertTrue(s9.equals(s9, s4));

		}


		@Test
		public void equalsTest() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(5, 59, 300);
			assertTrue(StopWatch.equals(s1, s2));
		}


		@Test
		public void stopwatchFromSpotConstructor() {
			assertTrue(StopWatch.equals(new StopWatch(0,300),
					new StopWatch(300)));
		}


		@Test (expected = IllegalArgumentException.class)
		public void stopwatchNegativeFromSpotConstructor() {
			assertTrue(StopWatch.equals(new StopWatch(0,-300),
					new StopWatch(-300)));
		}


		@Test (expected = IllegalArgumentException.class)
		public void stopwatchNegativeOtherParameterFromSpotConstructor() {
			assertTrue(StopWatch.equals(new StopWatch(-30,300),
					new StopWatch(-30,300)));
		}


		@Test (expected = IllegalArgumentException.class)
		public void stopwatchNegativeMinParameterFromSpotConstructor() {
			assertTrue(StopWatch.equals(new StopWatch(-30,30,300),
					new StopWatch(-30,30,300)));
		}


		@Test (expected = IllegalArgumentException.class)
		public void stopwatchNullDoubleEquals() {
			assertTrue(StopWatch.equals(null, null));
		}



		@Test(expected = IllegalArgumentException.class)
		public void stopwatchEqualsDiffTypes() {
			String s1 = new String("Jacob");
			StopWatch s2 = new StopWatch(500);
			s2.equals(s1);
		}


		@Test(expected = IllegalArgumentException.class)

		public void stopwatchEqualsNewException() {
			StopWatch s1 = null;
			StopWatch s2 = new StopWatch(500);
			s2.equals(s1);
		}


		@Test
		public void equalsTestTry() {
			StopWatch s5 = new StopWatch(300);
			StopWatch s6 = new StopWatch(0, 300);
			assertTrue(StopWatch.equals(s5, s6));
		}


		@Test
		public void equalsTestExact() {
			StopWatch s5 = new StopWatch(300);
			StopWatch s6 = new StopWatch(0, 300);
			assertTrue(s5.equals(s6));
		}


	/******************************************************************
	 * Tests of the compareTo method
	 */

		@Test
		public void testCompareTo() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(6, 01, 200);
			StopWatch s3 = new StopWatch(5, 50, 200);
			StopWatch s4 = new StopWatch(5, 59, 300);

			assertTrue(s2.compareTo(s1) > 0);
			assertTrue(s3.compareTo(s1) < 0);
			assertTrue(s1.compareTo(s4) == 0);

		}


		@Test (expected = IllegalArgumentException.class)
		public void testCompareToNull() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = null;
			s1.compareTo(s2);
		}


		@Test
		public void difficultCompares(){

			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(6, 01, 200);
			StopWatch s3 = new StopWatch(5, 50, 200);
			StopWatch s4 = new StopWatch(6, 0, 200);
			StopWatch s5 = new StopWatch(5, 59, 200);
			StopWatch s6 = new StopWatch(6, 01, 201);
			StopWatch s7 = new StopWatch(6, 01, 200);
			StopWatch s8 = new StopWatch(5, 59, 300);

			assertTrue(s2.compareTo(s1) > 0);
			assertTrue(s1.compareTo(s2) < 0);
			assertTrue(s3.compareTo(s1) < 0);
			assertTrue(s1.compareTo(s3) > 0);
			assertTrue(s1.compareTo(s4) < 0);
			assertTrue(s1.compareTo(s5) > 0);
			assertTrue(s5.compareTo(s1) < 0);
			assertTrue(s2.compareTo(s7) == 0);
			assertTrue(s1.compareTo(s8) == 0);
			assertTrue(s2.compareTo(s6) < 0);
			assertTrue(s6.compareTo(s2) > 0);


		}


	/******************************************************************
	 * Tests of the load and save methods
	 */

		@Test
		public void testLoadSave() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(5, 59, 300);

			s1.save("file1");
			s1 = new StopWatch();  // resets to zero

			s1.load("file1");
			assertTrue(s1.equals(s2));

		}


		@Test(expected = IllegalArgumentException.class)
		public void testLoadSaveFailSave() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(5, 59, 300);
			String name = null;
			s1.save(name);
			s1 = new StopWatch();  // resets to zero

			s1.load("file1");
			assertTrue(s1.equals(s2));

		}


		@Test(expected = IllegalArgumentException.class)
		public void testLoadSaveFailLoad() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(5, 59, 300);

			s1.save("file1");
			s1 = new StopWatch();  // resets to zero

			s1.load("file8");
			assertTrue(s1.equals(s2));

		}


		@Test(expected = IllegalArgumentException.class)
		public void testLoadSaveNullLoad() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(5, 59, 300);
			String name = null;
			s1.save("file1");
			s1 = new StopWatch();  // resets to zero

			s1.load(name);
			assertTrue(s1.equals(s2));

		}


	/******************************************************************
	 * Tests of the save method
	 */

		@Test(expected = NullPointerException.class)
		public void testLoadSaveFailSave2() {
			String name = "file1";
			StopWatch s2 = null;
			s2.save(name);
		}


		@Test(expected = IllegalArgumentException.class)
		public void testLoadSaveFailSave3() {

			StopWatch s2 = new StopWatch(5, 59, 300);
			s2.save("");
		}


	/******************************************************************
	 * Tests of the suspend method
	 */

		@Test
		public void testMutate() {
			StopWatch s1 = new StopWatch(5, 59, 300);
			StopWatch s2 = new StopWatch(5, 59, 300);

			StopWatch.setSuspend(true);
			s1.add(1000);
			assertTrue(s1.equals(s2));
			StopWatch.setSuspend(false);
		}


		@Test
		public void testSuspended() {
			StopWatch s1 = new StopWatch();
			StopWatch.setSuspend(true);
			assertTrue(StopWatch.isSuspended());
			StopWatch.setSuspend(false);
			assertFalse(StopWatch.isSuspended());

		}


	/******************************************************************
	 * Tests of the stopwatch from other stopwatch constructor
	 */

		@Test(expected = IllegalArgumentException.class)
		public void stopwatchFromStopwatch() {
			StopWatch s1 = null;
			StopWatch s2 = new StopWatch(s1);
		}


		@Test
		public void stopwatchFromStopwatchWithTime() {
			StopWatch s1 = new StopWatch(30, 30, 800);
			StopWatch s2 = new StopWatch(s1);
			assertTrue(StopWatch.equals(s1, s2));
		}

	/******************************************************************
	 * Tests of the sub method - both stopwatch and millisecond input
	 */

		@Test
		public void testSubMethod() {
			StopWatch s5 = new StopWatch(1, 300);
			s5.sub(1000);
			StopWatch s6 = new StopWatch(300);
			assertTrue(s5.equals(s6));
		}


		@Test
		public void testSubMethodLarge() {
			StopWatch s5 = new StopWatch(1, 1, 300);

			s5.sub(1000);
			s5.sub(60000);
			StopWatch s6 = new StopWatch(300);
			assertTrue(s5.equals(s6));
		}


		@Test(expected = IllegalStateException.class)
		public void testSubMethodNegative() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.sub(1500);
			s5.sub(60000);
		}


		@Test
		public void testSubWithoutError() {

			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = new StopWatch(1, 0, 0);
			StopWatch s3 = new StopWatch(1, 0, 0);
			s1.sub(s2);
			assertTrue(s1.equals(s3));

		}


		@Test(expected = IllegalArgumentException.class)
		public void testSubWithError() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			s1.sub(new StopWatch(-1000));
		}


		@Test (expected = IllegalArgumentException.class)
		public void testSubWithNewError() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = new StopWatch(3, 0, 0);
			s1.sub(s2);


		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchSubtractionNeg() {
			StopWatch s2 = new StopWatch(500);
			s2.sub(-500);
		}


		@Test(expected = IllegalArgumentException.class)
		public void stopwatchSubtractionError() {
			StopWatch s1 = null;
			StopWatch s2 = new StopWatch(500);
			s2.sub(s1);
		}


		@Test
		public void stopwatchSubtractionFromSkeletonWatch3Params() {
			StopWatch s2 = new StopWatch(2,1,502);
			s2.sub(new StopWatch(2,0,503));
			assertEquals(s2.toString(), "0:00:999");
		}


		@Test
		public void stopwatchSubtractionFromSkeletonWatch2Params() {
			StopWatch s2 = new StopWatch(3,0,500);
			s2.sub(new StopWatch(1,501));
			assertEquals(s2.toString(), "2:58:999");
		}


		@Test
		public void stopwatchSubtractionFromSkeletonWatch1params() {
			StopWatch s2 = new StopWatch(3,0,500);
			s2.sub(new StopWatch(501));
			assertEquals(s2.toString(), "2:59:999");
		}


		@Test
		public void stopwatchSubtractionFromSkeletonWatch3paramsSuspended() {
			StopWatch s2 = new StopWatch(2,1,502);
			s2.sub(new StopWatch(2,0,503));
			assertEquals(s2.toString(), "0:00:999");
		}


		@Test
		public void stopwatchSubtractionNotSuspended() {
			StopWatch s2 = new StopWatch(1,500);
			s2.sub(500);
		}


		@Test (expected = IllegalArgumentException.class)
		public void stopwatchSubtractionWithNull() {
			StopWatch s2 = new StopWatch(1,500);
			StopWatch s1 = null;
			s2.sub(s1);
		}


		@Test
		public void testSubWithoutErrorSuspended() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = new StopWatch(1, 0, 0);
			s1.sub(s2);
			assertTrue(s1.equals(s2));

		}


		@Test
		public void stopwatchSubtractionSuspended() {
			StopWatch s2 = new StopWatch(1,500);
			StopWatch s3 = new StopWatch(1,500);
			StopWatch.setSuspend(true);
			s2.sub(500);
			StopWatch.setSuspend(false);
			assertTrue(s2.equals(s3));
		}


		@Test
		public void testSubwitherrorSuspended() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = new StopWatch(1, 0, 0);
			StopWatch s3 = new StopWatch(2, 0, 0);
			StopWatch.setSuspend(true);
			s1.sub(s2);
			StopWatch.setSuspend(false);
			assertTrue(s1.equals(s3));

		}


		@Test (expected = NullPointerException.class)
		public void testSubWithErrorNull() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = null;
			s2.sub(s1);

		}


		@Test (expected = IllegalArgumentException.class)
		public void testSubWithErrorNullInput() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s2 = null;
			s1.sub(null);

		}


	/******************************************************************
	 * Tests of the setMilliseconds(), setSeconds(), and setMinutes()
	 * methods
	 */

		@Test(expected = IllegalArgumentException.class)
		public void testSetMillisecondsNegInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setMilliseconds(-800);

		}


		@Test(expected = IllegalArgumentException.class)
		public void testSetMillisecondsLargeInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setMilliseconds(8008);

		}


		@Test
		public void testSetMillisecondsStandardInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setMilliseconds(800);
			StopWatch s6 = new StopWatch(1, 1, 800);
			assertTrue(s5.equals(s6));

		}


		@Test(expected = IllegalArgumentException.class)
		public void testSetSecondsNegInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setSeconds(-800);

		}


		@Test(expected = IllegalArgumentException.class)
		public void testSetSecondsLargeInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setSeconds(8008);

		}


		@Test
		public void testSetSecondsStandardInput() {

			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setSeconds(8);
			StopWatch s6 = new StopWatch(1, 8, 300);
			assertTrue(s5.equals(s6));

		}


		@Test(expected = IllegalArgumentException.class)
		public void testSetMinutesNegInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setMinutes(-800);

		}


		@Test
		public void testSetMinutesLargeInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setMinutes(8008);
			StopWatch s6 = new StopWatch(8008, 1, 300);
			assertTrue(s5.equals(s6));

		}


		@Test
		public void testSetMinutesStandardInput() {
			StopWatch s5 = new StopWatch(1, 1, 300);
			s5.setMinutes(8);
			StopWatch s6 = new StopWatch(8, 1, 300);
			assertTrue(s5.equals(s6));

		}


		@Test
		public void testSetMinsWithSuspended() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s3 = new StopWatch(2, 0, 0);
			StopWatch.setSuspend(true);
			s1.setMinutes(3);
			StopWatch.setSuspend(false);
			assertTrue(s1.equals(s3));
		}


		@Test (expected = IllegalArgumentException.class)
		public void testSetMinsWithoutSuspendedNegativeMins() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			s1.setMinutes(-3);
		}


		@Test
		public void testSetSecsWithSuspended() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s3 = new StopWatch(2, 0, 0);
			StopWatch.setSuspend(true);
			s1.setSeconds(3);
			StopWatch.setSuspend(false);
			assertTrue(s1.equals(s3));
		}


		@Test (expected = IllegalArgumentException.class)
		public void testSetSecsWithoutSuspendesNegativeSecs() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			s1.setSeconds(-3);
		}


		@Test (expected = IllegalArgumentException.class)
		public void testSetSecsWithoutSuspendedLargeSecs() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			s1.setSeconds(3000);
		}


		@Test
		public void testSetMillisecsWithSuspended() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			StopWatch s3 = new StopWatch(2, 0, 0);
			StopWatch.setSuspend(true);
			s1.setMilliseconds(300);
			StopWatch.setSuspend(false);
			assertTrue(s1.equals(s3));
		}


		@Test (expected = IllegalArgumentException.class)
		public void testSetMillisecsWithoutSuspendedNegativeMillisecs() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			s1.setMilliseconds(-3);
		}


		@Test (expected = IllegalArgumentException.class)
		public void testSetMillisecsWithoutSuspendedLargeMillisecs() {
			StopWatch s1 = new StopWatch(2, 0, 0);
			s1.setMilliseconds(3000);
		}


	}
