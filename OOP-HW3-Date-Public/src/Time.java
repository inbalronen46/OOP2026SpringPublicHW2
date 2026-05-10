import java.util.Scanner;

import exceptionsTypes.InputTimeException;

/**
 * @author Ron Sivan (ronny.sivan@gmail.com)
 * @author Michal Horovitz
 */

public class Time {

	public static final short SECS_PER_MIN = 60;
	public static final short MINS_PER_HOUR = 60;
	public static final short HOURS_PER_DAY = 24;

	public static final int SECS_PER_HOUR = MINS_PER_HOUR * SECS_PER_MIN;
	public static final int SECS_PER_DAY = HOURS_PER_DAY * SECS_PER_HOUR;

	private short hour;
	private short min;
	private short sec;

	public Time(Time t) {
		this(t.getHour(), t.getMinute(), t.getSecond());
	}

	public Time(short h, short m, short s) {
		setHour(h);
		setMinute(m);
		setSecond(s);
	}

	public Time(String time) throws InputTimeException {
		Scanner paramsScan = new Scanner(time);
		try {
			paramsScan.useDelimiter(":");
			setHour(paramsScan.nextShort());
			setMinute(paramsScan.nextShort());
			setSecond(paramsScan.nextShort());
		} catch (Exception e) {
			throw new InputTimeException("Exception in scanning the time string");
		} finally {
			paramsScan.close();
		}
	}

	/**
	 * @return the hours - an integer number between 0 to 23.
	 */
	public short getHour() {
		return hour;
	}

	/**
	 * @return the minutes - an integer number between 0 to 59.
	 */
	public short getMinute() {
		return min;
	}

	/**
	 * @return the seconds - an integer number between 0 to 59.
	 */
	public short getSecond() {
		return sec;
	}

	/**
	 * @param hour update the hours to be the new parameter.
	 */
	public void setHour(short hour) {
		if ((hour > 23) || (hour < 0))
			throw new IllegalArgumentException("Hour argument " + hour + " is illegal.");
		this.hour = hour;
	}

	/**
	 * @param min update the minutes to be the new parameter.
	 */
	public void setMinute(short min) {
		if ((hour > 60) || (hour < 0))
			throw new IllegalArgumentException("Hour argument " + hour + " is illegal.");
		this.min = min;
	}

	/**
	 * @param sec update the seconds to be the new parameter.
	 */
	public void setSecond(short sec) {
		if ((hour > 60) || (hour < 0))
			throw new IllegalArgumentException("Hour argument " + hour + " is illegal.");
		this.sec = sec;
	}

	/**
	 * @param t
	 * @return return true if this instance represents the same time as t. Otherwise
	 *         return false. If t is null return false
	 */
	public boolean equals(Time t) {
		if (t == null)
			return false;
		return ((getHour() == t.getHour()) && (getMinute() == t.getMinute()) && (getSecond() == t.getSecond()));
	}

	/**
	 * @param t
	 * @return return true if this instance is before t. Otherwise return false If t
	 *         is null return false
	 */
	/**
	 * @param t
	 * @return return true if this instance is before t. Otherwise return false If t
	 *         is null return false
	 */
	public boolean before(Time t) {
		if (t == null)
			return false;
		if (getHour() < t.getHour())
			return true;
		if (getHour() > t.getHour())
			return false;
		if (getMinute() < t.getMinute())
			return true;
		if (getMinute() > t.getMinute())
			return false;
		if (getSecond() < t.getSecond())
			return true;

		return (false);
	}

	/**
	 * @param t
	 * @return return true if this instance is after t. Otherwise return false If t
	 *         is null return false
	 */
	public boolean after(Time t) {
		if (equals(t))
			return false;
		return (!before(t));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%02d", getHour()));
		sb.append(":");
		sb.append(String.format("%02d", getMinute()));
		sb.append(":");
		sb.append(String.format("%02d", getSecond()));

		return (sb.toString());
	}

}
