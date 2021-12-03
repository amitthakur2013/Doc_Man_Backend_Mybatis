package com.docdownload.controller;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docdownload.model.Document;
import com.docdownload.repository.DocumentMapper;
import com.docdownload.service.DocumentServiceImpl;

@Controller
public class DocumentController {

	@Autowired
	private DocumentMapper repo;

	@Autowired
	private DocumentServiceImpl docService;


	// FIND BY STATUS PENDING

	@GetMapping("/")
	public ResponseEntity<?> viewHomePage() {

		List<Document> docs=docService.getAllByPendingStatus();
		return ResponseEntity.ok(docs);
	}

	// FOR STATUS UPDATE

	@PutMapping("/document/{id}")
	public ResponseEntity<?> updateDoc(@PathVariable("id") Long id, @RequestBody Document doc) {
		boolean updatedDoc = docService.updateDocumentStatus(id, doc);
		if (updatedDoc) {
			return ResponseEntity.ok(updatedDoc);
		} else {
			return new ResponseEntity(null, HttpStatus.NOT_MODIFIED);
		}
	}

	// FOR PDF TYPE FILE VIEWING

	@GetMapping("/view/pdf/{id}")
	public ResponseEntity<Resource> getDoc(@PathVariable("id") Long id, HttpServletResponse response) {

		Document doc = docService.getDocumentContentById(id);
		if (doc != null) {
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=Dummy.pdf");

			ByteArrayResource resource = new ByteArrayResource(doc.getContent());

			return ResponseEntity.ok().headers(header).contentLength(doc.getContent().length)
					.contentType(MediaType.parseMediaType("application/pdf")).body(resource);

		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	// FOR JPG/JPEG/PNG/BMP VIEWING

	@GetMapping("/view/image/{id}")
	public ResponseEntity<?> getImg(@PathVariable("id") Long id) {
		Map<String, byte[]> imgData = new HashMap<>();
		Document doc = docService.getDocumentContentById(id);
		if (doc != null) {
			imgData.put("imgByte", doc.getContent());
			return ResponseEntity.ok(imgData);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	// FOR DOWNLOADING FILE

	@GetMapping("/download/file/{id}")
	public ResponseEntity<Resource> getZipDoc(@PathVariable("id") Long id, HttpServletResponse response) {

		Document doc = docService.getDocumentContentById(id);

		if (doc != null) {
			String mimeType = URLConnection.guessContentTypeFromName(doc.getName());

			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + doc.getName());
			header.add(HttpHeaders.CONTENT_TYPE, mimeType);

			ByteArrayResource resource = new ByteArrayResource(doc.getContent());

			return ResponseEntity.ok().headers(header).body(resource);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	
}
