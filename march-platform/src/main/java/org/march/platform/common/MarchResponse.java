package org.march.platform.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response") 
public class MarchResponse {
	
	private ResponseStatus status;

	public MarchResponse(ResponseStatus status) {
		this.status = status;
	}
	
	public static MarchResponse result(ResponseStatus status) {
		return new MarchResponse(status);
	}
	
	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public MarchResponse() {
	}
	
}
