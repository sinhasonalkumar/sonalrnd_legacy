//package com.sonal.java.xml.jaxb.vo;
//
//public class BaseVOAdapter XmlAdapter<BaseVOAdapter.AdaptedContactMethod, BaseVO>  {
//	package blog.inheritance.xmladapter;
//	 
//	import javax.xml.bind.annotation.XmlAttribute;
//	import javax.xml.bind.annotation.adapters.XmlAdapter;
//	 
//	public class ContactMethodAdapter extends
//	    XmlAdapter<ContactMethodAdapter.AdaptedContactMethod, ContactMethod> {
//	 
//	    @Override
//	    public AdaptedContactMethod marshal(ContactMethod contactMethod)
//	        throws Exception {
//	        if (null == contactMethod) {
//	            return null;
//	        }
//	        AdaptedContactMethod adaptedContactMethod = new AdaptedContactMethod();
//	        if (contactMethod instanceof Address) {
//	            Address address = (Address) contactMethod;
//	            adaptedContactMethod.street = address.street;
//	            adaptedContactMethod.city = address.city;
//	        } else {
//	            PhoneNumber phoneNumber = (PhoneNumber) contactMethod;
//	            adaptedContactMethod.number = phoneNumber.number;
//	        }
//	        return adaptedContactMethod;
//	    }
//	 
//	    @Override
//	    public ContactMethod unmarshal(AdaptedContactMethod adaptedContactMethod)
//	        throws Exception {
//	        if (null == adaptedContactMethod) {
//	            return null;
//	        }
//	        if (null != adaptedContactMethod.number) {
//	            PhoneNumber phoneNumber = new PhoneNumber();
//	            phoneNumber.number = adaptedContactMethod.number;
//	            return phoneNumber;
//	        } else {
//	            Address address = new Address();
//	            address.street = adaptedContactMethod.street;
//	            address.city = adaptedContactMethod.city;
//	            return address;
//	        }
//	    }
//	 
//	    public static class AdaptedContactMethod {
//	 
//	        @XmlAttribute
//	        public String number;
//	 
//	        @XmlAttribute
//	        public String street;
//	 
//	        @XmlAttribute
//	        public String city;
//	 
//	    }
//	 
//	}
//}
