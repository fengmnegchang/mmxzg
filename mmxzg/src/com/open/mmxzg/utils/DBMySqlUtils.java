///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-8下午3:30:13
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.utils;
//
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import android.util.Log;
//
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
//
///**
// ***************************************************************************************************************************************************************************** 
// * 
// * @author :fengguangjing
// * @createTime:2017-8-8下午3:30:13
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// ***************************************************************************************************************************************************************************** 
// */
//public class DBMySqlUtils {
//	public static final String TAG = "DBMySqlUtils";
//	public static final String DB_URL = "jdbc:mysql://192.168.1.15:3306/mmxzg";
//	public static final String USER_NAME = "root";
//	public static final String PASS_WORD = "fgj5211314";
//
//	public static boolean query(String sql) {
//		boolean next = false;
//		try {
//			// 注册驱动
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD);
//			Statement stmt = (Statement) conn.createStatement();
////			String sql = "select * from collection where url='"+openbean.getUrl()+"' and typename='"+openbean.getTypename()+"'";
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				Log.d(TAG, "field1-->");
//				next = true;
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//			Log.d(TAG, "success to connect!");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}  
//		return next;
//	}
//	
//	public static void insert(String[] queries) {
//		try {
//			// 注册驱动
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD);
//			Statement statemenet = (Statement) conn.createStatement();
//			for (String query : queries) {
//			    statemenet.addBatch(query);
//			}
//			statemenet.executeBatch();
//			statemenet.close(); 
//			conn.close();
//			Log.d(TAG, "success to connect!");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}  
//	}
//	
////	public static void insert(OpenDBBean openbean) {
////		try {
////			// 注册驱动
////			Class.forName("com.mysql.jdbc.Driver").newInstance();
////			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD);
////			String sql = "insert into collection(type,imgsrc,title,time,typename,url,downloadurl) values(?,?,?,?,?,?,?)";  
////			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
////			pst.setInt(1, openbean.getType());  
////			pst.setString(2, openbean.getImgsrc()); 
////			pst.setString(3, openbean.getTitle());
////			pst.setString(4, openbean.getTime());
////			pst.setString(5, openbean.getTypename());
////			pst.setString(6, openbean.getUrl());
////			pst.setString(7, openbean.getDownloadurl());
////			pst.executeUpdate();  
////			pst.close();  
////			conn.close();
////			Log.d(TAG, "success to connect!");
////		} catch (ClassNotFoundException e) {
////			e.printStackTrace();
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} catch (InstantiationException e) {
////			e.printStackTrace();
////		} catch (IllegalAccessException e) {
////			e.printStackTrace();
////		}  
////	}
//	
//	public static void update(String sql) {
//		try {
//			// 注册驱动
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD);
////			String sql = "update collection set title='"+openbean.getTitle()+"' where url='"+openbean.getUrl()+"' and typename='"+openbean.getTypename()+"'";  
//			Statement stmt= (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//			stmt.executeUpdate(sql);
//			stmt.close();
//			conn.close();
//			Log.d(TAG, "success to connect!");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}  
//	}
//}
