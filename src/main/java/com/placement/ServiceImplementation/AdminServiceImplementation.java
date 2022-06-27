package com.placement.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.placement.Entity.AdminEntity;
import com.placement.Exception.ResourceNotFoundException;
import com.placement.Payloads.AdminDto;
import com.placement.Repository.AdminRepository;
import com.placement.Repository.StudentRepository;
import com.placement.Service.AdminService;
@Repository
public class AdminServiceImplementation implements AdminService
{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	
	private StudentRepository studentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AdminDto createAdmin(AdminDto adminDto) 
	{
		AdminEntity adminEntity=this.adminDtoToAdminEntity(adminDto);
		AdminEntity savedAdmin=this.adminRepository.save(adminEntity); 
		return this.adminEntityToAdminDto(savedAdmin);
	}


	

	@Override
	public AdminDto getAdminById(int adminId) 
	{
		AdminEntity adminEntity=this.adminRepository.findById(adminId).
				      orElseThrow(()->new ResourceNotFoundException("Admin", "AdminId", adminId));
		return this.modelMapper.map(adminEntity, AdminDto.class);
	}

	@Override
	public List<AdminDto> getAllAdmins()
	{
		List<AdminEntity> adminEntity=this.adminRepository.findAll();
		List<AdminDto> adminDtoList=adminEntity.stream().map(admin->this.modelMapper.map(adminEntity, AdminDto.class)).collect(Collectors.toList());
		return adminDtoList;
	}

	@Override
	public AdminDto updateAdminById(AdminDto adminDto, int adminId) 
	{
		AdminEntity adminEntity=this.adminRepository.findById(adminId).orElseThrow(()->new ResourceNotFoundException("Admin","AdminId",adminId));
		// TODO Auto-generated method stub
		AdminEntity updateAdmin=this.adminRepository.save(this.adminDtoToAdminEntity(adminDto));
		return this.adminEntityToAdminDto(updateAdmin);
	}

	@Override
	public void deleteAdminById(int adminId)
	{
		AdminEntity adminEntity=this.adminRepository.findById(adminId).orElseThrow(()->new ResourceNotFoundException("Admin","AdminId",adminId));
		this.adminRepository.delete(adminEntity);
		
	}
	private AdminDto adminEntityToAdminDto(AdminEntity savedAdmin)
	{
		// TODO Auto-generated method stub
		return this.modelMapper.map(savedAdmin, AdminDto.class);
	}


	private AdminEntity adminDtoToAdminEntity(AdminDto adminDto)
	{
		// TODO Auto-generated method stub
		return this.modelMapper.map(adminDto, AdminEntity.class);
	}


}