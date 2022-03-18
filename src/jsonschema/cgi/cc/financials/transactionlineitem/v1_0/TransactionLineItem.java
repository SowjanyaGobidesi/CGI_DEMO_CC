package jsonschema.cgi.cc.financials.transactionlineitem.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.SimplePropertyProcessing;

import javax.annotation.Generated;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.financials.transactionlineitem-1.0#/definitions/TransactionLineItem", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class TransactionLineItem extends JsonWrapper {

  private static final String FQN = "cgi.cc.financials.transactionlineitem-1.0#/definitions/TransactionLineItem";

  public TransactionLineItem() {
    super();
  }

  private TransactionLineItem(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static TransactionLineItem wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new TransactionLineItem(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static TransactionLineItem parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static TransactionLineItem parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<TransactionLineItem> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), TransactionLineItem::wrap);
  }

  public static List<TransactionLineItem> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), TransactionLineItem::wrap);
  }

  public String getComments() {
    return getTyped("Comments");
  }

  public void setComments(String value) {
    put("Comments", value);
  }

  public String getbbox_lineCategory() {
    return getTyped("bbox_lineCategory");
  }

  public void setbbox_lineCategory(String value) {
    put("bbox_lineCategory", value);
  }

  public String getbbox_lineItemAmount() {
    return getTyped("bbox_lineItemAmount");
  }

  public void setbbox_lineItemAmount(String value) {
    put("bbox_lineItemAmount", value);
  }

  public Double getconf_lineCategory() {
    return getTyped("conf_lineCategory");
  }

  public void setconf_lineCategory(Double value) {
    put("conf_lineCategory", value);
  }

  public Double getconf_lineItemAmount() {
    return getTyped("conf_lineItemAmount");
  }

  public void setconf_lineItemAmount(Double value) {
    put("conf_lineItemAmount", value);
  }

  public String getlineCategory() {
    return getTyped("lineCategory");
  }

  public void setlineCategory(String value) {
    put("lineCategory", value);
  }

  public String getlineItemAmount() {
    return getTyped("lineItemAmount");
  }

  public void setlineItemAmount(String value) {
    put("lineItemAmount", value);
  }

}
