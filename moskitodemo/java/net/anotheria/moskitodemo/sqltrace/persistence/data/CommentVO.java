/**
 ********************************************************************************
 *** The implementation of the Comment..java                                  ***
 *** Generator: net.anotheria.asg.generator.model.db.VOGenerator              ***
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

import net.anotheria.util.crypt.MD5Util;

import java.io.Serializable;

public class CommentVO implements Comment, Serializable {

	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String text;
	private long timestamp;
	private boolean wishesupdates;
	private long daocreated;
	private long daoupdated;

	public CommentVO(String anId){
		id = anId;
	}

	public CommentVO(CommentVO toClone){
		this.id = toClone.id;
		copyAttributesFrom(toClone);
	}

	CommentVO(CommentBuilder builder){
		id = "";
		firstname = builder.firstName;
		lastname = builder.lastName;
		email = builder.email;
		text = builder.text;
		timestamp = builder.timestamp;
		wishesupdates = builder.wishesUpdates;
	}

	public String getId(){
		return id;
	}
	public String getFirstName(){
		return firstname;
	}

	public void setFirstName(String value){
		this.firstname = value;
	}

	public String getLastName(){
		return lastname;
	}

	public void setLastName(String value){
		this.lastname = value;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String value){
		this.email = value;
	}

	public String getText(){
		return text;
	}

	public void setText(String value){
		this.text = value;
	}

	public long getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(long value){
		this.timestamp = value;
	}

	public boolean getWishesUpdates(){
		return wishesupdates;
	}

	public void setWishesUpdates(boolean value){
		this.wishesupdates = value;
	}

	public long getDaoCreated(){
		return daocreated;
	}
	public void setDaoCreated(long value){
		this.daocreated = value;
	}
	public long getDaoUpdated(){
		return daoupdated;
	}
	public void setDaoUpdated(long value){
		this.daoupdated = value;
	}

	public String toString(){
		String ret = "Comment ";
		ret += "["+getId()+"] ";
		ret += "firstName: "+getFirstName();
		ret += ", ";
		ret += "lastName: "+getLastName();
		ret += ", ";
		ret += "email: "+getEmail();
		ret += ", ";
		ret += "text: "+getText();
		ret += ", ";
		ret += "timestamp: "+getTimestamp();
		ret += ", ";
		ret += "wishesUpdates: "+getWishesUpdates();
		return ret;
	}

	public void copyAttributesFrom(Comment toCopy){
		this.firstname = toCopy.getFirstName();
		this.lastname = toCopy.getLastName();
		this.email = toCopy.getEmail();
		this.text = toCopy.getText();
		this.timestamp = toCopy.getTimestamp();
		this.wishesupdates = toCopy.getWishesUpdates();
	}

	public Object getPropertyValue(String propertyName){
		if (PROP_ID.equals(propertyName))
			return getId();
		if (PROP_FIRST_NAME.equals(propertyName))
			return getFirstName();
		if (PROP_LAST_NAME.equals(propertyName))
			return getLastName();
		if (PROP_EMAIL.equals(propertyName))
			return getEmail();
		if (PROP_TEXT.equals(propertyName))
			return getText();
		if (PROP_TIMESTAMP.equals(propertyName))
			return getTimestamp();
		if (PROP_WISHES_UPDATES.equals(propertyName))
			return getWishesUpdates();
		throw new RuntimeException("No property getter for "+propertyName);
	}

	public String getDefinedName(){
		return "Comment";
	}

	public String getDefinedParentName(){
		return "Comments";
	}

	public String getFootprint(){
		StringBuilder footprint = new StringBuilder();
		footprint.append(getFirstName());
		footprint.append(getLastName());
		footprint.append(getEmail());
		footprint.append(getText());
		footprint.append(getTimestamp());
		footprint.append(getWishesUpdates());
		return MD5Util.getMD5Hash(footprint);
	}

	public boolean equals(Object o){
		return o == this || ((o instanceof CommentVO) && ((CommentVO)o).getId().equals(getId()));
	}
}
