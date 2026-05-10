import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exceptionsTypes.InputTimeException;

public class TestPartADateTime {

	IDate gd1;
	IDate jd1;
	Time t1;
	Time t2;
	
	DateTime dt1;
	DateTime dt2;
	
	@Before
	public void before() throws Exception {
		gd1 = new GregorianDate(01, 10, 2020);
		jd1 = new JewishDate(02, 11, 2021);
		t1 = new Time("10:00:00");
		t2 = new Time("02:10:30");
	
		dt1 = new DateTime(gd1, t1);
		dt2 = new DateTime(jd1, t2);
	}

	@Test
	public void testGettersSetters() {
		compareGetters(dt1, DateUtils.DateType.GREGORIAN, 01, 10, 2020, 10,0,0);
		compareGetters(dt2, DateUtils.DateType.JEWISH, 02, 11, 2021, 2,10,30);	
		gd1 = new GregorianDate(01, 10, 2020);
		dt1.setDate(jd1);
		dt1.setTime(t2);
		assertEquals(dt1.getDate().toString(), jd1.toString());
		assertEquals(dt1.getTime().toString(), t2.toString());
		
		dt2.setDate(gd1);
		dt2.setTime(t1);
		assertEquals(dt2.getDate().toString(), gd1.toString());
		assertEquals(dt2.getTime().toString(), t1.toString());
	}

	private void compareGetters( DateTime dateTime, DateUtils.DateType type, int day, int month, int year, 
			int hr, int min, int sec) {
		assertEquals(type, dateTime.getDateCalendar());
		assertEquals(day, dateTime.getDay());
		assertEquals(month, dateTime.getMonth());
		assertEquals(year, dateTime.getYear());
		assertEquals(hr, dateTime.getHour());
		assertEquals(min, dateTime.getMinute());
		assertEquals(sec, dateTime.getSecond());
	}

	@Test
	public void testEqualsBforeAfter() throws InputTimeException {
		DateTime dt3=new DateTime(dt1.getDate(), dt1.getTime()); //equals to dt1.
		DateTime dt4=new DateTime(dt1.getDate(), new Time("9:00:00"));	//equals to dt1 gregorian date, but before in time.
		DateTime dt5=new DateTime(new GregorianDate(01, 9, 2020), dt1.getTime());// equals in time to dt1, but date before dt1
		
		//equals to dt1, but dt1 is Gregorian sate, and dt6 is Jewish date.
		DateTime dt6=new DateTime(DateUtils.jewishDateFromAbsolute(DateUtils.absoluteFromGregorianDate((GregorianDate)dt1.getDate())), dt1.getTime());
		
		assertTrue(dt6.equals(dt1));
		assertFalse(dt6.before(dt1));
		assertFalse(dt6.after(dt1));
		
		assertTrue(dt2.before(dt1));
		assertFalse(dt2.equals(dt1));
		assertFalse(dt2.after(dt1));
		
		assertTrue(dt1.after(dt2));
		assertFalse(dt1.equals(dt2));
		assertFalse(dt1.before(dt2));
		
		assertTrue(dt3.equals(dt1));
		assertTrue(dt4.before(dt1));
		assertTrue(dt5.before(dt1));
		assertTrue(dt1.after(dt4));
		assertTrue(dt1.after(dt5));
		
	}

	@Test
	public void testTostring() {
		assertEquals(dt1.toString(), "Gregorian date 01 Oct. 2020, 10:00:00");
		assertEquals(dt2.toString(), "Jewish date 02 Shvat 2021, 02:10:30");
	}


}