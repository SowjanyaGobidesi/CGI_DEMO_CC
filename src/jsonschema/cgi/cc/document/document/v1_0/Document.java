package jsonschema.cgi.cc.document.document.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.Autocreate;
import gw.lang.SimplePropertyProcessing;
import jsonschema.cgi.cc.document.documentcontent.v1_0.DocumentContent;
import jsonschema.cgi.cc.document.documentsummary.v1_0.DocumentSummary;

import javax.annotation.Generated;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.document.document-1.0#/definitions/Document", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class Document extends JsonWrapper {

  private static final String FQN = "cgi.cc.document.document-1.0#/definitions/Document";

  public Document() {
    super();
  }

  private Document(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static Document wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new Document(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static Document parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static Document parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<Document> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), Document::wrap);
  }

  public static List<Document> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), Document::wrap);
  }

  @Autocreate
  public DocumentContent getDocumentContent() {
    return DocumentContent.wrap(getTyped("DocumentContent"));
  }

  public void setDocumentContent(DocumentContent value) {
    put("DocumentContent", value == null ? null : value.unwrap());
  }

  @Autocreate
  public DocumentSummary getDocumentSummary() {
    return DocumentSummary.wrap(getTyped("DocumentSummary"));
  }

  public void setDocumentSummary(DocumentSummary value) {
    put("DocumentSummary", value == null ? null : value.unwrap());
  }

}
