/**
 ********************************************************************************
 *** Comment.java                                                             ***
 *** generated by AnoSiteGenerator (ASG), Version: 1.3.3                      ***
 *** Copyright (C) 2005 - 2010 Anotheria.net, www.anotheria.net               ***
 *** All Rights Reserved.                                                     ***
 ********************************************************************************
 *** Don't edit this code, if you aren't sure                                 ***
 *** that you do exactly know what you are doing!                             ***
 *** It's better to invest time in the generator, as into the generated code. ***
 ********************************************************************************
 */

package net.anotheria.moskitodemo.sqltrace.persistence.data;

public interface Comment {

	/**
	 * Constant property name for "id" for internal storage and queries.
	 */
	public static final String PROP_ID	= "id";
	/**
	 * Constant property name for "firstName" for internal storage and queries.
	 */
	public static final String PROP_FIRST_NAME	= "firstName";
	/**
	 * Constant property name for "lastName" for internal storage and queries.
	 */
	public static final String PROP_LAST_NAME	= "lastName";
	/**
	 * Constant property name for "email" for internal storage and queries.
	 */
	public static final String PROP_EMAIL	= "email";
	/**
	 * Constant property name for "text" for internal storage and queries.
	 */
	public static final String PROP_TEXT	= "text";
	/**
	 * Constant property name for "timestamp" for internal storage and queries.
	 */
	public static final String PROP_TIMESTAMP	= "timestamp";
	/**
	 * Constant property name for "wishesUpdates" for internal storage and queries.
	 */
	public static final String PROP_WISHES_UPDATES	= "wishesUpdates";

	/**
	 * Returns the value of the firstName attribute.
	 */
	public String getFirstName();

	/**
	 * Sets the value of the firstName attribute.
	 */
	public void setFirstName(String value);

	/**
	 * Returns the value of the lastName attribute.
	 */
	public String getLastName();

	/**
	 * Sets the value of the lastName attribute.
	 */
	public void setLastName(String value);

	/**
	 * Returns the value of the email attribute.
	 */
	public String getEmail();

	/**
	 * Sets the value of the email attribute.
	 */
	public void setEmail(String value);

	/**
	 * Returns the value of the text attribute.
	 */
	public String getText();

	/**
	 * Sets the value of the text attribute.
	 */
	public void setText(String value);

	/**
	 * Returns the value of the timestamp attribute.
	 */
	public long getTimestamp();

	/**
	 * Sets the value of the timestamp attribute.
	 */
	public void setTimestamp(long value);

	/**
	 * Returns the value of the wishesUpdates attribute.
	 */
	public boolean getWishesUpdates();

	/**
	 * Sets the value of the wishesUpdates attribute.
	 */
	public void setWishesUpdates(boolean value);


    public String getId();
}
