package com.horasphere.springstarter.web.models;

import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface UserDTORepository extends Repository<UserDTO, String>
{
    List<UserDTO> findAll();
    UserDTO findOne(String email);
}