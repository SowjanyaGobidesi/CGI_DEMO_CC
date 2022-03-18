package jsonschema.cgi.cc.pulse.data.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.Autocreate;
import gw.lang.SimplePropertyProcessing;
import jsonschema.cgi.cc.financials.check.v1_0.Check;

import javax.annotation.Generated;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.pulse.data-1.0#/definitions/data", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class data extends JsonWrapper {

  private static final String FQN = "cgi.cc.pulse.data-1.0#/definitions/data";

  public data() {
    super();
  }

  private data(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static data wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new data(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static data parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static data parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<data> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), data::wrap);
  }

  public static List<data> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), data::wrap);
  }

  @Autocreate
  public Check getgeneral() {
    return Check.wrap(getTyped("general"));
  }

  public void setgeneral(Check value) {
    put("general", value == null ? null : value.unwrap());
  }

}
