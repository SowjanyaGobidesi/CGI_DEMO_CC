package jsonschema.cgi.cc.financials.checkpayee.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.SimplePropertyProcessing;
import typekey.ContactRole;

import javax.annotation.Generated;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.financials.checkpayee-1.0#/definitions/CheckPayee", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class CheckPayee extends JsonWrapper {

  private static final String FQN = "cgi.cc.financials.checkpayee-1.0#/definitions/CheckPayee";

  public CheckPayee() {
    super();
  }

  private CheckPayee(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static CheckPayee wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new CheckPayee(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static CheckPayee parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static CheckPayee parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<CheckPayee> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), CheckPayee::wrap);
  }

  public static List<CheckPayee> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), CheckPayee::wrap);
  }

  public ContactRole getPayeeType() {
    return getTyped("PayeeType");
  }

  public void setPayeeType(ContactRole value) {
    put("PayeeType", value);
  }

  public String getbbox_ownerName() {
    return getTyped("bbox_ownerName");
  }

  public void setbbox_ownerName(String value) {
    put("bbox_ownerName", value);
  }

  public Double getconf_ownerName() {
    return getTyped("conf_ownerName");
  }

  public void setconf_ownerName(Double value) {
    put("conf_ownerName", value);
  }

  public String getownerName() {
    return getTyped("ownerName");
  }

  public void setownerName(String value) {
    put("ownerName", value);
  }

}
