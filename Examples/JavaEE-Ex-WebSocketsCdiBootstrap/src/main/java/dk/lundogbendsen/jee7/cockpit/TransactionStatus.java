package dk.lundogbendsen.jee7.cockpit;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlType;

@XmlType
public class TransactionStatus {
	private Date timeStamp;
	private String message;
	private ResultType result;
	
	public TransactionStatus(String message, ResultType result) {
		super();
		this.timeStamp=new Date();
		this.message = message;
		this.result = result;
	}

	public TransactionStatus(String message) {
		this(message, ResultType.PENDING);
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultType getResult() {
		return result;
	}

	public void setResult(ResultType result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TransactionStatus [timeStamp=" + timeStamp + ", message="
				+ message + ", result=" + result + "]";
	}
}
