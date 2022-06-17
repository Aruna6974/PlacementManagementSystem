package com.placement.Service;

import java.util.List;

import com.placement.Payloads.CompanyDto;


public interface CompanyService 
{

	public CompanyDto createCompany(CompanyDto companyDto,int studentId);
	
	public CompanyDto getCompanyById(int companyId);
	 
	public List<CompanyDto> getAllCompanies();
	
	public CompanyDto updateCompaniesById( CompanyDto companyDto,int companyId);
	
	public void deleteCompanyById(int companyId);
	
	public List<CompanyDto> getAllCompaniesByStudent(int studentId);
	
	


}
