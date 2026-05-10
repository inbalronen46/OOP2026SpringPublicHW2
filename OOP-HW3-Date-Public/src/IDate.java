
public interface IDate {

	public void setDay(short day);

	public void setMonth(short month);

	public void setYear(short year);

	public short getDay();

	public short getMonth();

	public short getYear();

	/**
	 * @param t
	 * @return return true if this instance represents the same date and time as t.
	 *         Otherwise return false.	 
	 *         If t is null return false
	 */
	public boolean equals(IDate t);

	/**
	 * @param t
	 * @return return true if this instance is before t. Otherwise return false.
	 *         If t is null return false
	 */
	public boolean before(IDate t);

	/**
	 * @param t
	 * @return return true if this instance is after t. Otherwise return false
	 *         If t is null return false
	 */
	public boolean after(IDate t);

	/**
	 * @return the number of days from the first day in the Gregorian calendar
	 */
	public int numberOfDaysFromFirstDayGregorian();

	
	/**
	 * @return the type of the date (e.g. Gregorian, Jewish)
	 */
	public DateUtils.DateType getDateCalendar();

}
