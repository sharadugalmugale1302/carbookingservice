package com.demo.carbookingservice.model;

public class Response {

	private int responseCode;
	private String responseMesage;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMesage() {
		return responseMesage;
	}

	public void setResponseMesage(String responseMesage) {
		this.responseMesage = responseMesage;
	}
	private Response(ResponseBuilder builder) {
		this.responseCode=builder.responseCode;
		this.responseMesage=builder.responseMesage;
	}

	// Builder Class
	public static class ResponseBuilder {

		private int responseCode;
		private String responseMesage;

		public ResponseBuilder() {

		}

		public ResponseBuilder setResponseCode(int responseCode) {
			this.responseCode = responseCode;
			return this;
		}

		public ResponseBuilder setResponseMesage(String responseMesage) {
			this.responseMesage = responseMesage;
			return this;
		}

		public Response build(){
			return new Response(this);
		}
	}
}
