package cn.hd.common.enumeration;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.type.IntegerType;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;




public class EnumDescriptionUserType  implements  UserType, ParameterizedType {
	private  Class enumClass;
	
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return arg0;
	}
	public Object deepCopy(Object arg0) throws HibernateException {
		return arg0;
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		 return  (Serializable) arg0;
	}

	public boolean equals(Object id1, Object id2) throws HibernateException {
		  if  (id1  ==  id2) {
	            return   true ;
	       }
	        if  (id1  ==   null   ||  id2  ==   null ) {
	            return   false ;
	       }

	        final  EnumDescription did1  =  (EnumDescription) id1;
	        final  EnumDescription did2  =  (EnumDescription) id2;

	        return  did1.getVal()  ==  did2.getVal()
	        		&& ((did1.getDescription() == null) ? false : (did2
	                        .getDescription() == null) ? true : did1.getDescription().equals(did2
	                                .getDescription()));
	}

	public int hashCode(Object arg0) throws HibernateException {
		return arg0.hashCode();
	}

	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names,
			SessionImplementor arg2, Object arg3) throws HibernateException,
			SQLException {
		try  {
			int  id  =  resultSet.getInt(names[ 0 ]);
            if  (resultSet.wasNull()) {
                return   null ;
           }
            return  enumClass.getMethod( "findById" ,  new  Class[] { Integer. class  })
                   .invoke( null , id);
		}  catch  (IllegalArgumentException e) {
           e.printStackTrace();
		}  catch  (SecurityException e) {
           e.printStackTrace();
		}  catch  (IllegalAccessException e) {
           e.printStackTrace();
		}  catch  (InvocationTargetException e) {
           e.printStackTrace();
		}  catch  (NoSuchMethodException e) {
           e.printStackTrace();
		}
        return   null ;
	}

	public void nullSafeSet(PreparedStatement statement, Object value, int index,
			SessionImplementor arg3) throws HibernateException, SQLException {
		 if  (value  ==   null ) {
	           statement.setNull(index, IntegerType.INSTANCE.sqlType());
	       }  else  {
	           EnumDescription dID  =  (EnumDescription) value;
	           statement.setInt(index, dID.getVal());
	       }
		
	}

	public Object replace(Object original, Object arg1, Object arg2)
			throws HibernateException {
		return original;
	}

	public Class returnedClass() {
		return EnumDescription.class;
	}

	public int[] sqlTypes() {
		return new   int []{IntegerType.INSTANCE.sqlType()};
	}

	public void setParameterValues(Properties arg0) {
			try  {
	           enumClass  =  ReflectHelper.classForName(arg0.getProperty( "class" ));
	       }  catch  (ClassNotFoundException e) {
	            //  TODO Auto-generated catch block 
	           e.printStackTrace();
	       }
		
	}


}
