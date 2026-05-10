import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestPartADate {

	IDate gd1;
	IDate jd1;

	@Before
	public void before() {
		gd1 = new GregorianDate(01, 10, 2020);
		jd1 = new JewishDate(02, 11, 2021);
	}

	@Test
	public void testGettersSetters() {
		compareGetters(DateUtils.DateType.GREGORIAN, 01, 10, 2020, gd1);
		compareGetters(DateUtils.DateType.JEWISH, 02, 11, 2021, jd1);

		gd1.setDay((short) 15);
		gd1.setMonth((short) 02);
		gd1.setYear((short) 2010);
		compareGetters(DateUtils.DateType.GREGORIAN, 15, 02, 2010, gd1);

		jd1.setDay((short) 20);
		jd1.setMonth((short) 01);
		jd1.setYear((short) 5780);
		compareGetters(DateUtils.DateType.JEWISH, 20, 01, 5780, jd1);
	}

	private void compareGetters(DateUtils.DateType type, int day, int month, int year, IDate date) {
		assertEquals(type, date.getDateCalendar());
		assertEquals(day, date.getDay());
		assertEquals(month, date.getMonth());
		assertEquals(year, date.getYear());
	}

	@Test
	public void testEqualsBforeAfter() {
		assertFalse(gd1.before(jd1));
		assertTrue(gd1.after(jd1));
		assertFalse(gd1.equals(jd1));

		assertFalse(jd1.after(gd1));
		assertTrue(jd1.before(gd1));
		assertFalse(jd1.equals(gd1));

		JewishDate jd2 = DateUtils.jewishDateFromAbsolute(DateUtils.absoluteFromGregorianDate((GregorianDate) gd1));

		assertFalse(jd2.before(jd1));
		assertTrue(jd2.after(jd1));
		assertFalse(jd2.equals(jd1));

		assertFalse(gd1.before(jd2));
		assertFalse(gd1.after(jd2));
		assertTrue(gd1.equals(jd2));

		assertFalse(jd2.before(gd1));
		assertFalse(jd2.after(gd1));
		assertTrue(jd2.equals(gd1));

		assertTrue(jd2.equals(jd2));
		assertTrue(gd1.equals(gd1));
	}

	@Test
	public void testTostring() {
		assertEquals(gd1.toString(), "Gregorian date 01 Oct. 2020");
		assertEquals(jd1.toString(), "Jewish date 02 Shvat 2021");
		jd1 = new JewishDate(02, 11, 5782);
	}

	@Test
	public void testLeapJewishYear() {

		jd1 = new JewishDate(02, 12, 5782);
		assertEquals(jd1.toString(), "Jewish date 02 Adar A 5782");
		jd1 = new JewishDate(02, 13, 5782);
		assertEquals(jd1.toString(), "Jewish date 02 Adar B 5782");
		assertEquals(jd1.getMonth(), 13);
	}

	@Test
	public void testConstactors() {
		JewishDate jd2 = null;
		JewishDate jd3 = null;
		GregorianDate gd2 = null;
		GregorianDate gd3 = null;
		try {
			jd2 = new JewishDate((JewishDate) jd1);
			jd3 = new JewishDate("02/11/2021");

			gd2 = new GregorianDate((GregorianDate) gd1);
			gd3 = new GregorianDate("01/10/2020");
		} catch (Exception e) {
			fail("An unexpected exception was thrown.");
		}
		assertTrue(jd1.equals(jd2));
		assertTrue(jd1.equals(jd3));

		assertTrue(gd1.equals(gd2));
		assertTrue(gd1.equals(gd3));
	}

}