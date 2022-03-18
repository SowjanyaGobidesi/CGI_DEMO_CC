package jsonschema.cgi.cc.pulse.metadata.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.Autocreate;
import gw.lang.SimplePropertyProcessing;
import jsonschema.cgi.cc.pulse.jobstatus.v1_0.job_status;

import javax.annotation.Generated;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.pulse.metadata-1.0#/definitions/metadata", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class metadata extends JsonWrapper {

  private static final String FQN = "cgi.cc.pulse.metadata-1.0#/definitions/metadata";

  public metadata() {
    super();
  }

  private metadata(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static metadata wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new metadata(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static metadata parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static metadata parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<metadata> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), metadata::wrap);
  }

  public static List<metadata> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), metadata::wrap);
  }

  public String getfile_name() {
    return getTyped("file_name");
  }

  public void setfile_name(String value) {
    put("file_name", value);
  }

  public String getjob_id() {
    return getTyped("job_id");
  }

  public void setjob_id(String value) {
    put("job_id", value);
  }

  @Autocreate
  public job_status getjob_status() {
    return job_status.wrap(getTyped("job_status"));
  }

  public void setjob_status(job_status value) {
    put("job_status", value == null ? null : value.unwrap());
  }

  public String getlocation() {
    return getTyped("location");
  }

  public void setlocation(String value) {
    put("location", value);
  }

  public String getowner() {
    return getTyped("owner");
  }

  public void setowner(String value) {
    put("owner", value);
  }

  public String getreport_date_time() {
    return getTyped("report_date_time");
  }

  public void setreport_date_time(String value) {
    put("report_date_time", value);
  }

  public String getsize() {
    return getTyped("size");
  }

  public void setsize(String value) {
    put("size", value);
  }

}
