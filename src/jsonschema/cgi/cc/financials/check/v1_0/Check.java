package jsonschema.cgi.cc.financials.check.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.Autocreate;
import gw.lang.SimplePropertyProcessing;
import jsonschema.cgi.cc.financials.checkpayee.v1_0.CheckPayee;
import jsonschema.cgi.cc.financials.transaction.v1_0.Transaction;
import typekey.BankAccount;
import typekey.BankAccountType;
import typekey.CheckBatching;
import typekey.CheckHandlingInstructions;
import typekey.CheckType;
import typekey.DeductionType;
import typekey.DeliveryMethod;
import typekey.PaymentMethod;
import typekey.ReportabilityType;

import javax.annotation.Generated;
import java.util.Date;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.financials.check-1.0#/definitions/Check", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class Check extends JsonWrapper {

  private static final String FQN = "cgi.cc.financials.check-1.0#/definitions/Check";

  public Check() {
    super();
  }

  private Check(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static Check wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new Check(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static Check parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static Check parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<Check> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), Check::wrap);
  }

  public static List<Check> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), Check::wrap);
  }

  public String getAccountName() {
    return getTyped("AccountName");
  }

  public void setAccountName(String value) {
    put("AccountName", value);
  }

  public BankAccount getBankAccount() {
    return getTyped("BankAccount");
  }

  public void setBankAccount(BankAccount value) {
    put("BankAccount", value);
  }

  public String getBankAccountNumber() {
    return getTyped("BankAccountNumber");
  }

  public void setBankAccountNumber(String value) {
    put("BankAccountNumber", value);
  }

  public BankAccountType getBankAccountType() {
    return getTyped("BankAccountType");
  }

  public void setBankAccountType(BankAccountType value) {
    put("BankAccountType", value);
  }

  public String getBankName() {
    return getTyped("BankName");
  }

  public void setBankName(String value) {
    put("BankName", value);
  }

  public String getBankRoutingNumber() {
    return getTyped("BankRoutingNumber");
  }

  public void setBankRoutingNumber(String value) {
    put("BankRoutingNumber", value);
  }

  public CheckBatching getCheckBatching() {
    return getTyped("CheckBatching");
  }

  public void setCheckBatching(CheckBatching value) {
    put("CheckBatching", value);
  }

  public CheckHandlingInstructions getCheckInstructions() {
    return getTyped("CheckInstructions");
  }

  public void setCheckInstructions(CheckHandlingInstructions value) {
    put("CheckInstructions", value);
  }

  public String getCheckNumber() {
    return getTyped("CheckNumber");
  }

  public void setCheckNumber(String value) {
    put("CheckNumber", value);
  }

  public CheckType getCheckType() {
    return getTyped("CheckType");
  }

  public void setCheckType(CheckType value) {
    put("CheckType", value);
  }

  public String getComments() {
    return getTyped("Comments");
  }

  public void setComments(String value) {
    put("Comments", value);
  }

  public DeductionType getDeductionType() {
    return getTyped("DeductionType");
  }

  public void setDeductionType(DeductionType value) {
    put("DeductionType", value);
  }

  public DeliveryMethod getDeliveryMethod() {
    return getTyped("DeliveryMethod");
  }

  public void setDeliveryMethod(DeliveryMethod value) {
    put("DeliveryMethod", value);
  }

  public Date getEnteredTime() {
    return getTyped("EnteredTime");
  }

  public void setEnteredTime(Date value) {
    put("EnteredTime", value);
  }

  public Boolean getIsPrimary() {
    return getTyped("IsPrimary");
  }

  public void setIsPrimary(Boolean value) {
    put("IsPrimary", value);
  }

  public Date getIssueDate() {
    return getTyped("IssueDate");
  }

  public void setIssueDate(Date value) {
    put("IssueDate", value);
  }

  public String getMailTo() {
    return getTyped("MailTo");
  }

  public void setMailTo(String value) {
    put("MailTo", value);
  }

  public String getMemo() {
    return getTyped("Memo");
  }

  public void setMemo(String value) {
    put("Memo", value);
  }

  public String getPayTo() {
    return getTyped("PayTo");
  }

  public void setPayTo(String value) {
    put("PayTo", value);
  }

  public PaymentMethod getPaymentMethod() {
    return getTyped("PaymentMethod");
  }

  public void setPaymentMethod(PaymentMethod value) {
    put("PaymentMethod", value);
  }

  public Boolean getPendEscalationForBulk() {
    return getTyped("PendEscalationForBulk");
  }

  public void setPendEscalationForBulk(Boolean value) {
    put("PendEscalationForBulk", value);
  }

  public ReportabilityType getReportability() {
    return getTyped("Reportability");
  }

  public void setReportability(ReportabilityType value) {
    put("Reportability", value);
  }

  public Date getScheduledSendDate() {
    return getTyped("ScheduledSendDate");
  }

  public void setScheduledSendDate(Date value) {
    put("ScheduledSendDate", value);
  }

  public Date getServicePdEnd() {
    return getTyped("ServicePdEnd");
  }

  public void setServicePdEnd(Date value) {
    put("ServicePdEnd", value);
  }

  public Date getServicePdStart() {
    return getTyped("ServicePdStart");
  }

  public void setServicePdStart(Date value) {
    put("ServicePdStart", value);
  }

  public String getbbox_companyName() {
    return getTyped("bbox_companyName");
  }

  public void setbbox_companyName(String value) {
    put("bbox_companyName", value);
  }

  public String getbbox_invoiceNumber() {
    return getTyped("bbox_invoiceNumber");
  }

  public void setbbox_invoiceNumber(String value) {
    put("bbox_invoiceNumber", value);
  }

  public String getbbox_toAddress() {
    return getTyped("bbox_toAddress");
  }

  public void setbbox_toAddress(String value) {
    put("bbox_toAddress", value);
  }

  public String getbbox_towedDate() {
    return getTyped("bbox_towedDate");
  }

  public void setbbox_towedDate(String value) {
    put("bbox_towedDate", value);
  }

  public String getcompanyName() {
    return getTyped("companyName");
  }

  public void setcompanyName(String value) {
    put("companyName", value);
  }

  public Double getconf_companyName() {
    return getTyped("conf_companyName");
  }

  public void setconf_companyName(Double value) {
    put("conf_companyName", value);
  }

  public Double getconf_invoiceNumber() {
    return getTyped("conf_invoiceNumber");
  }

  public void setconf_invoiceNumber(Double value) {
    put("conf_invoiceNumber", value);
  }

  public Double getconf_toAddress() {
    return getTyped("conf_toAddress");
  }

  public void setconf_toAddress(Double value) {
    put("conf_toAddress", value);
  }

  public Double getconf_towedDate() {
    return getTyped("conf_towedDate");
  }

  public void setconf_towedDate(Double value) {
    put("conf_towedDate", value);
  }

  public String getinvoiceNumber() {
    return getTyped("invoiceNumber");
  }

  public void setinvoiceNumber(String value) {
    put("invoiceNumber", value);
  }

  public List<Transaction> getpayments() {
    return getWrappedList("payments", Transaction::wrap);
  }

  public void setpayments(List<Transaction> value) {
    putWrappedList("payments", value);
  }

  public void addToPayments(Transaction value) {
    addToListHelper("payments", value);
  }

  @Autocreate
  public CheckPayee getprimaryPayee() {
    return CheckPayee.wrap(getTyped("primaryPayee"));
  }

  public void setprimaryPayee(CheckPayee value) {
    put("primaryPayee", value == null ? null : value.unwrap());
  }

  public String gettoAddress() {
    return getTyped("toAddress");
  }

  public void settoAddress(String value) {
    put("toAddress", value);
  }

  public String gettowedDate() {
    return getTyped("towedDate");
  }

  public void settowedDate(String value) {
    put("towedDate", value);
  }

}
