package jsonschema.cgi.cc.pulse.jobstatus.v1_0;

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
@Generated(comments = "cgi.cc.pulse.jobstatus-1.0#/definitions/job_status", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class job_status extends JsonWrapper {

  private static final String FQN = "cgi.cc.pulse.jobstatus-1.0#/definitions/job_status";

  public job_status() {
    super();
  }

  private job_status(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static job_status wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new job_status(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static job_status parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static job_status parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<job_status> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), job_status::wrap);
  }

  public static List<job_status> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), job_status::wrap);
  }

  public String getcode() {
    return getTyped("code");
  }

  public void setcode(String value) {
    put("code", value);
  }

  public String getmessage() {
    return getTyped("message");
  }

  public void setmessage(String value) {
    put("message", value);
  }

  public String getprocessing_time() {
    return getTyped("processing_time");
  }

  public void setprocessing_time(String value) {
    put("processing_time", value);
  }

}
