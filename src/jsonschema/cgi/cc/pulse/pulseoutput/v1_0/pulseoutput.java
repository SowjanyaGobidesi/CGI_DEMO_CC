package jsonschema.cgi.cc.pulse.pulseoutput.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.Autocreate;
import gw.lang.SimplePropertyProcessing;
import jsonschema.cgi.cc.pulse.content.v1_0.content;
import jsonschema.cgi.cc.pulse.data.v1_0.data;
import jsonschema.cgi.cc.pulse.globalstats.v1_0.global_stats;
import jsonschema.cgi.cc.pulse.metadata.v1_0.metadata;

import javax.annotation.Generated;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.pulse.pulseoutput-1.0#/definitions/pulseoutput", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class pulseoutput extends JsonWrapper {

  private static final String FQN = "cgi.cc.pulse.pulseoutput-1.0#/definitions/pulseoutput";

  public pulseoutput() {
    super();
  }

  private pulseoutput(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static pulseoutput wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new pulseoutput(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static pulseoutput parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static pulseoutput parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<pulseoutput> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), pulseoutput::wrap);
  }

  public static List<pulseoutput> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), pulseoutput::wrap);
  }

  @Autocreate
  public content getcontent() {
    return content.wrap(getTyped("content"));
  }

  public void setcontent(content value) {
    put("content", value == null ? null : value.unwrap());
  }

  @Autocreate
  public data getdata() {
    return data.wrap(getTyped("data"));
  }

  public void setdata(data value) {
    put("data", value == null ? null : value.unwrap());
  }

  @Autocreate
  public global_stats getglobal_stats() {
    return global_stats.wrap(getTyped("global_stats"));
  }

  public void setglobal_stats(global_stats value) {
    put("global_stats", value == null ? null : value.unwrap());
  }

  @Autocreate
  public metadata getmetadata() {
    return metadata.wrap(getTyped("metadata"));
  }

  public void setmetadata(metadata value) {
    put("metadata", value == null ? null : value.unwrap());
  }

}
