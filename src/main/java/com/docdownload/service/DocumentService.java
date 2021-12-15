package com.docdownload.service;

import java.util.List;
import java.util.Map;

import com.docdownload.model.Document;
import com.docdownload.utility.SearchFilterRequest;

public interface DocumentService {
	
	public List<Document> getAllByPendingStatus();
	
	public List<Document> getAllBySearchFilterCriteria(SearchFilterRequest searchFilterRequest);
	
	public Document getDocumentContentById(Long id);
	
	public boolean updateDocumentStatus(Long id,Document doc);
}
