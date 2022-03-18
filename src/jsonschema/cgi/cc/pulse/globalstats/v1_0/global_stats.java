package jsonschema.cgi.cc.pulse.globalstats.v1_0;

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
@Generated(comments = "cgi.cc.pulse.globalstats-1.0#/definitions/global_stats", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class global_stats extends JsonWrapper {

  private static final String FQN = "cgi.cc.pulse.globalstats-1.0#/definitions/global_stats";

  public global_stats() {
    super();
  }

  private global_stats(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static global_stats wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new global_stats(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static global_stats parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static global_stats parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<global_stats> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), global_stats::wrap);
  }

  public static List<global_stats> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), global_stats::wrap);
  }

  public String getclassification() {
    return getTyped("classification");
  }

  public void setclassification(String value) {
    put("classification", value);
  }

  public String getclassified() {
    return getTyped("classified");
  }

  public void setclassified(String value) {
    put("classified", value);
  }

  public String getempty_line_count() {
    return getTyped("empty_line_count");
  }

  public void setempty_line_count(String value) {
    put("empty_line_count", value);
  }

  public String getformat() {
    return getTyped("format");
  }

  public void setformat(String value) {
    put("format", value);
  }

  public String getline_count() {
    return getTyped("line_count");
  }

  public void setline_count(String value) {
    put("line_count", value);
  }

  public String getpage_count() {
    return getTyped("page_count");
  }

  public void setpage_count(String value) {
    put("page_count", value);
  }

  public String gettype() {
    return getTyped("type");
  }

  public void settype(String value) {
    put("type", value);
  }

  public String getword_count() {
    return getTyped("word_count");
  }

  public void setword_count(String value) {
    put("word_count", value);
  }

}
