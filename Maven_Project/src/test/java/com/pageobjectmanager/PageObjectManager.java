package com.pageobjectmanager;

import com.pageobjectmodel.LoginPage;
import com.utility.FileReaderManager;

public class PageObjectManager {
	
	private FileReaderManager fileReader;
	private static PageObjectManager pageObjectManager;
	private LoginPage loginPage;
	
	//source-select getters 
	
	public FileReaderManager getFileReader() {
		if (fileReader==null){
			fileReader = new FileReaderManager();
		}
		return fileReader;
	}
	public static PageObjectManager getPageObjectManager() {
		if (pageObjectManager == null)
		{
			pageObjectManager = new PageObjectManager();
		}
		return pageObjectManager;
	}
	public LoginPage getLoginPage() {
		
		if (loginPage==null)
		{
			loginPage = new LoginPage();
		}
		return loginPage;
	}
	

}
