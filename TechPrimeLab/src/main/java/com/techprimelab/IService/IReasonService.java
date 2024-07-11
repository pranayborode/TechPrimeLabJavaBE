package com.techprimelab.IService;

import java.util.List;


import com.techprimelab.Models.Reason;

public interface IReasonService {
	
	List<Reason> getAllReason();
	String getReasonNameById(int reasonId);
}
