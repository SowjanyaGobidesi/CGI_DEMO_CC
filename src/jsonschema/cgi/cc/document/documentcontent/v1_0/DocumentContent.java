package jsonschema.cgi.cc.document.documentcontent.v1_0;

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
@Generated(comments = "cgi.cc.document.documentcontent-1.0#/definitions/DocumentContent", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class DocumentContent extends JsonWrapper {

  private static final String FQN = "cgi.cc.document.documentcontent-1.0#/definitions/DocumentContent";

  public DocumentContent() {
    super();
  }

  private DocumentContent(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static DocumentContent wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new DocumentContent(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static DocumentContent parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static DocumentContent parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<DocumentContent> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), DocumentContent::wrap);
  }

  public static List<DocumentContent> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), DocumentContent::wrap);
  }

  public byte[] getContent() {
    return getTyped("Content");
  }

  public void setContent(byte[] value) {
    put("Content", value);
  }

  public String getMimeType() {
    return getTyped("MimeType");
  }

  public void setMimeType(String value) {
    put("MimeType", value);
  }

}
