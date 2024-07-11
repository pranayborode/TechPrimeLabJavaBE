package com.techprimelab.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.IReasonService;

import com.techprimelab.Models.Reason;
import com.techprimelab.Repositories.ReasonRepo;

@Service
public class ReasonService implements IReasonService {

	@Autowired
	ReasonRepo reasonRepo;

	@Override
	public List<Reason> getAllReason() {
		List<Reason> reasonList = reasonRepo.findAll();
		return reasonList;
	}

	@Override
	public String getReasonNameById(int reasonId) {
		Optional<Reason> reasonOptional = reasonRepo.findById(reasonId);
		return reasonOptional.map(Reason::getReasonName).orElse(null);
	}
}
