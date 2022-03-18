package jsonschema.cgi.cc.financials.transaction.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.Autocreate;
import gw.lang.SimplePropertyProcessing;
import jsonschema.cgi.cc.financials.transactionlineitem.v1_0.TransactionLineItem;
import jsonschema.cgi.cc.financials.vehicle.v1_0.Vehicle;
import typekey.CostCategory;
import typekey.CostType;
import typekey.Currency;
import typekey.PaymentType;
import typekey.RecoveryCategory;

import javax.annotation.Generated;
import java.util.Date;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.financials.transaction-1.0#/definitions/Transaction", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class Transaction extends JsonWrapper {

  private static final String FQN = "cgi.cc.financials.transaction-1.0#/definitions/Transaction";

  public Transaction() {
    super();
  }

  private Transaction(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static Transaction wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new Transaction(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static Transaction parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static Transaction parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<Transaction> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), Transaction::wrap);
  }

  public static List<Transaction> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), Transaction::wrap);
  }

  public String getComments() {
    return getTyped("Comments");
  }

  public void setComments(String value) {
    put("Comments", value);
  }

  public CostCategory getCostCategory() {
    return getTyped("CostCategory");
  }

  public void setCostCategory(CostCategory value) {
    put("CostCategory", value);
  }

  public CostType getCostType() {
    return getTyped("CostType");
  }

  public void setCostType(CostType value) {
    put("CostType", value);
  }

  public Currency getCurrency() {
    return getTyped("Currency");
  }

  public void setCurrency(Currency value) {
    put("Currency", value);
  }

  public Boolean getDeletable() {
    return getTyped("Deletable");
  }

  public void setDeletable(Boolean value) {
    put("Deletable", value);
  }

  public Boolean getDoesNotErodeReserves() {
    return getTyped("DoesNotErodeReserves");
  }

  public void setDoesNotErodeReserves(Boolean value) {
    put("DoesNotErodeReserves", value);
  }

  public Boolean getEditable() {
    return getTyped("Editable");
  }

  public void setEditable(Boolean value) {
    put("Editable", value);
  }

  public PaymentType getPaymentType() {
    return getTyped("PaymentType");
  }

  public void setPaymentType(PaymentType value) {
    put("PaymentType", value);
  }

  public RecoveryCategory getRecoveryCategory() {
    return getTyped("RecoveryCategory");
  }

  public void setRecoveryCategory(RecoveryCategory value) {
    put("RecoveryCategory", value);
  }

  public Currency getReservingCurrency() {
    return getTyped("ReservingCurrency");
  }

  public void setReservingCurrency(Currency value) {
    put("ReservingCurrency", value);
  }

  public typekey.Transaction getSubtype() {
    return getTyped("Subtype");
  }

  public void setSubtype(typekey.Transaction value) {
    put("Subtype", value);
  }

  public Date getTransactionDate() {
    return getTyped("TransactionDate");
  }

  public void setTransactionDate(Date value) {
    put("TransactionDate", value);
  }

  public String getbbox_taxAmount() {
    return getTyped("bbox_taxAmount");
  }

  public void setbbox_taxAmount(String value) {
    put("bbox_taxAmount", value);
  }

  public String getbbox_totalAmount() {
    return getTyped("bbox_totalAmount");
  }

  public void setbbox_totalAmount(String value) {
    put("bbox_totalAmount", value);
  }

  public Double getconf_taxAmount() {
    return getTyped("conf_taxAmount");
  }

  public void setconf_taxAmount(Double value) {
    put("conf_taxAmount", value);
  }

  public Double getconf_totalAmount() {
    return getTyped("conf_totalAmount");
  }

  public void setconf_totalAmount(Double value) {
    put("conf_totalAmount", value);
  }

  public List<TransactionLineItem> getlineItems() {
    return getWrappedList("lineItems", TransactionLineItem::wrap);
  }

  public void setlineItems(List<TransactionLineItem> value) {
    putWrappedList("lineItems", value);
  }

  public void addToLineItems(TransactionLineItem value) {
    addToListHelper("lineItems", value);
  }

  public String gettaxAmount() {
    return getTyped("taxAmount");
  }

  public void settaxAmount(String value) {
    put("taxAmount", value);
  }

  public String gettotalAmount() {
    return getTyped("totalAmount");
  }

  public void settotalAmount(String value) {
    put("totalAmount", value);
  }

  @Autocreate
  public Vehicle getvehicle() {
    return Vehicle.wrap(getTyped("vehicle"));
  }

  public void setvehicle(Vehicle value) {
    put("vehicle", value == null ? null : value.unwrap());
  }

}
