package org.march.platform.webService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.march.platform.common.MarchResponse;
import org.march.platform.common.ResponseStatus;
import org.march.platform.common.Utils;
import org.march.platform.dto.DemoDto;
import org.march.platform.dto.DemoRequestDto;
import org.march.platform.dto.FileDemoDto;
import org.march.platform.entity.Demo;
import org.march.platform.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/demo")
public class DemoWebService {

	@Autowired
	private DemoService demoService;

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MarchResponse add(Demo demo) {
		ResponseStatus status = ResponseStatus.OK;
		try {
			demoService.add(demo);
		} catch (Exception e) {
			status = ResponseStatus.FAIL;
		}
		return MarchResponse.result(status);
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MarchResponse delete(@PathParam("id") Long id) {
		ResponseStatus status = ResponseStatus.OK;
		try {
			demoService.delete(id);
		} catch (Exception e) {
			status = ResponseStatus.FAIL;
		}
		return MarchResponse.result(status);
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MarchResponse delete(Demo demo) {
		ResponseStatus status = ResponseStatus.OK;
		try {
			demoService.update(demo);
		} catch (Exception e) {
			status = ResponseStatus.FAIL;
		}
		return MarchResponse.result(status);
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DemoDto getById(@PathParam("id") Long id) {
		try {
			return demoService.getById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DemoDto> getAll() {
		try {
			return demoService.getAll();
		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("/fileUpload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public MarchResponse uploadFile(Attachment att, @Context HttpServletRequest request) {
		ResponseStatus status = ResponseStatus.OK;
		try {
			DataHandler handler = att.getDataHandler();
			String strDirPath = request.getSession().getServletContext().getRealPath("/");
			strDirPath = strDirPath.replace(request.getContextPath().substring(1) + "\\", "");
			try {
				Utils.writeFile(handler.getInputStream(), strDirPath + "DemoUpload", handler.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			status = ResponseStatus.FAIL;
		}
		return MarchResponse.result(status);
	}

	@GET
	@Path("/getFiles")
	public List<FileDemoDto> getFiles(@Context HttpServletRequest request) {
		List<FileDemoDto> returnList = new ArrayList<>();
		String strDirPath = request.getSession().getServletContext().getRealPath("/");
		String basePath = strDirPath.replace(request.getContextPath().substring(1) + "\\", "") + "DemoUpload";
		File dir = new File(basePath);
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File file: Utils.ifNullReturnEmpty(Arrays.asList(files))) {
				String fileName = file.getName();
				String fullPath = basePath + "/" + fileName;
				FileDemoDto dto = new FileDemoDto(fileName, fullPath);
				returnList.add(dto);
			}
		}
		return returnList;
	}
	
	@DELETE
	@Path("/deleteFile/{fileName}")
	@Produces(MediaType.APPLICATION_JSON)
	public MarchResponse deleteFile(@PathParam("fileName") String fileName, @Context HttpServletRequest request) {
		ResponseStatus status = ResponseStatus.OK;
		String strDirPath = request.getSession().getServletContext().getRealPath("/");
		String basePath = strDirPath.replace(request.getContextPath().substring(1) + "\\", "") + "DemoUpload";
		try {
			File file = new File(basePath + "/" + fileName);
			if(file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			status = ResponseStatus.FAIL;
		}
		return MarchResponse.result(status);
	}
	
	@POST
	@Path("/getPaginationList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DemoDto> getPaginationList(DemoRequestDto demoRequestDto) {
		List<DemoDto> resultList = demoService.getListByPagination(demoRequestDto);
		return resultList;
	}
	
}
