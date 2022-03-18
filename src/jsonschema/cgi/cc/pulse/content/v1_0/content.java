package jsonschema.cgi.cc.pulse.content.v1_0;

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
@Generated(comments = "cgi.cc.pulse.content-1.0#/definitions/content", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class content extends JsonWrapper {

  private static final String FQN = "cgi.cc.pulse.content-1.0#/definitions/content";

  public content() {
    super();
  }

  private content(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static content wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new content(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static content parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static content parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<content> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), content::wrap);
  }

  public static List<content> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), content::wrap);
  }

  public String getencoded_text() {
    return getTyped("encoded_text");
  }

  public void setencoded_text(String value) {
    put("encoded_text", value);
  }

  public String getencoding() {
    return getTyped("encoding");
  }

  public void setencoding(String value) {
    put("encoding", value);
  }

}
