package com.docdownload.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.docdownload.model.Document;

@Repository
@Mapper
public interface DocumentMapper {

	@Results(id = "docMap", value = { @Result(property = "id", column = "id"),
			@Result(property = "customerId", column = "customer_id"),
			@Result(property = "docCorrespondingNumber", column = "doc_corresponding_number"),
			@Result(property = "status", column = "status"), @Result(property = "uploadTime", column = "upload_time"),
			@Result(property = "name", column = "name") })
	@Select("Select id, customer_id, doc_corresponding_number, status, upload_time, name from documents where status='wip'")
	public List<Document> findAllByPendingStatus();

	@Results(id = "docCheckMap", value = { @Result(property = "id", column = "id"), })
	@Select("Select id from documents where id=#{id}")
	public Document checkDocPresent(Long id);

	@Results(id = "docContentMap", value = { @Result(property = "content", column = "content"),
			@Result(property = "name", column = "name"), })
	@Select("Select name,content from documents where id=#{id}")
	public Document findDocContentById(Long id);

	@Update("Update documents set status=#{status}, updated_on=#{dt}, reject_reasons=#{reasons} where id=#{id}")
	public int updateStatus(Long id, String status, Date dt, String reasons);
	
	
	@Select({"<script>",
	      "select * from documents",
	      "  <where>",
	      "    <if test='stat != null'>status=#{stat}</if>",
	      "    <if test='doc_type != null'>AND doc_type=#{doc_type}</if>",
	      "    <if test='customer_id != null'>AND customer_id=#{customer_id}</if>",
	      "    <if test='fromDate != null &amp;&amp; toDate != null'>AND cast(upload_time as date) BETWEEN #{fromDate} AND #{toDate}</if>",
	      "  </where>",
	      "</script>"})
	public List<Document> findAllBySearchFilterCriteria(String stat, String doc_type, String customer_id, String fromDate, String toDate);
}
