package com.furlenco.core.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.furlenco.commons.entity.AdminUser;

public interface AdminUserRepo extends CrudRepository<AdminUser, Integer> {
	public List<AdminUser> findAll();
}