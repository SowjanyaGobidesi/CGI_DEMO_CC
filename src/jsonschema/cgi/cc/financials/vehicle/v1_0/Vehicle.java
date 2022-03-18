package jsonschema.cgi.cc.financials.vehicle.v1_0;

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
@Generated(comments = "cgi.cc.financials.vehicle-1.0#/definitions/Vehicle", value = "com.guidewire.pl.json.codegen.SchemaWrappersGenerator")
public class Vehicle extends JsonWrapper {

  private static final String FQN = "cgi.cc.financials.vehicle-1.0#/definitions/Vehicle";

  public Vehicle() {
    super();
  }

  private Vehicle(JsonObject jsonObject) {
    super(jsonObject);
  }

  public static Vehicle wrap(JsonObject jsonObject) {
    return jsonObject == null ? null : new Vehicle(jsonObject);
  }

  public static String getFullyQualifiedName() {
    return FQN;
  }

  public static Vehicle parse(String json) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN));
  }

  public static Vehicle parse(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : wrap(JsonParser.OBJECT.parse(json, FQN, jsonValidationResult, jsonDeserializationOptions));
  }

  public static List<Vehicle> parseArray(String json) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN), Vehicle::wrap);
  }

  public static List<Vehicle> parseArray(String json, JsonValidationResult jsonValidationResult, JsonDeserializationOptions jsonDeserializationOptions) {
    return json == null ? null : new JsonWrapperList<>(JsonParser.OBJECT.parseArray(json, FQN, jsonValidationResult, jsonDeserializationOptions), Vehicle::wrap);
  }

  public String getbbox_vehicleMake() {
    return getTyped("bbox_vehicleMake");
  }

  public void setbbox_vehicleMake(String value) {
    put("bbox_vehicleMake", value);
  }

  public String getbbox_vehicleModel() {
    return getTyped("bbox_vehicleModel");
  }

  public void setbbox_vehicleModel(String value) {
    put("bbox_vehicleModel", value);
  }

  public String getbbox_vin() {
    return getTyped("bbox_vin");
  }

  public void setbbox_vin(String value) {
    put("bbox_vin", value);
  }

  public String getbbox_year() {
    return getTyped("bbox_year");
  }

  public void setbbox_year(String value) {
    put("bbox_year", value);
  }

  public Double getconf_vehicleMake() {
    return getTyped("conf_vehicleMake");
  }

  public void setconf_vehicleMake(Double value) {
    put("conf_vehicleMake", value);
  }

  public Double getconf_vehicleModel() {
    return getTyped("conf_vehicleModel");
  }

  public void setconf_vehicleModel(Double value) {
    put("conf_vehicleModel", value);
  }

  public Double getconf_vin() {
    return getTyped("conf_vin");
  }

  public void setconf_vin(Double value) {
    put("conf_vin", value);
  }

  public Double getconf_year() {
    return getTyped("conf_year");
  }

  public void setconf_year(Double value) {
    put("conf_year", value);
  }

  public String getvehicleMake() {
    return getTyped("vehicleMake");
  }

  public void setvehicleMake(String value) {
    put("vehicleMake", value);
  }

  public String getvehicleModel() {
    return getTyped("vehicleModel");
  }

  public void setvehicleModel(String value) {
    put("vehicleModel", value);
  }

  public String getvin() {
    return getTyped("vin");
  }

  public void setvin(String value) {
    put("vin", value);
  }

  public Integer getyear() {
    return getTyped("year");
  }

  public void setyear(Integer value) {
    put("year", value);
  }

}
