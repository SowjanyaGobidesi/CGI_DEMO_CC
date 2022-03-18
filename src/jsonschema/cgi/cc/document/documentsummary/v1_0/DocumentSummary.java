package jsonschema.cgi.cc.document.documentsummary.v1_0;

import com.guidewire.pl.json.runtime.JsonWrapperList;
import gw.api.json.JsonDeserializationOptions;
import gw.api.json.JsonObject;
import gw.api.json.JsonParser;
import gw.api.json.JsonValidationResult;
import gw.api.json.JsonWrapper;
import gw.lang.SimplePropertyProcessing;
import typekey.DocumentType;
import typekey.LanguageType;

import javax.annotation.Generated;
import java.util.Date;
import java.util.List;

@SimplePropertyProcessing
@Generated(comments = "cgi.cc.document.documentsummary-1.0#/definitions/DocumentSummary", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class DocumentSummary extends JsonWrapper {

  private static final String FQN = "cgi.cc.document.documentsummary-1.0#/definitions/DocumentSummary";

  public DocumentSummary() {
    super();
  }

  private DocumentSummary(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static DocumentSummary wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new DocumentSummary(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static DocumentSummary parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static DocumentSummary parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<DocumentSummary> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), DocumentSummary::wrap);
  }

  public static List<DocumentSummary> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), DocumentSummary::wrap);
  }

  public String getAuthor() {
    return getTyped("Author");
  }

  public void setAuthor(String value) {
    put("Author", value);
  }

  public Date getDateModified() {
    return getTyped("DateModified");
  }

  public void setDateModified(Date value) {
    put("DateModified", value);
  }

  public String getDescription() {
    return getTyped("Description");
  }

  public void setDescription(String value) {
    put("Description", value);
  }

  public DocumentType getDocumentType() {
    return getTyped("DocumentType");
  }

  public void setDocumentType(DocumentType value) {
    put("DocumentType", value);
  }

  public Boolean getHasContent() {
    return getTyped("HasContent");
  }

  public void setHasContent(Boolean value) {
    put("HasContent", value);
  }

  public LanguageType getLanguage() {
    return getTyped("Language");
  }

  public void setLanguage(LanguageType value) {
    put("Language", value);
  }

  public String getName() {
    return getTyped("Name");
  }

  public void setName(String value) {
    put("Name", value);
  }

}
